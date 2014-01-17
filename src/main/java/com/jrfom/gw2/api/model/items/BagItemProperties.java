package com.jrfom.gw2.api.model.items;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BagItemProperties {
  @JsonProperty("no_sell_or_sort")
  private String sellOrSort;
  private int size;

  public BagItemProperties() {}

  public boolean willSellOrSort() {
    // Fucking retarded GW2 API.
    return Boolean.valueOf(this.sellOrSort);
  }

  public void setSellOrSort(String sellOrSort) {
    this.sellOrSort = sellOrSort;
  }

  public int getSize() {
    return this.size;
  }

  public void setSize(int size) {
    this.size = size;
  }
}