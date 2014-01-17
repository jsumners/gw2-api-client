package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.jrfom.gw2.api.model.RecipesList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class RecipesListSerializerTest {
  private static final Logger log = LoggerFactory.getLogger(RecipesListSerializerTest.class);

  @Test
  public void test() throws IOException {
    log.info("Running RecipesList serializer test");
    ObjectMapper mapper = new ObjectMapper();
    RecipesList list = mapper.readValue(
      this.getClass().getResourceAsStream("/json/Recipes.json"),
      RecipesList.class
    );

    ObjectWriter writer = mapper.writer();
    String json = writer.writeValueAsString(list);

    RecipesList list2 = mapper.readValue(json, RecipesList.class);
    for (int i = 0, j = list.size(); i < j; i += 1) {
      assertTrue(list.get(i).equals(list2.get(i)));
    }
  }
}