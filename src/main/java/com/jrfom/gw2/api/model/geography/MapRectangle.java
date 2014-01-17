package com.jrfom.gw2.api.model.geography;

import java.util.ArrayList;
import java.util.Collection;

import com.jrfom.gw2.annotations.Gw2ApiVersion;

@Gw2ApiVersion("v1")
public class MapRectangle extends ArrayList<MapCoordinate> {
  MapRectangle() {
    this.add(0, null);
    this.add(0, null);
  }

  MapRectangle(ArrayList<MapCoordinate> list) {
    this.addAll(0, list);
  }

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

  public MapCoordinate getUpperLeft() {
    return this.get(0);
  }

  public MapCoordinate getLowerRight() {
    return this.get(1);
  }
}