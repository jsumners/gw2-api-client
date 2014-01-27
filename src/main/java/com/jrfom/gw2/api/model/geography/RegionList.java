package com.jrfom.gw2.api.model.geography;

import java.util.HashMap;

import com.google.common.base.Optional;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents a list of {@link com.jrfom.gw2.api.model.geography.Region}s as
 * used by {@link com.jrfom.gw2.api.model.geography.FloorMap}s.
 */
@Gw2ApiVersion("v1")
public class RegionList extends HashMap<String, Region> {
  public RegionList() {}

  /**
   * Retrieve a region with a specific identifier.
   *
   * @param id The unique identfier of a
   * {@link com.jrfom.gw2.api.model.geography.Region} to retrieve.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.Region}
   * wrapped in an {@link com.google.common.base.Optional} or an empty
   * {@code Optional}.
   */
  public Optional<Region> getRegionWithId(int id) {
    return this.getRegionWithId("" + id);
  }

  /**
   * Retrieve a region with a specific identifier.
   *
   * @param id The unique identfier of a
   * {@link com.jrfom.gw2.api.model.geography.Region}, as a string, to retrieve.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.Region}
   * wrapped in an {@link com.google.common.base.Optional} or an empty
   * {@code Optional}.
   */
  public Optional<Region> getRegionWithId(String id) {
    Optional<Region> result = Optional.absent();

    for (String key : this.keySet()) {
      if (key.equals(id)) {
        result = Optional.of(this.get(key));
        break;
      }
    }

    return result;
  }
}