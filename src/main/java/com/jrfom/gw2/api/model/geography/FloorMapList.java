package com.jrfom.gw2.api.model.geography;

import java.util.HashMap;

import com.google.common.base.Optional;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

@Gw2ApiVersion("v1")
public class FloorMapList extends HashMap<String, FloorMap> {
  public FloorMapList() {}

  public Optional<FloorMap> getMapWithId(int id) {
    return this.getMapWithId("" + id);
  }

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