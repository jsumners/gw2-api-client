package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.jrfom.gw2.api.model.crafting.RecipesList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class RecipesListDeserializerTest {
  private static final Logger log = LoggerFactory.getLogger(RecipesListDeserializerTest.class);

  @Test
  public void test() throws IOException {
    log.info("Running RecipesList deserializer test");
    RecipesListDeserializer deserializer = new RecipesListDeserializer();
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule simpleModule = new SimpleModule(
      "my_module",
      new Version(1, 0, 0, null, "com.jrfom", "foo")
    );
    simpleModule.addDeserializer(RecipesList.class, deserializer);
    mapper.registerModule(simpleModule);

    RecipesList recipesList = mapper.readValue(
      this.getClass().getResourceAsStream("/json/Recipes.json"),
      RecipesList.class
    );
    assertTrue(recipesList.get(0) == 1275);
    assertTrue(recipesList.get(1) == 3147);
    assertTrue(recipesList.get(2) == 959);
    assertTrue(recipesList.get(3) == 3219);
    assertTrue(recipesList.get(4) == 3721);
  }
}