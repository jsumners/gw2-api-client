package com.jrfom.gw2.api.model.events;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

@JsonDeserialize(using = JsonDeserializer.None.class)
@Gw2ApiVersion("v1")
public class EventPolyLocation extends EventLocation {
  @JsonProperty("z_range")
  private Range zRange;
  private ArrayList<Point> points;

  public EventPolyLocation() {
    this.setType("poly");
  }

  public Range getzRange() {
    return this.zRange;
  }

  public void setzRange(Range zRange) {
    this.zRange = zRange;
  }

  public ArrayList<Point> getPoints() {
    return this.points;
  }

  public void setPoints(ArrayList<Point> points) {
    this.points = points;
  }
}