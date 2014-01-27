package com.jrfom.gw2.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.geography.MapCoordinate;

/**
 * Represents a "Heart Renown" quest.
 */
@Gw2ApiVersion("v1")
public class Task {
  @JsonProperty("task_id")
  private int taskId;
  private String objective;
  private int level;
  @JsonProperty("coord")
  private MapCoordinate coordinate;

  public Task() {}

  /**
   * The unique identifier for the task.
   *
   * @return An integer identifier.
   */
  public int getTaskId() {
    return this.taskId;
  }

  public void setTaskId(int taskId) {
    this.taskId = taskId;
  }

  /**
   * The objective description for this task.
   *
   * @return A string description.
   */
  public String getObjective() {
    return this.objective;
  }

  public void setObjective(String objective) {
    this.objective = objective;
  }

  /**
   * The game character level of the task.
   *
   * @return An integer character level.
   */
  public int getLevel() {
    return this.level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  /**
   * The location of the task within the parent map.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.MapCoordinate}.
   */
  public MapCoordinate getCoordinate() {
    return this.coordinate;
  }

  public void setCoordinate(MapCoordinate coordinate) {
    this.coordinate = coordinate;
  }
}