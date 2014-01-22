package com.jrfom.gw2.api.model.events;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.jackson.deserializers.RangeDeserializer;
import com.jrfom.gw2.jackson.serializers.RangeSerializer;

/**
 * Represents a pair of numbers that define a range in the z-plane. This is
 * used by the {@link com.jrfom.gw2.api.model.events.EventPolyLocation}
 * {@link com.jrfom.gw2.api.model.events.Location} type.
 */
@JsonDeserialize(using = RangeDeserializer.class)
@JsonSerialize(using = RangeSerializer.class)
@JsonSubTypes({@JsonSubTypes.Type(Point.class)})
@Gw2ApiVersion("v1")
public class Range {
  private double x;
  private double y;

  public Range() {}

  public double getX() {
    return this.x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return this.y;
  }

  public void setY(double y) {
    this.y = y;
  }
}