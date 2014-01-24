package com.jrfom.gw2.api.model.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents item properties specific to
 * {@link com.jrfom.gw2.api.model.items.BagItem}s.
 */
@Gw2ApiVersion("v1")
public class BagItemProperties {
  @JsonProperty("no_sell_or_sort")
  private String sellOrSort;
  private int size;

  public BagItemProperties() {}

  /**
   * Indicates if the bag will participate in the selling or sorting actions.
   *
   * @return False if it will not participate, true otherwise.
   */
  public boolean willSellOrSort() {
    // Fucking retarded GW2 API.
    return Boolean.valueOf(this.sellOrSort);
  }

  public void setSellOrSort(String sellOrSort) {
    this.sellOrSort = sellOrSort;
  }

  /**
   * The number of slots available in the bag.
   *
   * @return A integer representing the number of slots in the bag.
   */
  public int getSize() {
    return this.size;
  }

  public void setSize(int size) {
    this.size = size;
  }
}