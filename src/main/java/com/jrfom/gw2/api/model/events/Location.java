package com.jrfom.gw2.api.model.events;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.jackson.deserializers.LocationDeserializer;

@JsonSubTypes({
  @JsonSubTypes.Type(EventLocation.class),
  @JsonSubTypes.Type(EventCylinderLocation.class),
  @JsonSubTypes.Type(EventSphereLocation.class),
  @JsonSubTypes.Type(EventPolyLocation.class)
})
@JsonDeserialize(using = LocationDeserializer.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Gw2ApiVersion("v1")
public interface Location {
  String getType();
  EventCenterCoordinate getCenter();
  Double getHeight();
  Double getRadius();
  Double getRotation();
  Range getzRange();
  ArrayList<Point> getPoints();
}
