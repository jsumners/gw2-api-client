package com.jrfom.gw2.api.model.geography;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.Task;

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

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getMinLevel() {
    return this.minLevel;
  }

  public void setMinLevel(int minLevel) {
    this.minLevel = minLevel;
  }

  public int getMaxLevel() {
    return this.maxLevel;
  }

  public void setMaxLevel(int maxLevel) {
    this.maxLevel = maxLevel;
  }

  public int getDefaultFloor() {
    return this.defaultFloor;
  }

  public void setDefaultFloor(int defaultFloor) {
    this.defaultFloor = defaultFloor;
  }

  public MapRectangle getMapRectangle() {
    return this.mapRectangle;
  }

  public void setMapRectangle(MapRectangle mapRectangle) {
    this.mapRectangle = mapRectangle;
  }

  public MapRectangle getContinentRectangle() {
    return this.continentRectangle;
  }

  public void setContinentRectangle(MapRectangle continentRectangle) {
    this.continentRectangle = continentRectangle;
  }

  public ArrayList<PointOfInterest> getPointsOfInterest() {
    return this.pointsOfInterest;
  }

  public void setPointsOfInterest(ArrayList<PointOfInterest> pointsOfInterest) {
    this.pointsOfInterest = pointsOfInterest;
  }

  public ArrayList<Task> getTasks() {
    return this.tasks;
  }

  public void setTasks(ArrayList<Task> tasks) {
    this.tasks = tasks;
  }

  public ArrayList<SkillChallenge> getSkillChallenges() {
    return this.skillChallenges;
  }

  public void setSkillChallenges(ArrayList<SkillChallenge> skillChallenges) {
    this.skillChallenges = skillChallenges;
  }

  public ArrayList<Sector> getSectors() {
    return this.sectors;
  }

  public void setSectors(ArrayList<Sector> sectors) {
    this.sectors = sectors;
  }
}