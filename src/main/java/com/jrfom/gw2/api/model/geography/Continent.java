package com.jrfom.gw2.api.model.geography;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.Dimension;

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

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Dimension getDimensions() {
    return this.dimensions;
  }

  public void setDimensions(Dimension dimensions) {
    this.dimensions = dimensions;
  }

  public int getMinZoom() {
    return this.minZoom;
  }

  public void setMinZoom(int minZoom) {
    this.minZoom = minZoom;
  }

  public int getMaxZoom() {
    return this.maxZoom;
  }

  public void setMaxZoom(int maxZoom) {
    this.maxZoom = maxZoom;
  }

  public ArrayList<Integer> getFloors() {
    return this.floors;
  }

  public void setFloors(ArrayList<Integer> floors) {
    this.floors = floors;
  }
}