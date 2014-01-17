package com.jrfom.gw2.api.model.geography;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Optional;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.jackson.deserializers.MapsListDeserializer;
import com.jrfom.gw2.jackson.serializers.MapsListSerializer;

@JsonDeserialize(using = MapsListDeserializer.class)
@JsonSerialize(using = MapsListSerializer.class)
@Gw2ApiVersion("v1")
public class MapsList extends ArrayList<Map> {
  public MapsList() {}

  public MapsList(int capacity) {
    super(capacity);
  }

  public Optional<Map> getMapWithId(int id) {
    Optional<Map> result = Optional.absent();

    for (Map map : this) {
      if (map.getMapId() == id) {
        result = Optional.of(map);
      }
    }

    return result;
  }

  public Optional<Map> getMapWithName(String name) {
    Optional<Map> result = Optional.absent();

    for (Map map : this) {
      if (map.getMapName().equals(name)) {
        result = Optional.of(map);
      }
    }

    return result;
  }
}