package com.jrfom.gw2.api.model.geography;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents an in-game point of interest location.
 */
@Gw2ApiVersion("v1")
public class PointOfInterest {
  @JsonProperty("poi_id")
  private int poiId;
  private String name;
  private String type;
  private int floor;
  @JsonProperty("coord")
  private MapCoordinate coordinate;

  public PointOfInterest() {}

  /**
   * A unique identifier for the point of interest.
   *
   * @return An integer identifier.
   */
  public int getPoiId() {
    return this.poiId;
  }

  public void setPoiId(int poiId) {
    this.poiId = poiId;
  }

  /**
   * The in-game name of the point of interest.
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
   * <p>The kind of point of interest. Possible values are:</p>
   *
   * <ul>
   *   <li>landmark</li>
   *   <li>waypoint</li>
   *   <li>vista</li>
   * </ul>
   *
   * @return A string detailing the type for the point of interest.
   */
  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  /**
   * The floor where this point of interest is located.
   *
   * @return A unique identifier for a {@link com.jrfom.gw2.api.model.geography.Floor}.
   */
  public int getFloor() {
    return this.floor;
  }

  public void setFloor(int floor) {
    this.floor = floor;
  }

  /**
   * The coordinate for the location of the point of interest.
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