package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jrfom.gw2.api.model.items.*;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class GenericItemSerializerTest {
  private static final Logger log = LoggerFactory.getLogger(GenericItemSerializerTest.class);

  private ObjectMapper mapper;

  @Before
  public void setup() {
    this.mapper = new ObjectMapper();
  }

  @Test
  public void testArmorItemSerialization() throws IOException {
    log.info("Running ArmorItem serializer test");
    ArmorItem armorItem = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/ArmorItem.json"),
      ArmorItem.class
    );

    ObjectWriter writer = this.mapper.writer();
    String json = writer.writeValueAsString(armorItem);

    ArmorItem armorItem2 = this.mapper.readValue(json, ArmorItem.class);
    assertTrue(armorItem.getType().equals(armorItem2.getType()));
    assertTrue(armorItem.getArmor().getType().equals(armorItem2.getArmor().getType()));
  }

  @Test
  public void testBagItemSerialization() throws IOException {
    log.info("Running BagItem serializer test");
    BagItem bagItem = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/BagItem.json"),
      BagItem.class
    );

    ObjectWriter writer = this.mapper.writer();
    String json = writer.writeValueAsString(bagItem);

    BagItem bagItem2 = this.mapper.readValue(json, BagItem.class);
    assertTrue(bagItem.getType().equals(bagItem2.getType()));
    assertTrue(bagItem.getBag().getSize() == bagItem2.getBag().getSize());
  }

  @Test
  public void testConsumableItemSerialization() throws IOException {
    log.info("Running ConsumableItem serializer test");
    ConsumableItem item = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/ConsumableItem.json"),
      ConsumableItem.class
    );

    ObjectWriter writer = this.mapper.writer();
    String json = writer.writeValueAsString(item);

    ConsumableItem item2 = this.mapper.readValue(json, ConsumableItem.class);
    assertTrue(item.getType().equals(item2.getType()));
    assertTrue(item.getConsumable().getType().equals(item2.getConsumable().getType()));
  }

  @Test
  public void testContainerItemSerialization() throws IOException {
    log.info("Running ContainerItem serializer test");
    ContainerItem item = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/ContainerItem.json"),
      ContainerItem.class
    );

    ObjectWriter writer = this.mapper.writer();
    String json = writer.writeValueAsString(item);

    ContainerItem item2 = this.mapper.readValue(json, ContainerItem.class);
    assertTrue(item.getType().equals(item2.getType()));
    assertTrue(item.getContainer().getType().equals(item2.getContainer().getType()));
  }

  @Test
  public void testCraftingMaterialItemSerialization() throws IOException {
    log.info("Running CraftingMaterialItem serializer test");
    CraftingMaterialItem item = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/CraftingMaterialItem.json"),
      CraftingMaterialItem.class
    );

    ObjectWriter writer = this.mapper.writer();
    String json = writer.writeValueAsString(item);

    CraftingMaterialItem item2 = this.mapper.readValue(json, CraftingMaterialItem.class);
    assertTrue(item.getType().equals(item2.getType()));
    assertTrue(item.getItemId().equals(item2.getItemId()));
  }

  @Test
  public void testGizmoItemSerialization() throws IOException {
    log.info("Running GizmoItem serializer test");
    GizmoItem item = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/GizmoItem.json"),
      GizmoItem.class
    );

    ObjectWriter writer = this.mapper.writer();
    String json = writer.writeValueAsString(item);

    GizmoItem item2 = this.mapper.readValue(json, GizmoItem.class);
    assertTrue(item.getType().equals(item2.getType()));
    assertTrue(item.getGizmo().getType().equals(item2.getGizmo().getType()));
  }

  @Test
  public void testMiniPetItemSerialization() throws IOException {
    log.info("Running MiniPetItem serializer test");
    MiniPetItem item = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/MiniPetItem.json"),
      MiniPetItem.class
    );

    ObjectWriter writer = this.mapper.writer();
    String json = writer.writeValueAsString(item);

    MiniPetItem item2 = this.mapper.readValue(json, MiniPetItem.class);
    assertTrue(item.getType().equals(item2.getType()));
    assertTrue(item.getItemId().equals(item2.getItemId()));
  }

  @Test
  public void testTrinketItemSerialization() throws IOException {
    log.info("Running TrinketItem serializer test");
    TrinketItem item = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/TrinketItem.json"),
      TrinketItem.class
    );

    ObjectWriter writer = this.mapper.writer();
    String json = writer.writeValueAsString(item);

    TrinketItem item2 = this.mapper.readValue(json, TrinketItem.class);
    assertTrue(item.getType().equals(item2.getType()));
    assertTrue(item.getTrinket().getType().equals(item2.getTrinket().getType()));
  }

  @Test
  public void testTrophyItemSerialization() throws IOException {
    log.info("Running TrophyItem serializer test");
    TrophyItem item = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/TrophyItem.json"),
      TrophyItem.class
    );

    ObjectWriter writer = this.mapper.writer();
    String json = writer.writeValueAsString(item);

    TrophyItem item2 = this.mapper.readValue(json, TrophyItem.class);
    assertTrue(item.getType().equals(item2.getType()));
    assertTrue(item.getItemId().equals(item2.getItemId()));
  }

  @Test
  public void testUpgradeComponentSerialization() throws IOException {
    log.info("Running UpgradeComponent serializer test");
    UpgradeComponentItem item = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/UpgradeComponentItem.json"),
      UpgradeComponentItem.class
    );

    ObjectWriter writer = this.mapper.writer();
    String json = writer.writeValueAsString(item);

    UpgradeComponentItem item2 = this.mapper.readValue(json, UpgradeComponentItem.class);
    assertTrue(item.getType().equals(item2.getType()));
    assertTrue(item.getUpgradeComponent().getType().equals(item2.getUpgradeComponent().getType()));
  }

  @Test
  public void testWeaponItemSerialization() throws IOException {
    log.info("Running WeaponItem serializer test");
    WeaponItem item = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/WeaponItem.json"),
      WeaponItem.class
    );

    ObjectWriter writer = this.mapper.writer();
    String json = writer.writeValueAsString(item);

    WeaponItem item2 = this.mapper.readValue(json, WeaponItem.class);
    assertTrue(item.getType().equals(item2.getType()));
    assertTrue(item.getWeapon().getType().equals(item2.getWeapon().getType()));
  }
}