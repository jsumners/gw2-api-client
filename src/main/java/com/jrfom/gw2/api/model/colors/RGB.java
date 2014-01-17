package com.jrfom.gw2.api.model.colors;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Represents and RGB value as returned by the GW2 API.
 */
public class RGB extends ArrayList<Integer> {
  RGB() {
    this(new ArrayList<Integer>() {{ add(-1); add(-1); add(-1); }});
  }

  RGB(List<Integer> list) {
    this.addAll(0, list);
  }

  /**
   * This method is intended to only be used during deserialization. Using
   * it at any other time will result in undefined behavior.
   *
   * @param value The value to add.
   * @return True, always.
   */
  @Override
  public boolean add(Integer value) {
    int index = this.indexOf(-1);
    if (index != -1) {
      this.set(index, value);
    }

    return true;
  }

  /**
   * This method is meant to be used during initialization. Using it at
   * any other time will result in undefined behavior.
   *
   * @param collection The array to add.
   * @return True? False? Whatever {@link java.util.ArrayList#addAll(int, java.util.Collection)}
   * decides to return.
   */
  @Override
  public boolean addAll(Collection<? extends Integer> collection) {
    return super.addAll(0, collection);
  }

  public int getRed() {
    return this.get(0);
  }

  public void setRed(int red) {
    this.set(0, red);
  }

  public int getGreen() {
    return this.get(1);
  }

  public void setGreen(int green) {
    this.set(1, green);
  }

  public int getBlue() {
    return this.get(2);
  }

  public void setBlue(int blue) {
    this.set(2, blue);
  }
}