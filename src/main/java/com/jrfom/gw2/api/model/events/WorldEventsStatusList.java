package com.jrfom.gw2.api.model.events;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.jackson.deserializers.WorldEventStatusListDeserializer;
import com.jrfom.gw2.jackson.serializers.WorldEventStatusListSerializer;

/**
 * Models the result of {@code /v1/events.json}.
 */
@JsonDeserialize(using = WorldEventStatusListDeserializer.class)
@JsonSerialize(using = WorldEventStatusListSerializer.class)
@Gw2ApiVersion("v1")
public class WorldEventsStatusList extends ArrayList<WorldEventStatus> {
  public WorldEventsStatusList() {
    super(0);
  }

  /**
   * Retrieve a subset of the results that have the given event identifier. If you
   * queried the API for only one event, then this method will simply be the same
   * list. In that case, you should not use this method as it will loop through
   * all of the results anyway.
   *
   * @param id The identifier of the event.
   *
   * @return An array of {@link com.jrfom.gw2.api.model.events.WorldEventStatus}.
   * The array will have zero elements if there are no results found.
   */
  public ArrayList<WorldEventStatus> getEventsWithEventId(String id) {
    ArrayList<WorldEventStatus> result = new ArrayList<>(0);

    for (WorldEventStatus eventStatus : this) {
      if (eventStatus.getEventId().equals(id)) {
        result.add(eventStatus);
      }
    }

    return result;
  }

  /**
   * Retrieve a subset of the results that have the given map identifier. If you
   * queried the API for only one map, then this method will simply be the same
   * list. In that case, you should not use this method as it will loop through
   * all of the results anyway.
   *
   * @param id The map identifier to retrieve.
   *
   * @return A list of {@link com.jrfom.gw2.api.model.events.WorldEventStatus}.
   * The list will have zero elements if there are no results.
   */
  public ArrayList<WorldEventStatus> getEventsWithMapId(int id) {
    ArrayList<WorldEventStatus> result = new ArrayList<>(0);

    for (WorldEventStatus eventStatus : this) {
      if (eventStatus.getMapId() == id) {
        result.add(eventStatus);
      }
    }

    return result;
  }

  /**
   * Retrieve a subset of the results that have the given world identifier. If
   * you queried the API for only one world, then this will simply return the
   * same list. In that case, you should not use this method as it will loop
   * through all of the results anyway.
   *
   * @param id The world identifier to retrieve.
   * @return A list of {@link com.jrfom.gw2.api.model.events.WorldEventStatus}.
   * The list will have zero elements if there are no results.
   */
  public ArrayList<WorldEventStatus> getEventsWithWorldId(int id) {
    ArrayList<WorldEventStatus> result = new ArrayList<>(0);

    for (WorldEventStatus eventStatus : this) {
      if (eventStatus.getWorldId() == id) {
        result.add(eventStatus);
      }
    }

    return result;
  }

  /**
   * Retrieve a subset of the results that have the given state. You can use the
   * following states as a query parameter:
   *
   * <ul>
   *   <li>{@link com.jrfom.gw2.api.model.events.WorldEventStatus#STATUS_ACTIVE}</li>
   *   <li>{@link com.jrfom.gw2.api.model.events.WorldEventStatus#STATUS_FAIL}</li>
   *   <li>{@link com.jrfom.gw2.api.model.events.WorldEventStatus#STATUS_INACTIVE}</li>
   *   <li>{@link com.jrfom.gw2.api.model.events.WorldEventStatus#STATUS_PREPARATION}</li>
   *   <li>{@link com.jrfom.gw2.api.model.events.WorldEventStatus#STATUS_SUCCESS}</li>
   *   <li>{@link com.jrfom.gw2.api.model.events.WorldEventStatus#STATUS_WARMUP}</li>
   * </ul>
   *
   * @param state The state to retrieve.
   *
   * @return A list of {@link com.jrfom.gw2.api.model.events.WorldEventStatus}.
   * The list will have zero elements if there are no results.
   */
  public ArrayList<WorldEventStatus> getEventsWithState(String state) {
    ArrayList<WorldEventStatus> result = new ArrayList<>(0);

    for (WorldEventStatus eventStatus : this) {
      if (eventStatus.getState().equals(state)) {
        result.add(eventStatus);
      }
    }

    return result;
  }
}