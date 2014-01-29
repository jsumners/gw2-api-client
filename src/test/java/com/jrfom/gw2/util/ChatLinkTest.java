package com.jrfom.gw2.util;

import com.jrfom.gw2.api.model.Coins;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class ChatLinkTest {
  private static final Logger log = LoggerFactory.getLogger(ChatLinkTest.class);

  @Test
  public void testChatLinkItem() {
    log.info("Running ChatLink(link) for item test");
    ChatLink chatLink = new ChatLink("[&AgHRcQAA]");
    assertTrue(chatLink.getId() == 29137);
    assertTrue(chatLink.getCount() == 1);
  }

  @Test
  public void testChatLinkModdedItem() {
    log.info("Running ChatLink(link) for modded item test");
    ChatLink chatLink = new ChatLink("[&AgHRcQBA1l8AAA==]");
    assertTrue(chatLink.getId() == 29137);
    assertTrue(chatLink.getUpgradeId() == 24534);
    assertTrue(chatLink.getCount() == 1);
  }

  @Test
  public void testChatLinkCoin() {
    log.info("Running ChatLink(link) for coin test");
    ChatLink chatLink = new ChatLink("[&AQAAAAA=]");
    assertTrue(chatLink.getCoins() == 0);

    chatLink.setLinkString("[&AQEAAAA=]");
    assertTrue(chatLink.getCoins() == 1);

    chatLink.setLinkString("[&AdsnAAA=]");
    assertTrue(chatLink.getCoins() == 10203);

    Coins coins = new Coins(chatLink.getCoins());
    assertTrue(coins.goldValue() == 1);
    assertTrue(coins.silverValue() == 2);
    assertTrue(coins.copperValue() == 3);
  }

  @Test
  public void testChatLinkMapLink() {
    log.info("Running ChatLink(link) for map link test");
    ChatLink chatLink = new ChatLink("[&BDgAAAA=]");
    assertTrue(chatLink.getPoiId() == 56);
  }

  @Test
  public void testChatLinkRecipeLink() {
    log.info("Running ChatLink(link) for recipe link test");
    ChatLink chatLink = new ChatLink("[&CgEAAAA=]");
    assertTrue(chatLink.getRecipeId() == 1);
  }
}