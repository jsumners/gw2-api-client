package com.jrfom.gw2.api.model.geography;

import java.util.ArrayList;
import java.util.Collection;

import com.jrfom.gw2.annotations.Gw2ApiVersion;

@Gw2ApiVersion("v1")
public class MapCoordinate extends ArrayList<Number> {
  public MapCoordinate() {
    this.add(0, Integer.MIN_VALUE);
    this.add(1, Integer.MIN_VALUE);
  }

  public MapCoordinate(ArrayList<? extends Number> list) {
    this.addAll(list);
  }

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

  public Number getX() {
    return this.get(0);
  }

  public void setX(Number x) {
    this.set(0, x);
  }

  public Number getY() {
    return this.get(1);
  }

  public void setY(Number y) {
    this.set(1, y);
  }
}