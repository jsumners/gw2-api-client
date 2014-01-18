package com.jrfom.gw2.api.model.crafting;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class RecipeTest {
  private static final Logger log = LoggerFactory.getLogger(RecipeTest.class);

  @Test
  public void testDeserialization() throws IOException {
    log.info("Running Recipe deserialization test");
    ObjectMapper mapper = new ObjectMapper();
    Recipe recipe = mapper.readValue(
      this.getClass().getResourceAsStream("/json/Recipe.json"),
      Recipe.class
    );

    assertTrue(recipe.getRecipeId() == 1275);
    assertTrue(recipe.getType().equals("Coat"));
    assertTrue(recipe.getOutputItemId() == 11541);
    assertTrue(recipe.getOutputItemCount() == 1);
    assertTrue(recipe.getMinRating() == 25);
    assertTrue(recipe.getTimeToCraft() == 1000);
    assertTrue(recipe.getVendorValue().getValue() == 13000);
    assertTrue(recipe.getVendorValue().goldValue() == 1);
    assertTrue(recipe.getDisciplines().size() == 1);
    assertTrue(recipe.getDisciplines().get(0).equals("Leatherworker"));
    assertTrue(recipe.getFlags().size() == 0);
    assertTrue(recipe.getIngredients().size() == 3);
    assertTrue(recipe.getIngredients().get(0).getItemId() == 19797);
    assertTrue(recipe.getIngredients().get(0).getCount() == 1);
  }

  @Test
  public void testSerialization() throws IOException {
    log.info("Running Recipe serialization test");
    ObjectMapper mapper = new ObjectMapper();
    Recipe recipe = mapper.readValue(
      this.getClass().getResourceAsStream("/json/Recipe.json"),
      Recipe.class
    );

    ObjectWriter writer = mapper.writer();
    String json = writer.writeValueAsString(recipe);

    Recipe recipe2 = mapper.readValue(json, Recipe.class);
    assertTrue(recipe.getRecipeId() == recipe2.getRecipeId());
    assertTrue(recipe.getType().equals(recipe2.getType()));
  }
}