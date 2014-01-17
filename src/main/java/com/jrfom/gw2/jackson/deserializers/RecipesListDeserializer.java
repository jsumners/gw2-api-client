package com.jrfom.gw2.jackson.deserializers;

import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.RecipesList;

@Gw2ApiVersion("v1")
public class RecipesListDeserializer extends JsonDeserializer<RecipesList> {
  @Override
  public RecipesList deserialize(JsonParser jp, DeserializationContext ctxt)
    throws IOException, JsonProcessingException
  {
    RecipesList recipesList = new RecipesList();
    ObjectCodec codec = jp.getCodec();
    JsonNode node = codec.readTree(jp);
    node = node.get("recipes");

    Iterator<JsonNode> iterator = node.elements();
    while (iterator.hasNext()) {
      JsonNode recipeId = iterator.next();
      recipesList.add(recipeId.intValue());
    }

    return recipesList;
  }
}