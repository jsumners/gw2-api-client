package com.jrfom.gw2.api.model.geography;

import java.util.HashMap;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Optional;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.jackson.deserializers.ContinentsDeserializer;
import com.jrfom.gw2.jackson.serializers.ContinentsSerializer;

@JsonDeserialize(using = ContinentsDeserializer.class)
@JsonSerialize(using = ContinentsSerializer.class)
@Gw2ApiVersion("v1")
public class Continents extends HashMap<String, Continent> {
  public Continents() {}

  public Optional<Continent> getContinentWithId(int id) {
    return this.getContinentWithId("" + id);
  }

  public Optional<Continent> getContinentWithId(String id) {
    Optional<Continent> result = Optional.absent();
    Continent continent = this.get("" + id);

    if (continent != null) {
      result = Optional.of(continent);
    }

    return result;
  }

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