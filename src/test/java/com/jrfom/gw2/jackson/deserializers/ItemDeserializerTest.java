package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jrfom.gw2.api.model.items.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class ItemDeserializerTest {
  private static final Logger log = LoggerFactory.getLogger(ItemDeserializerTest.class);

  private ObjectMapper mapper = new ObjectMapper();

  @Test
  public void testWeaponDeserialization() throws IOException {
    log.info("Running WeaponItem deserialization test");
    WeaponItem weapon = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/WeaponItem.json"),
      WeaponItem.class
    );

    assertTrue(weapon.getType().equals("Weapon"));
    assertTrue(weapon.getItemId() == 28445);
    assertTrue(weapon.getName().equals("Strong Bow of Fire"));
    assertTrue(weapon.getDescription().equals(""));
    assertTrue(weapon.getLevel() == 44);
    assertTrue(weapon.getRarity().equals("Masterwork"));
    assertTrue(weapon.getVendorValue().silverValue() == 1);
    assertTrue(weapon.getVendorValue().copperValue() == 20);
    assertTrue(weapon.getIconFileId() == 65015);
    assertTrue(weapon.getIconFileSignature().equals("C6110F52DF5AFE0F00A56F9E143E9732176DDDE9"));
    assertTrue(weapon.getGameTypes().size() == 4);
    assertTrue(weapon.getFlags().size() == 1);
    assertTrue(weapon.getRestrictions().size() == 0);
    assertTrue(weapon.getWeapon().getType().equals("LongBow"));
    assertTrue(weapon.getWeapon().getDamageType().equals("Physical"));
    assertTrue(weapon.getWeapon().getMinPower() == 385);
    assertTrue(weapon.getWeapon().getMaxPower() == 452);
    assertTrue(weapon.getWeapon().getDefense() == 0);
    assertTrue(weapon.getWeapon().getInfusionSlots().size() == 0);
    assertTrue(weapon.getWeapon().getInfixUpgrade().getAttributes().size() == 2);
    assertTrue(weapon.getWeapon().getSuffixItemId() == 24547);
  }

  @Test
  public void testArmorDeserialization() throws IOException {
    log.info("Running ArmorItem deserialization test");
    ArmorItem armorItem = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/ArmorItem.json"),
      ArmorItem.class
    );

    assertTrue(armorItem.getType().equals("Armor"));
    assertTrue(armorItem.getItemId() == 100);
    assertTrue(armorItem.getName().equals("Rampager's Acolyte Coat of Divinity"));
    assertTrue(armorItem.getArmor().getType().equals("Coat"));
    assertTrue(armorItem.getArmor().getInfixUpgrade().getAttributes().size() == 3);
  }

  @Test
  public void testConsumableItemDeserialization() throws IOException {
    log.info("Running ConsumableItem deserialization test");
    ConsumableItem consumableItem = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/ConsumableItem.json"),
      ConsumableItem.class
    );

    assertTrue(consumableItem.getType().equals("Consumable"));
    assertTrue(consumableItem.getItemId() == 10000);
    assertTrue(consumableItem.getConsumable().getType().equals("Unlock"));
    assertTrue(consumableItem.getConsumable().getUnlockType().equals("CraftingRecipe"));
    assertTrue(consumableItem.getConsumable().getRecipeId() == 2756);
  }

  @Test
  public void testCraftingMaterialItemDeserialization() throws IOException {
    log.info("Running CraftingMaterialItem deserialization test");
    CraftingMaterialItem item = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/CraftingMaterialItem.json"),
      CraftingMaterialItem.class
    );

    assertTrue(item.getType().equals("CraftingMaterial"));
    assertTrue(item.getItemId() == 13000);
    assertTrue(item.getName().equals("Bronze Trident Head"));
  }

  @Test
  public void testTrinketItemDeserialization() throws IOException {
    log.info("Running TrinketItem deserialization test");
    TrinketItem item = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/TrinketItem.json"),
      TrinketItem.class
    );

    assertTrue(item.getType().equals("Trinket"));
    assertTrue(item.getName().equals("Opal Orichalcum Amulet of the Explorer"));
    assertTrue(item.getItemId() == 13500);
    assertTrue(item.getTrinket().getType().equals("Amulet"));
    assertTrue(item.getTrinket().getInfusionSlots().size() == 0);
    assertTrue(item.getTrinket().getSuffixItemId() == 24545);
  }

  @Test
  public void testBagItemDeserialization() throws IOException {
    log.info("Running BagItem deserialization test");
    BagItem bagItem = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/BagItem.json"),
      BagItem.class
    );

    assertTrue(bagItem.getType().equals("Bag"));
    assertTrue(bagItem.getName().equals("8 Slot Invisible Bag"));
    assertTrue(bagItem.getItemId() == 9480);
    assertTrue(bagItem.getBag().willSellOrSort() == false);
    assertTrue(bagItem.getBag().getSize() == 8);
  }

  @Test
  public void testContainerItemDeserialization() throws IOException {
    log.info("Running ContainerItem deserialization test");
    ContainerItem item = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/ContainerItem.json"),
      ContainerItem.class
    );

    assertTrue(item.getType().equals("Container"));
    assertTrue(item.getItemId() == 36520);
    assertTrue(item.getContainer().getType().equals("Default"));
  }

  @Test
  public void testGizmoItemDeserialization() throws IOException {
    log.info("Running GizmoItem deserialization test");
    GizmoItem item = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/GizmoItem.json"),
      GizmoItem.class
    );

    assertTrue(item.getType().equals("Gizmo"));
    assertTrue(item.getItemId() == 22335);
    assertTrue(item.getGizmo().getType().equals("Default"));
  }

  @Test
  public void testMiniPetItemDeserialization() throws IOException {
    log.info("Running MiniPetItem deserialization test");
    MiniPetItem item = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/MiniPetItem.json"),
      MiniPetItem.class
    );

    assertTrue(item.getType().equals("MiniPet"));
    assertTrue(item.getItemId() == 20211);
    assertTrue(item.getName().equals("Mini Black Moa"));
  }

  @Test
  public void testTrophyItemDeserialization() throws IOException {
    log.info("Running TrophyItem deserialization test");
    TrophyItem item = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/TrophyItem.json"),
      TrophyItem.class
    );

    assertTrue(item.getType().equals("Trophy"));
    assertTrue(item.getItemId() == 43555);
    assertTrue(item.getName().equals("Aetherized Metal Scrap"));
  }

  @Test
  public void testUpgradeComponentItemDeserialization() throws IOException {
    log.info("Running UpgradeComponentItem deserialization test");
    UpgradeComponentItem item = this.mapper.readValue(
      this.getClass().getResourceAsStream("/json/UpgradeComponentItem.json"),
      UpgradeComponentItem.class
    );

    assertTrue(item.getType().equals("UpgradeComponent"));
    assertTrue(item.getItemId() == 49424);
    assertTrue(item.getUpgradeComponent().getType().equals("Default"));
    assertTrue(item.getUpgradeComponent().getFlags().size() == 23);
    assertTrue(item.getUpgradeComponent().getInfusionUpgradeFlags().size() == 0);
    assertTrue(item.getUpgradeComponent().getInfixUpgrade().getBuff().getSkillId() == 22100);
    assertTrue(item.getUpgradeComponent().getSuffix().equals(""));
  }
}