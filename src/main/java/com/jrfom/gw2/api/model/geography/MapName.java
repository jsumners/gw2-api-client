package com.jrfom.gw2.api.model.geography;

import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents a map name as returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/map_names">/v1/map_names</a>.
 */
@Gw2ApiVersion("v1")
public class MapName {
  private int id;
  private String name;

  public MapName() {}

  /**
   * The unique identifier for the map.
   *
   * @return An integer identifier.
   */
  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  /**
   * The in-game name of the map. For example, "Lion's Arch".
   *
   * @return A string name.
   */
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}