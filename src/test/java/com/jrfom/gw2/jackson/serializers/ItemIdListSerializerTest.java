package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jrfom.gw2.api.model.items.ItemIdList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class ItemIdListSerializerTest {
  private static final Logger log = LoggerFactory.getLogger(ItemIdListSerializerTest.class);

  @Test
  public void test() throws IOException {
    log.info("Running ItemIdList custom serializer test");
    ObjectMapper mapper = new ObjectMapper();
    ItemIdList itemIdList = mapper.readValue(
      this.getClass().getResourceAsStream("/json/Items.json"),
      ItemIdList.class
    );

    ObjectWriter writer = mapper.writer();
    String json = writer.writeValueAsString(itemIdList);

    ItemIdList itemIdList2 = mapper.readValue(json, ItemIdList.class);
    assertTrue(itemIdList.size() == itemIdList2.size());

    for (int i = 0, j = itemIdList.size(); i < j; i += 1) {
      assertTrue(itemIdList.get(i).intValue() == itemIdList2.get(i).intValue());
    }
  }
}