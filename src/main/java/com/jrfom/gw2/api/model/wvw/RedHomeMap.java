package com.jrfom.gw2.api.model.wvw;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * {@inheritDoc}
 */
@JsonDeserialize(using = JsonDeserializer.None.class)
@Gw2ApiVersion("v1")
public class RedHomeMap implements WvwMap {
  protected String type;
  protected MatchScores scores;
  protected ArrayList<Objective> objectives;
  protected ArrayList<Bonus> bonuses;

  public RedHomeMap() {
    this.type = "RedHome";
  }

  /**
   * {@inheritDoc}
   */
  public String getType() {
    return type;
  }

  /**
   * {@inheritDoc}
   */
  public MatchScores getScores() {
    return scores;
  }

  /**
   * {@inheritDoc}
   */
  public void setScores(MatchScores scores) {
    this.scores = scores;
  }

  /**
   * {@inheritDoc}
   */
  public ArrayList<Objective> getObjectives() {
    return objectives;
  }

  /**
   * {@inheritDoc}
   */
  public void setObjectives(ArrayList<Objective> objectives) {
    this.objectives = objectives;
  }

  /**
   * {@inheritDoc}
   */
  public ArrayList<Bonus> getBonuses() {
    return bonuses;
  }

  /**
   * {@inheritDoc}
   */
  public void setBonuses(ArrayList<Bonus> bonuses) {
    this.bonuses = bonuses;
  }
}