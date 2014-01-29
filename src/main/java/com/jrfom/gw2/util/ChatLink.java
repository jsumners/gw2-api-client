package com.jrfom.gw2.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import com.google.common.io.BaseEncoding;
import com.jrfom.gw2.exceptions.InvalidLinkString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>This class provides an interface for decoding
 * <a href="http://wiki.guildwars2.com/wiki/Chat_link_format">chat links</a>.
 * The link is decoded whenever it is set on the object. That is, if you create
 * the object with {@link com.jrfom.gw2.util.ChatLink#ChatLink(String)}, or if
 * you invoke {@link com.jrfom.gw2.util.ChatLink#setLinkString(String)}, the
 * chat link is automatically decoded. In either case, if the string you supply
 * is not formatted correctly, then {@link com.jrfom.gw2.exceptions.InvalidLinkString}
 * will be thrown.</p>
 *
 * <p>Once a chat link has been decoded, you can determine the type of link by
 * calling {@link ChatLink#getType()}. Once you know the type, you can use
 * the provided accessors to get the information that link decoded to.</p>
 */
public class ChatLink {
  private static final Logger log = LoggerFactory.getLogger(ChatLink.class);

  public final static byte COIN_LINK = 0x01;
  public final static byte ITEM_LINK = 0x02;
  public final static byte TEXT_STRING = 0x03;
  public final static byte MAP_LINK = 0x04;
  public final static byte PVP_GAME = 0x05;
  public final static byte SKILL_LINK = 0x07;
  public final static byte TRAIT_LINK = 0x08;
  public final static byte PLAYER_LINK = 0x09;
  public final static byte RECIPE_LINK = 0x0A;

  private String linkString;
  private int id;
  private int upgradeId;
  private int count;
  private int coins;
  private int poiId;
  private int recipeId;

  private byte header;
  private byte[] body;

  /**
   * Create an empty instance. In this case, you will need to
   * invoke {@link com.jrfom.gw2.util.ChatLink#setLinkString(String)}
   * before any other operations can be performed.
   */
  public ChatLink() {}

  /**
   * Create an instance of {@code ChatLink} with the link set
   * to the provided {@code link}.
   *
   * @param link A valid chat link string, e.g. "[&AgEAWgAA]".
   *
   * @throws com.jrfom.gw2.exceptions.InvalidLinkString
   */
  public ChatLink(String link) {
    this.setLinkString(link);
  }

  /**
   * Retrieve the current link text. For example, "[&AgEAWgAA]".
   *
   * @return
   */
  public String getLinkString() {
    return this.linkString;
  }

  /**
   * Set the link text for the instance. This will overwrite
   * any previously set link text. A "link text" is in the form
   * "[&AgEAWgAA]".
   *
   * @param linkString The link text to set.
   *
   * @throws com.jrfom.gw2.exceptions.InvalidLinkString
   */
  public void setLinkString(String linkString) {
    if (!this.isValid(linkString)) {
      throw new InvalidLinkString();
    }

    this.coins = -1;
    this.id = -1;
    this.upgradeId = -1;
    this.count = -1;
    this.poiId = -1;
    this.recipeId = -1;

    this.linkString = linkString;
    this.decode();
  }

  /**
   * <p>Lookup what kind of link the {@code ChatLink} represents.
   * Possible results are:</p>
   *
   * <ul>
   *   <li>{@link com.jrfom.gw2.util.ChatLink#COIN_LINK}</li>
   *   <li>{@link com.jrfom.gw2.util.ChatLink#ITEM_LINK}</li>
   *   <li>{@link com.jrfom.gw2.util.ChatLink#TEXT_STRING}</li>
   *   <li>{@link com.jrfom.gw2.util.ChatLink#MAP_LINK}</li>
   *   <li>{@link com.jrfom.gw2.util.ChatLink#PVP_GAME}</li>
   *   <li>{@link com.jrfom.gw2.util.ChatLink#SKILL_LINK}</li>
   *   <li>{@link com.jrfom.gw2.util.ChatLink#TRAIT_LINK}</li>
   *   <li>{@link com.jrfom.gw2.util.ChatLink#PLAYER_LINK}</li>
   *   <li>{@link com.jrfom.gw2.util.ChatLink#RECIPE_LINK}</li>
   * </ul>
   *
   * @return A byte representing the type of link the chat link is.
   */
  public byte getType() {
    return this.header;
  }

  /**
   * If the chat link decodes to an item, then you can retrieve
   * the item id via this method.
   *
   * @return An item identifier if the chat link decodes to an item.
   */
  public int getId() {
    return this.id;
  }

  /**
   * If the chat link decodes to an item, and that item has an upgrade applied,
   * then you can retrieve the identifier of the upgrade item via this method.
   *
   * @return An item identifier for an upgrade item if the chat link decodes
   * to an upgraded item.
   */
  public int getUpgradeId() {
    return this.upgradeId;
  }

  /**
   * If the chat link decodes to an item, then you can retrieve
   * the count of the item via this method.
   *
   * @return The count of the item if the chat link decodes to an item.
   */
  public int getCount() {
    return this.count;
  }

  /**
   * If the chat link decodes to an amount of in-game currency, then you can
   * retrieve the number of "copper" coins the link represents via this method.
   *
   * @return The number of in-game copper represented by the link if the link
   * is a {@link com.jrfom.gw2.util.ChatLink#COIN_LINK}.
   */
  public int getCoins() {
    return this.coins;
  }

  /**
   * If the chat link decodes to a {@link com.jrfom.gw2.util.ChatLink#MAP_LINK}
   * then you can use this method to retrieve the identifier for the
   * {@link com.jrfom.gw2.api.model.geography.PointOfInterest}.
   *
   * @return An identifier for a {@link com.jrfom.gw2.api.model.geography.PointOfInterest}
   * if the chat link decodes to a {@link com.jrfom.gw2.util.ChatLink#MAP_LINK}.
   */
  public int getPoiId() {
    return this.poiId;
  }

  /**
   * If the chat link decodes to a {@link com.jrfom.gw2.util.ChatLink#RECIPE_LINK},
   * then you can use this method to retrieve the identifier for the
   * {@link com.jrfom.gw2.api.model.crafting.Recipe}.
   *
   * @return An identifier for a {@link com.jrfom.gw2.api.model.crafting.Recipe}
   * if the chat link decodes to a {@link com.jrfom.gw2.util.ChatLink#RECIPE_LINK}.
   */
  public int getRecipeId() {
    return this.recipeId;
  }

  private boolean isValid(String linkString) {
    boolean result = false;

    if (linkString.substring(0, 2).equals("[&")) {
      if (linkString.substring(linkString.length() - 1).equals("]")) {
        result = true;
      }
    }

    return result;
  }

  private void decode() {
    log.debug("Decoding link");
    BaseEncoding b64codec = BaseEncoding.base64();
    String code = this.linkString.substring(2, this.linkString.length() - 1);
    byte[] bytes = b64codec.decode(code);

    log.debug("Code string = `{}`", code);
    log.debug("Bytes = `{}`", this.toHex(bytes));

    this.header = bytes[0];
    log.debug("Link type: `{}`", this.getType());

    this.body = Arrays.copyOfRange(bytes, 1, bytes.length);
    log.debug("Data bytes: `{}`", this.toHex(this.body));

    switch (this.header) {
      case ChatLink.ITEM_LINK:
        this.decodeItem();
        break;
      case ChatLink.COIN_LINK:
        this.decodeCoin();
        break;
      case ChatLink.MAP_LINK:
        this.decodeMap();
        break;
      case ChatLink.RECIPE_LINK:
        this.decodeRecipe();
        break;
      default:
    }
  }

  private void decodeItem() {
    this.count = (int) this.body[0];
    log.debug("Item count = `{}`", this.count);
    byte[] itemBytes = new byte[4];
    byte[] upgradeBytes = new byte[4];

    ByteBuffer bb = ByteBuffer.allocate(4);
    bb.order(ByteOrder.LITTLE_ENDIAN);
    bb.clear();

    itemBytes = Arrays.copyOfRange(this.body, 1, 5);
    if (this.body.length > 5) {
      // Item has an upgrade component so we have to
      // null out the last byte.
      itemBytes[3] = 0x00;

      // And then get the upgrade item bytes.
      upgradeBytes = Arrays.copyOfRange(this.body, 5, 9);
    }

    // Read the base item id
    bb.put(itemBytes);
    bb.flip();
    this.id = bb.getInt();
    log.debug("Item id = `{}`", this.id);

    // Read the upgrade component id if it exists
    if (this.body.length > 5) {
      bb.clear();
      bb.put(upgradeBytes);
      bb.flip();
      this.upgradeId = bb.getInt();
    }
  }

  private void decodeCoin() {
    ByteBuffer bb = ByteBuffer.allocate(this.body.length);
    bb.order(ByteOrder.LITTLE_ENDIAN);
    bb.clear();

    bb.put(this.body);
    bb.flip();

    this.coins = bb.getInt();
    log.debug("Coins = `{}`", this.coins);
  }

  private void decodeMap() {
    this.poiId = this.decodeTwoByteId(this.body);
    log.debug("Map id = `{}`", this.poiId);
  }

  private void decodeRecipe() {
    this.recipeId = this.decodeTwoByteId(this.body);
    log.debug("Recipe id = `{}`", this.recipeId);
  }

  private int decodeTwoByteId(byte[] bytes) {
    // Except, integers are 4 bytes wide.
    int result;
    ByteBuffer bb = ByteBuffer.allocate(4);

    bb.order(ByteOrder.LITTLE_ENDIAN);
    bb.clear();
    bb.put(bytes);
    bb.flip();

    result = bb.getInt();

    return result;
  }

  private String toHex(byte[] bytes) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0,j = bytes.length; i < j; i += 1) {
      builder.append(String.format("%02X ", bytes[i]));
    }
    return builder.toString();
  }
}