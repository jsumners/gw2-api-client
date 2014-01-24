package com.jrfom.gw2.api.model.items;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents an {@link com.jrfom.gw2.api.model.items.InfixUpgrade} buff
 * property. A buff modifies a skill.
 */
@Gw2ApiVersion("v1")
public class UpgradeBuff {
  @JsonProperty("skill_id")
  private int skillId;
  private String description;

  public UpgradeBuff() {}

  /**
   * The identifier for the skill that the buff modifies.
   *
   * @return An integer skill identifier.
   */
  public int getSkillId() {
    return this.skillId;
  }

  public void setSkillId(int skillId) {
    this.skillId = skillId;
  }

  /**
   * A description of the buff, e.g. "+1 Agony Resistance".
   *
   * @return The buff description.
   */
  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}