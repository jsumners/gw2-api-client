package com.jrfom.gw2.api.model.geography;

import java.util.HashMap;

import com.google.common.base.Optional;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents a list of {@link com.jrfom.gw2.api.model.geography.FloorMap}s as
 * used by {@link com.jrfom.gw2.api.model.geography.Region}.
 */
@Gw2ApiVersion("v1")
public class FloorMapList extends HashMap<String, FloorMap> {
  public FloorMapList() {}

  /**
   * Retrieve a specific floor map given the map's {@code id}.
   *
   * @param id A unique identifier for a
   *           {@link com.jrfom.gw2.api.model.geography.FloorMap}.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.FloorMap}
   * wrapped in an {@link com.google.common.base.Optional} or an empty
   * {@code Optional}.
   */
  public Optional<FloorMap> getMapWithId(int id) {
    return this.getMapWithId("" + id);
  }

  /**
   * Retrieve a specific floor map given the map's {@code id}.
   *
   * @param id A unique identifier, as a string, for a
   *           {@link com.jrfom.gw2.api.model.geography.FloorMap}.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.FloorMap}
   * wrapped in an {@link com.google.common.base.Optional} or an empty
   * {@code Optional}.
   */
  public Optional<FloorMap> getMapWithId(String id) {
    Optional<FloorMap> result = Optional.absent();

    for (String key : this.keySet()) {
      if (key.equals(id)) {
        result = Optional.of(this.get(key));
        break;
      }
    }

    return result;
  }
}