package com.jrfom.gw2.api.model.crafting;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.jackson.deserializers.RecipesListDeserializer;
import com.jrfom.gw2.jackson.serializers.RecipesListSerializer;

/**
 * Represents a list of {@link com.jrfom.gw2.api.model.crafting.Recipe}
 * identifiers as is returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/recipes">/v1/recipes</a>.
 */
@JsonDeserialize(using = RecipesListDeserializer.class)
@JsonSerialize(using = RecipesListSerializer.class)
@Gw2ApiVersion("v1")
public class RecipesList extends ArrayList<Integer> {
}