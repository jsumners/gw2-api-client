package com.jrfom.gw2.api.model.events;

import java.util.ArrayList;

import com.google.common.base.Optional;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

@Gw2ApiVersion("v1")
public class WorldNamesList extends ArrayList<WorldName> {
  public WorldNamesList() {
    super(0);
  }

  public Optional<WorldName> getWorldNameWithId(int id) {
    Optional<WorldName> result = Optional.absent();

    for (WorldName worldName : this) {
      if (worldName.getId() == id) {
        result = Optional.of(worldName);
        break;
      }
    }

    return result;
  }

  public Optional<WorldName> getWorldNameWithName(String name) {
    Optional<WorldName> result = Optional.absent();

    for (WorldName worldName : this) {
      if (worldName.getName().equals(name)) {
        result = Optional.of(worldName);
        break;
      }
    }

    return result;
  }
}