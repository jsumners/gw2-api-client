package com.jrfom.gw2.api.model.geography;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.Dimension;

/**
 * Represents the details of an in-game continent.
 */
@Gw2ApiVersion("v1")
public class Continent {
  private String name;
  @JsonProperty("continent_dims")
  private Dimension dimensions;
  @JsonProperty("min_zoom")
  private int minZoom;
  @JsonProperty("max_zoom")
  private int maxZoom;
  private ArrayList<Integer> floors;

  public Continent() {}

  /**
   * The in-game name of the continent. For example, "Tyria".
   * @return
   */
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * The WIDTHxHEIGHT of the continent.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.Dimension}.
   */
  public Dimension getDimensions() {
    return this.dimensions;
  }

  public void setDimensions(Dimension dimensions) {
    this.dimensions = dimensions;
  }

  /**
   * The minimum zoom level of the continent on the world map.
   *
   * @return An integer zoom level.
   */
  public int getMinZoom() {
    return this.minZoom;
  }

  public void setMinZoom(int minZoom) {
    this.minZoom = minZoom;
  }

  /**
   * The maximum zoom level of the continent on the world map.
   *
   * @return An integer zoom level.
   */
  public int getMaxZoom() {
    return this.maxZoom;
  }

  public void setMaxZoom(int maxZoom) {
    this.maxZoom = maxZoom;
  }

  /**
   * A list of all the {@link com.jrfom.gw2.api.model.geography.Floor}
   * identifiers contained within the continent.
   *
   * @return An array of floor identifiers.
   */
  public ArrayList<Integer> getFloors() {
    return this.floors;
  }

  public void setFloors(ArrayList<Integer> floors) {
    this.floors = floors;
  }
}