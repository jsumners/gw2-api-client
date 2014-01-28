package com.jrfom.gw2.api.model.wvw;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.jackson.deserializers.WvwBonusDeserializer;

/**
 * Represents a World vs World bonus.
 */
@JsonSubTypes({
  @JsonSubTypes.Type(BloodlustBonus.class)
})
@JsonDeserialize(using = WvwBonusDeserializer.class)
@Gw2ApiVersion("v1")
public interface Bonus {
  /**
   * A short name for the bonus, e.g. "bloodlust" for "Borderlands Bloodlust".
   *
   * @return A string name for the bonus.
   */
  String getType();

  /**
   * The name of the team that currently owns the bonus. The name will be either
   * "Red", "Green", or "Blue".
   *
   * @return A string name of the team that owns the bonus.
   */
  String getOwner();
}
