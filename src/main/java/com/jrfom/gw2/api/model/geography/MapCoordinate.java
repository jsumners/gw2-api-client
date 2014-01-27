package com.jrfom.gw2.api.model.geography;

import java.util.ArrayList;
import java.util.Collection;

import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents an (x,y) coordinate in a cartesian plane.
 */
@Gw2ApiVersion("v1")
public class MapCoordinate extends ArrayList<Number> {
  public MapCoordinate() {
    this.add(0, Integer.MIN_VALUE);
    this.add(1, Integer.MIN_VALUE);
  }

  public MapCoordinate(ArrayList<? extends Number> list) {
    this.addAll(list);
  }

  /**
   * To be used during deserialization.
   *
   * @param value
   * @return
   */
  public boolean add(Number value) {
    int index = this.indexOf(Integer.MIN_VALUE);
    if (index != -1) {
      this.set(index, value);
    }

    return true;
  }

  public boolean addAll(Collection<? extends Number> collection) {
    return super.addAll(0, collection);
  }

  /**
   * Retrieve the x part of the coordinate.
   *
   * @return An instance of {@link java.lang.Number}.
   */
  public Number getX() {
    return this.get(0);
  }

  public void setX(Number x) {
    this.set(0, x);
  }

  /**
   * Retrieve the y part of the coordinate.
   *
   * @return An instance of {@link java.lang.Number}.
   */
  public Number getY() {
    return this.get(1);
  }

  public void setY(Number y) {
    this.set(1, y);
  }
}