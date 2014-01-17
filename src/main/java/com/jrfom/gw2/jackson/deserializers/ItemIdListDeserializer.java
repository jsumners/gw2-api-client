package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.jrfom.gw2.api.model.items.ItemIdList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ItemIdListDeserializer extends JsonDeserializer<ItemIdList> {
  private static final Logger log = LoggerFactory.getLogger(ItemIdListDeserializer.class);
  @Override
  public ItemIdList deserialize(JsonParser jp, DeserializationContext ctxt)
    throws IOException, JsonProcessingException
  {
    ItemIdList itemIdList = new ItemIdList();
    ObjectCodec codec = jp.getCodec();
    JsonNode node = codec.readTree(jp);
    node = node.get("items");

    Iterator<JsonNode> iterator = node.elements();
    while (iterator.hasNext()) {
      JsonNode itemNumberNode = iterator.next();
      Number itemNumber = itemNumberNode.numberValue();
      itemIdList.add(itemNumber);
    }

    return itemIdList;
  }
}