package com.jrfom.gw2.api.model.events;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.jackson.deserializers.LocationDeserializer;

/**
 * <p>A {@linkplain com.jrfom.gw2.api.model.events.Location} describes where
 * an {@link com.jrfom.gw2.api.model.events.Event} takes place in the game
 * world. There are three possible location types:</p>
 *
 * <ul>
 *   <li>{@link com.jrfom.gw2.api.model.events.EventCylinderLocation}</li>
 *   <li>{@link com.jrfom.gw2.api.model.events.EventPolyLocation}</li>
 *   <li>{@link com.jrfom.gw2.api.model.events.EventSphereLocation}</li>
 * </ul>
 */
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
  /**
   * A string describing the type of the location. The possible values are:
   * "cylinder", "poly", and "sphere".
   *
   * @return The location type.
   */
  String getType();

  /**
   * A coordinate pin-pointing the center location of the event location.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.events.EventCenterCoordinate}.
   */
  EventCenterCoordinate getCenter();

  /**
   * The height of an event location.
   *
   * @return A {@link java.lang.Double}.
   */
  Double getHeight();

  /**
   * The radius of an event location.
   *
   * @return A {@link java.lang.Double}.
   */
  Double getRadius();

  Double getRotation();

  Range getzRange();

  ArrayList<Point> getPoints();
}
