package com.jrfom.gw2.api.model.items;

import java.util.List;

/**
 * Represents an infusion slot on an {@link com.jrfom.gw2.api.model.items.Item}.
 * An infusion slot is represented by an array of flags that indicate the type
 * of infusions possible in that slot.</p>
 *
 * <p>The possible flags for each infusion slot are:</p>
 *
 * <ul>
 *   <li>Defense</li>
 *   <li>Offense</li>
 *   <li>Utility</li>
 * </ul>
 */
public class InfusionSlot {
  private List<String> flags;

  public InfusionSlot() {}

  public List<String> getFlags() {
    return this.flags;
  }

  public void setFlags(List<String> flags) {
    this.flags = flags;
  }
}