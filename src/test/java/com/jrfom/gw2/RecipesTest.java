package com.jrfom.gw2;

import java.io.IOException;

import com.google.common.base.Optional;
import com.jrfom.gw2.api.model.GwApiError;
import com.jrfom.gw2.api.model.crafting.Recipe;
import com.jrfom.gw2.api.model.crafting.RecipesList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class RecipesTest extends BaseApiClientTestClass {
  private static final Logger log = LoggerFactory.getLogger(RecipesTest.class);

  @Test
  public void testGetRecipes() throws IOException {
    log.info("Running ApiClient.getRecipes() test");
    String expectedResponse = this.loadExpectedResponse("/json/Recipes.json");
    this.setupMockServerSuccess("recipes.json", expectedResponse);

    RecipesList list = this.apiClient.getRecipes();
    assertTrue(list.size() == 5);
    assertTrue(list.get(0) == 1275);
  }

  @Test
  public void testGetRecipe() throws IOException {
    log.info("Running ApiClient.getRecipe(recipeId) test");
    String expectedResponse = this.loadExpectedResponse("/json/Recipe.json");
    this.setupMockServerSuccess("recipe_details.json?recipe_id=1275", expectedResponse);

    Optional<Recipe> result = this.apiClient.getRecipe(1275);
    assertTrue(result.isPresent());

    Recipe recipe = result.get();
    assertTrue(recipe.getRecipeId() == 1275);
    assertTrue(recipe.getType().equals("Coat"));
  }

  @Test
  public void testGetRecipeInLang() throws IOException {
    log.info("Running ApiClient.getRecipeInLang(recipeId, lang) test");
    String expectedResponse = this.loadExpectedResponse("/json/Recipe.json");
    this.setupMockServerSuccess(
      "recipe_details.json?recipe_id=1275&lang=en",
      expectedResponse
    );

    Optional<Recipe> result = this.apiClient.getRecipeInLang(1275, "en");
    assertTrue(result.isPresent());

    Recipe recipe = result.get();
    assertTrue(recipe.getRecipeId() == 1275);
    assertTrue(recipe.getType().equals("Coat"));
  }

  @Test
  public void testGetRecipeFail() throws IOException {
    log.info("Running ApiClient.getRecipe(recipeId) failure test");
    String expectedResponse = this.loadExpectedResponse("/json/errors/InvalidRecipe.json");
    this.setupMockServerFail("recipe_details.json?recipe_id=-1", expectedResponse);

    try {
      Optional<Recipe> result = this.apiClient.getRecipe(-1);
    } catch (GwApiError e) {
      assertTrue(e.getError() == 10);
      assertTrue(e.getProduct() == 0);
      assertTrue(e.getModule() == 2);
      assertTrue(e.getLine() == 440);
      assertTrue(e.getText().equals("invalid recipe_id"));
    }
  }
}