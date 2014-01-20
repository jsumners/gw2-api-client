package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    ObjectMapper mapper = new ObjectMapper();
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