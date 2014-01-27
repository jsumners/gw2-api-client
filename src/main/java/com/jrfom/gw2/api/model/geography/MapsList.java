package com.jrfom.gw2.api.model.geography;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Optional;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.jackson.deserializers.MapsListDeserializer;
import com.jrfom.gw2.jackson.serializers.MapsListSerializer;

/**
 * Represents a list of {@link com.jrfom.gw2.api.model.geography.Map}s as
 * returned by <a href="http://wiki.guildwars2.com/wiki/API:1/maps">/v1/maps</a>.
 */
@JsonDeserialize(using = MapsListDeserializer.class)
@JsonSerialize(using = MapsListSerializer.class)
@Gw2ApiVersion("v1")
public class MapsList extends ArrayList<Map> {
  public MapsList() {}

  public MapsList(int capacity) {
    super(capacity);
  }

  /**
   * Retrieve a {@link com.jrfom.gw2.api.model.geography.Map} from the list
   * that has the specified {@code id}.
   *
   * @param id A valid map identifier.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.Map}
   * wrapped in an {@link com.google.common.base.Optional} or an empty
   * {@code Optional}.
   */
  public Optional<Map> getMapWithId(int id) {
    Optional<Map> result = Optional.absent();

    for (Map map : this) {
      if (map.getMapId() == id) {
        result = Optional.of(map);
      }
    }

    return result;
  }

  /**
   * Retrieve a {@link com.jrfom.gw2.api.model.geography.Map} from the list
   * that has the specified {@code name}.
   *
   * @param name A valid map name.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.Map}
   * wrapped in an {@link com.google.common.base.Optional} or an empty
   * {@code Optional}.
   */
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