package com.jrfom.gw2.api.model.geography;

import java.util.HashMap;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Optional;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.jackson.deserializers.ContinentsDeserializer;
import com.jrfom.gw2.jackson.serializers.ContinentsSerializer;

/**
 * Represents a list of {@link com.jrfom.gw2.api.model.geography.Continent}s
 * as returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/continents">/v1/continents</a>.
 */
@JsonDeserialize(using = ContinentsDeserializer.class)
@JsonSerialize(using = ContinentsSerializer.class)
@Gw2ApiVersion("v1")
public class Continents extends HashMap<String, Continent> {
  public Continents() {}

  /**
   * Retrieve a continent from the list identified by its numeric id.
   *
   * @param id The integer id for the continent to retrieve.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.Continent}
   * wrapped in an {@link com.google.common.base.Optional} or an empty
   * {@code Optional}.
   */
  public Optional<Continent> getContinentWithId(int id) {
    return this.getContinentWithId("" + id);
  }

  /**
   * Retrieve a continent from the list identified by its numeric id
   * in string form.
   *
   * @param id The integer id as a string.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.Continent}
   * wrapped in an {@link com.google.common.base.Optional} or an empty
   * {@code Optional}.
   */
  public Optional<Continent> getContinentWithId(String id) {
    Optional<Continent> result = Optional.absent();
    Continent continent = this.get("" + id);

    if (continent != null) {
      result = Optional.of(continent);
    }

    return result;
  }

  /**
   * Retrieve a continent from the list that has the given {@code name}.
   *
   * @param name The in-game name of the continent to retrieve.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.Continent}
   * wrapped in an {@link com.google.common.base.Optional} or an empty
   * {@code Optional}.
   */
  public Optional<Continent> getContinentWithName(String name) {
    Optional<Continent> result = Optional.absent();

    for (Continent continent : this.values()) {
      if (continent.getName().equals(name)) {
        result = Optional.of(continent);
        break;
      }
    }

    return result;
  }
}