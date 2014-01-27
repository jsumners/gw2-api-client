package com.jrfom.gw2.api.model.geography;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.Task;

/**
 * Represents a map as is returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/map_floor">/v1/map_floor</a>.
 */
@Gw2ApiVersion("v1")
public class FloorMap {
  private String name;
  @JsonProperty("min_level")
  private int minLevel;
  @JsonProperty("max_level")
  private int maxLevel;
  @JsonProperty("default_floor")
  private int defaultFloor;
  @JsonProperty("map_rect")
  private MapRectangle mapRectangle;
  @JsonProperty("continent_rect")
  private MapRectangle continentRectangle;
  @JsonProperty("points_of_interest")
  private ArrayList<PointOfInterest> pointsOfInterest;
  private ArrayList<Task> tasks;
  @JsonProperty("skill_challenges")
  private ArrayList<SkillChallenge> skillChallenges;
  private ArrayList<Sector> sectors;

  public FloorMap() {}

  /**
   * The in-game name of the map.
   *
   * @return A string name.
   */
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * The minimum target character level for the map.
   *
   * @return An integer character level.
   */
  public int getMinLevel() {
    return this.minLevel;
  }

  public void setMinLevel(int minLevel) {
    this.minLevel = minLevel;
  }

  /**
   * The maximum target character level for the map.
   *
   * @return An integer character level.
   */
  public int getMaxLevel() {
    return this.maxLevel;
  }

  public void setMaxLevel(int maxLevel) {
    this.maxLevel = maxLevel;
  }

  /**
   * The default {@link com.jrfom.gw2.api.model.geography.Floor} for the map.
   *
   * @return An integer identified the default floor.
   */
  public int getDefaultFloor() {
    return this.defaultFloor;
  }

  public void setDefaultFloor(int defaultFloor) {
    this.defaultFloor = defaultFloor;
  }

  /**
   * A pair of coordinates defining the size of the map.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.MapRectangle}.
   */
  public MapRectangle getMapRectangle() {
    return this.mapRectangle;
  }

  public void setMapRectangle(MapRectangle mapRectangle) {
    this.mapRectangle = mapRectangle;
  }

  /**
   * A pair of coordinates defining the size of the map within the gam
   * coordinate system.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.MapRectangle}.
   */
  public MapRectangle getContinentRectangle() {
    return this.continentRectangle;
  }

  public void setContinentRectangle(MapRectangle continentRectangle) {
    this.continentRectangle = continentRectangle;
  }

  /**
   * A list of all the {@link com.jrfom.gw2.api.model.geography.PointOfInterest}
   * in the map.
   *
   * @return An {@link java.util.ArrayList} of
   * {@link com.jrfom.gw2.api.model.geography.PointOfInterest}.
   */
  public ArrayList<PointOfInterest> getPointsOfInterest() {
    return this.pointsOfInterest;
  }

  public void setPointsOfInterest(ArrayList<PointOfInterest> pointsOfInterest) {
    this.pointsOfInterest = pointsOfInterest;
  }

  /**
   * A list of the "Heart Renown" quests in the map.
   *
   * @return An {@link java.util.ArrayList} of
   * {@link com.jrfom.gw2.api.model.Task}s.
   */
  public ArrayList<Task> getTasks() {
    return this.tasks;
  }

  public void setTasks(ArrayList<Task> tasks) {
    this.tasks = tasks;
  }

  /**
   * A list of {@link com.jrfom.gw2.api.model.geography.SkillChallenge}s
   * available in the map.
   *
   * @return An {@link java.util.ArrayList} of
   * {@link com.jrfom.gw2.api.model.geography.SkillChallenge}s.
   */
  public ArrayList<SkillChallenge> getSkillChallenges() {
    return this.skillChallenges;
  }

  public void setSkillChallenges(ArrayList<SkillChallenge> skillChallenges) {
    this.skillChallenges = skillChallenges;
  }

  /**
   * A list of sub-areas in the map.
   *
   * @return An {@link java.util.ArrayList} of
   * {@link com.jrfom.gw2.api.model.geography.Sector}s.
   */
  public ArrayList<Sector> getSectors() {
    return this.sectors;
  }

  public void setSectors(ArrayList<Sector> sectors) {
    this.sectors = sectors;
  }
}