package com.jrfom.gw2.api.model.geography;

import java.util.ArrayList;

import com.google.common.base.Optional;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents a list of {@link com.jrfom.gw2.api.model.geography.MapName}s as
 * returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/map_names">/v1/map_names</a>.
 */
@Gw2ApiVersion("v1")
public class MapNamesList extends ArrayList<MapName> {
  public MapNamesList() {
    super(0);
  }

  /**
   * Retrieve a {@link com.jrfom.gw2.api.model.geography.MapName} from the list
   * that has the given {@code id}.
   *
   * @param id A valid map id.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.MapName}
   * wrapped in an {@link com.google.common.base.Optional} or an empty
   * {@code Optional}.
   */
  public Optional<MapName> getNameWithId(int id) {
    Optional<MapName> result = Optional.absent();

    for (MapName mapName : this) {
      if (mapName.getId() == id) {
        result = Optional.of(mapName);
        break;
      }
    }

    return result;
  }

  /**
   * Retrieve a {@link com.jrfom.gw2.api.model.geography.MapName} from the list
   * that has the given {@code name}.
   *
   * @param name An in-game map name, e.g. "Lion's Arch".
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.MapName}
   * wrapped in an {@link com.google.common.base.Optional} or an empty
   * {@code Optional}.
   */
  public Optional<MapName> getNameWithName(String name) {
    Optional<MapName> result = Optional.absent();

    for (MapName mapName : this) {
      if (mapName.getName().equals(name)) {
        result = Optional.of(mapName);
        break;
      }
    }

    return result;
  }
}