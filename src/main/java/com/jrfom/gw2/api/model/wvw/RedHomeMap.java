package com.jrfom.gw2.api.model.wvw;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * See {@link com.jrfom.gw2.api.model.wvw.WvwMap}.
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
  @Override
  public String getType() {
    return type;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public MatchScores getScores() {
    return scores;
  }

  public void setScores(MatchScores scores) {
    this.scores = scores;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ArrayList<Objective> getObjectives() {
    return objectives;
  }

  public void setObjectives(ArrayList<Objective> objectives) {
    this.objectives = objectives;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ArrayList<Bonus> getBonuses() {
    return bonuses;
  }

  public void setBonuses(ArrayList<Bonus> bonuses) {
    this.bonuses = bonuses;
  }
}