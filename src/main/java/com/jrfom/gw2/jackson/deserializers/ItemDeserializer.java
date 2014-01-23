package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.items.*;

@Gw2ApiVersion("v1")
public class ItemDeserializer extends JsonDeserializer<GenericItem> {
  private HashMap<String, Class<? extends GenericItem>> classRegistry;

  public ItemDeserializer() {
    this.classRegistry = new HashMap<>(11);
    this.classRegistry.put("armor", ArmorItem.class);
    this.classRegistry.put("bag", BagItem.class);
    this.classRegistry.put("consumable", ConsumableItem.class);
    this.classRegistry.put("container", ContainerItem.class);
    this.classRegistry.put("craftingmaterial", CraftingMaterialItem.class);
    this.classRegistry.put("gizmo", GizmoItem.class);
    this.classRegistry.put("minipet", MiniPetItem.class);
    this.classRegistry.put("trinket", TrinketItem.class);
    this.classRegistry.put("trophy", TrophyItem.class);
    this.classRegistry.put("upgradecomponent", UpgradeComponentItem.class);
    this.classRegistry.put("weapon", WeaponItem.class);
  }

  @Override
  public GenericItem deserialize(JsonParser jp, DeserializationContext ctxt)
    throws IOException, JsonProcessingException
  {
    ObjectCodec codec = jp.getCodec();
    JsonNode node = codec.readTree(jp);
    String itemType = node.get("type").textValue().toLowerCase();

    return codec.treeToValue(node, this.classRegistry.get(itemType));
  }
}