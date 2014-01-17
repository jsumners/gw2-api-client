package com.jrfom.gw2.api.model.items;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class InfixUpgrade {
  private ArrayList<UpgradeAttribute> attributes;
  private UpgradeBuff buff;

  public InfixUpgrade() {}

  public ArrayList<UpgradeAttribute> getAttributes() {
    return this.attributes;
  }

  public void setAttributes(ArrayList<UpgradeAttribute> attributes) {
    this.attributes = attributes;
  }

  public UpgradeBuff getBuff() {
    return this.buff;
  }

  public void setBuff(UpgradeBuff buff) {
    this.buff = buff;
  }
}