package com.jrfom.gw2.api.model;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Represents a WIDTHxHEIGHT measurement.
 */
public class Dimension extends ArrayList<Integer> {
  public Dimension() {
    this.add(0, Integer.MIN_VALUE);
    this.add(1, Integer.MIN_VALUE);
  }

  public Dimension(int capacity) {
    super(capacity);
  }

  public Dimension(ArrayList<Integer> list) {
    this.addAll(list);
  }

  /**
   * To be used during deserialization.
   *
   * @param value
   * @return
   */
  public boolean add(Integer value) {
    int index = this.indexOf(Integer.MIN_VALUE);
    if (index != -1) {
      this.set(index, value);
    }
    return true;
  }

  public boolean addAll(Collection<? extends Integer> collection) {
    return super.addAll(0, collection);
  }

  /**
   * Retrieve the height portion of the measurement.
   *
   * @return An integer representing the height.
   */
  public int getHeight() {
    return this.get(0);
  }

  public void setHeight(int height) {
    this.set(0, height);
  }

  /**
   * Retrieve the width portion of the measurement.
   *
   * @return An integer representing the width.
   */
  public int getWidth() {
    return this.get(1);
  }

  public void setWidth(int width) {
    this.set(1, width);
  }
}