package com.jrfom.gw2.api.model.geography;

import java.util.ArrayList;
import java.util.Collection;

import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents a set of {@link com.jrfom.gw2.api.model.geography.MapCoordinate}s.
 */
@Gw2ApiVersion("v1")
public class MapRectangle extends ArrayList<MapCoordinate> {
  MapRectangle() {
    this.add(0, null);
    this.add(0, null);
  }

  MapRectangle(ArrayList<MapCoordinate> list) {
    this.addAll(0, list);
  }

  /**
   * To be used during deserialization.
   *
   * @param coordinate
   * @return
   */
  public boolean add(MapCoordinate coordinate) {
    int index = this.indexOf(null);
    if (index != -1) {
      this.set(index, coordinate);
    }

    return true;
  }

  public boolean addAll(Collection<? extends MapCoordinate> collection) {
    return super.addAll(0, collection);
  }

  /**
   * Retrieve the coordinate of the upper left corner of a rectangle.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.MapCoordinate}.
   */
  public MapCoordinate getUpperLeft() {
    return this.get(0);
  }

  /**
   * Retrieve the coordinate of the lower right corner of a rectangle.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.MapCoordinate}.
   */
  public MapCoordinate getLowerRight() {
    return this.get(1);
  }
}