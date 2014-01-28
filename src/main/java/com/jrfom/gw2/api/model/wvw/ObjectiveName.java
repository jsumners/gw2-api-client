package com.jrfom.gw2.api.model.wvw;

import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents a World vs World {@link com.jrfom.gw2.api.model.wvw.Objective}
 * name as is returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/wvw/objective_names">/v1/wvw/objective_names</a>.
 */
@Gw2ApiVersion("v1")
public class ObjectiveName {
  private int id;
  private String name;

  public ObjectiveName() {}

  /**
   * The unique identifier for the {@link com.jrfom.gw2.api.model.wvw.Objective}.
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
   * The name of the {@link com.jrfom.gw2.api.model.wvw.Objective}. Really, the
   * type of objective as this is not show in-game.
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