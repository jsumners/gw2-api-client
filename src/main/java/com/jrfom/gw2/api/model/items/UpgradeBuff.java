package com.jrfom.gw2.api.model.items;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpgradeBuff {
  @JsonProperty("skill_id")
  private int skillId;
  private String description;

  public UpgradeBuff() {}

  public int getSkillId() {
    return this.skillId;
  }

  public void setSkillId(int skillId) {
    this.skillId = skillId;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}