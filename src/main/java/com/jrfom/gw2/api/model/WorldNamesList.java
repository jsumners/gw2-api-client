package com.jrfom.gw2.api.model;

import java.util.ArrayList;

import com.google.common.base.Optional;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents a list of game {@link com.jrfom.gw2.api.model.WorldName}s as
 * returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/world_names">/v1/world_names</a>.
 */
@Gw2ApiVersion("v1")
public class WorldNamesList extends ArrayList<WorldName> {
  public WorldNamesList() {
    super(0);
  }

  /**
   * Retrieve a world name by its unique identifier.
   *
   * @param id A world name identifier.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.WorldName} wrapped
   * in an {@link com.google.common.base.Optional} or an emtpy {@code Optional}.
   */
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

  /**
   * Retrieve a world name by its in-game name.
   *
   * @param name A world's in-game name.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.WorldName} wrapped
   * in an {@link com.google.common.base.Optional} or an emtpy {@code Optional}.
   */
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