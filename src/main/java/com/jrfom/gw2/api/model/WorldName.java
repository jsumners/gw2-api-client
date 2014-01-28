package com.jrfom.gw2.api.model;

import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents a game world name as returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/world_names">/v1/world_names</a>.
 */
@Gw2ApiVersion("v1")
public class WorldName {
  private int id;
  private String name;

  public WorldName() {}

  /**
   * The unique identifier for the game world.
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
   * The in-game name of the game world, e.g. "Sorrow's Furnace".
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