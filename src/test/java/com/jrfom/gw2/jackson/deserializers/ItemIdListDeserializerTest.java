package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jrfom.gw2.api.model.items.ItemIdList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class ItemIdListDeserializerTest {
  private static final Logger log = LoggerFactory.getLogger(ItemIdListDeserializerTest.class);

  @Test
  public void test() throws IOException {
    log.info("Running ItemIdList custom Jackson deserializer test");
    ItemIdListDeserializer deserializer = new ItemIdListDeserializer();
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule simpleModule = new SimpleModule(
      "my_module",
      new Version(1, 0, 0, null, "com.jrfom", "foo")
    );
    simpleModule.addDeserializer(ItemIdList.class, deserializer);
    mapper.registerModule(simpleModule);

    ItemIdList itemIdList = mapper.readValue(
      this.getClass().getResourceAsStream("/json/Items.json"),
      ItemIdList.class
    );

    assertTrue(itemIdList.size() == 5);
    assertTrue(itemIdList.get(0).intValue() == 12546);
    assertTrue(itemIdList.get(1).intValue() == 38875);
    assertTrue(itemIdList.get(2).intValue() == 26706);
    assertTrue(itemIdList.get(3).intValue() == 28445);
    assertTrue(itemIdList.get(4).intValue() == 34460);
  }
}