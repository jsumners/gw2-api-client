package com.jrfom.gw2.api.model.items;

public class UpgradeAttribute {
  private String attribute;
  private int modifier;

  public UpgradeAttribute() {}

  public String getAttribute() {
    return this.attribute;
  }

  public void setAttribute(String attribute) {
    this.attribute = attribute;
  }

  public int getModifier() {
    return this.modifier;
  }

  public void setModifier(int modifier) {
    this.modifier = modifier;
  }
}