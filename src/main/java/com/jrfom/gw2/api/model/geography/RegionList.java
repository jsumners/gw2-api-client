package com.jrfom.gw2.api.model.geography;

import java.util.HashMap;

import com.google.common.base.Optional;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

@Gw2ApiVersion("v1")
public class RegionList extends HashMap<String, Region> {
  public RegionList() {}

  public Optional<Region> getRegionWithId(int id) {
    return this.getRegionWithId("" + id);
  }

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