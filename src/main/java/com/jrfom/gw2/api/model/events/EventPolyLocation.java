package com.jrfom.gw2.api.model.events;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents an {@link com.jrfom.gw2.api.model.events.Event}
 * {@link com.jrfom.gw2.api.model.events.Location} that is described by
 * a polygon.
 */
@JsonDeserialize(using = JsonDeserializer.None.class)
@Gw2ApiVersion("v1")
public class EventPolyLocation extends EventLocation {
  @JsonProperty("z_range")
  private Range zRange;
  private ArrayList<Point> points;

  public EventPolyLocation() {
    this.setType("poly");
  }

  /**
   * The distance in the z-plane that the polygon occupies.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.events.Range}.
   */
  public Range getzRange() {
    return this.zRange;
  }

  public void setzRange(Range zRange) {
    this.zRange = zRange;
  }

  /**
   * A list of points that define the vertices of the polygon.
   *
   * @return An {@link java.util.ArrayList} of
   * {@link com.jrfom.gw2.api.model.events.Point} objects.
   */
  public ArrayList<Point> getPoints() {
    return this.points;
  }

  public void setPoints(ArrayList<Point> points) {
    this.points = points;
  }
}