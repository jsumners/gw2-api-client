package com.jrfom.gw2.jackson.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.jrfom.gw2.api.model.RecipesList;

public class RecipesListSerializer extends JsonSerializer<RecipesList> {
  @Override
  public void serialize(RecipesList value, JsonGenerator jgen, SerializerProvider provider)
    throws IOException, JsonProcessingException
  {
    jgen.writeStartObject();

    jgen.writeArrayFieldStart("recipes");
    for (Integer recipeId : value) {
      jgen.writeNumber(recipeId);
    }
    jgen.writeEndArray();

    jgen.writeEndObject();
  }
}