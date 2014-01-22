package com.jrfom.gw2;

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.jrfom.gw2.api.model.Build;
import com.jrfom.gw2.api.model.GwApiError;
import com.jrfom.gw2.api.model.colors.ColorsList;
import com.jrfom.gw2.api.model.events.EventDetails;
import com.jrfom.gw2.api.model.events.EventNamesList;
import com.jrfom.gw2.api.model.events.WorldEventsStatusList;
import com.jrfom.gw2.api.model.geography.Continents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 * <p>Provides a simple interface for interacting with the public Guild
 * Wars 2 API. Whenever possible, methods of this interface will return simple
 * objects. However, if there is a possibility for an error being returned by
 * the remote API, then the resulting object of this client's method will be
 * wrapped in an {@link com.google.common.base.Optional}. In all such cases,
 * an instance of {@link com.jrfom.gw2.api.model.GwApiError} will be thrown.
 * This error can either be ignored, or it can be trapped with a
 * {@code try...catch} construct.</p>
 *
 * <p>You can either instantiate this class directly, or use the
 * {@link ApiClient#getInstance()} method to retrieve a static instance.</p>
 */
@Component
public class ApiClient extends RestTemplate {
  private static final Logger log = LoggerFactory.getLogger(ApiClient.class);
  private final String baseUrl = "https://api.guildwars2.com/v1/";

  private static ApiClient apiClient;

  public ApiClient() {
    ResponseErrorHandler errorHandler = new ResponseErrorHandler() {
      @Override
      public boolean hasError(ClientHttpResponse response) throws IOException {
        HttpStatus status = response.getStatusCode();
        HttpStatus.Series series = status.series();
        return (HttpStatus.Series.CLIENT_ERROR.equals(series) ||
          HttpStatus.Series.SERVER_ERROR.equals(series)
        );
      }

      @Override
      public void handleError(ClientHttpResponse response) throws IOException {
        log.debug("errorHandler.handleError() called");
        log.debug(response.getStatusText());

        String json = response.getStatusText();
        ObjectMapper mapper = new ObjectMapper();
        GwApiError gwApiError = mapper.readValue(json, GwApiError.class);
        throw gwApiError;
      }
    };

    this.setErrorHandler(errorHandler);
  }

  /**
   * A factory method to get a static instance of
   * {@link com.jrfom.gw2.ApiClient}.
   *
   * @return A static instance of {@linkplain com.jrfom.gw2.ApiClient}.
   */
  public static ApiClient getInstance() {
    if (apiClient == null) {
      apiClient = new ApiClient();
    }

    return apiClient;
  }

  /**
   * Utility method mainly used for testing.
   *
   * @return The base URL for the Guild Wars 2 public API.
   */
  public String getBaseUrl() {
    return baseUrl;
  }

  /**
   * Retrieve the current build number for Guild Wars 2. See
   * <a href="http://wiki.guildwars2.com/wiki/API:1/build">API:1/build</a> for
   * more information.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.Build}.
   */
  public Build getBuild() {
    log.debug("Attempting to get build");
    return this.getForObject(this.baseUrl + "build.json", Build.class);
  }

  /**
   * Retrieve the list of colors localized to the English language. See
   * <a href="http://wiki.guildwars2.com/wiki/API:1/colors">API:1/colors</a> for
   * more information.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.colors.ColorsList}.
   */
  public ColorsList getColors() {
    return this.getColors(null);
  }

  /**
   * Retrieve the list of colors localized to the specified language. See
   * <a href="http://wiki.guildwars2.com/wiki/API:1/colors">API:1/colors</a> for
   * more information.
   *
   * @param lang A valid language abbreviation. For example, "en" for English or
   *             "de" for German. An invalid abbreviation will equate to using
   *             "en".
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.colors.ColorsList}.
   */
  public ColorsList getColors(String lang) {
    log.debug("Attempting to get colors with lang: `{}`", lang);
    String url = "colors.json";
    if (lang != null) {
      url = url + "?lang={lang}";
    }

    return this.getForObject(this.baseUrl + url, ColorsList.class, lang);
  }

  /**
   * Retrieve the list of continents. See
   * <a href="http://wiki.guildwars2.com/wiki/API:1/continents">API:1/continents</a>
   * for more information.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.Continents}.
   */
  public Continents getContinents() {
    log.debug("Attempting to get continents");
    return this.getForObject(this.baseUrl + "continents.json", Continents.class);
  }

  /**
   * Retrieve the full list of events and their details localized to the
   * English language. This <em>will</em> be a slow process. See
   * {@link com.jrfom.gw2.ApiClient#getEventDetailsForEventId(String)} for a
   * better method to use.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.events.EventDetails}
   * wrapped in an {@link com.google.common.base.Optional}. If there was an
   * error, then the {@code Optional} will be empty. In this case, a
   * {@link com.jrfom.gw2.api.model.GwApiError} will be thrown. You can trap
   * that exception if you need more details.
   */
  public Optional<EventDetails> getEventDetails() {
    return this.getEventDetailsForEventIdInLang(null, null);
  }

  /**
   * Retrieve the details for a given event identifier. A list of identifiers
   * can be retrieved via {@link com.jrfom.gw2.ApiClient#getEventNames()}. The
   * details will be localized to the English language. For a different
   * localization use
   * {@link com.jrfom.gw2.ApiClient#getEventDetailsForEventIdInLang(String, String)}.
   *
   * @param eventId The event identifier to lookup.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.events.EventDetails}
   * wrapped in an {@link com.google.common.base.Optional}. If there was an
   * error, then the {@code Optional} will be empty. In this case, a
   * {@link com.jrfom.gw2.api.model.GwApiError} will be thrown. You can trap
   * that exception if you need more details.
   */
  public Optional<EventDetails> getEventDetailsForEventId(String eventId) {
    return this.getEventDetailsForEventIdInLang(eventId, null);
  }

  /**
   * Retrieve a list of event details as described in
   * <a href="http://wiki.guildwars2.com/wiki/API:1/event_details">API:1/event_details</a>.
   * If no {@code eventId} is given, a full list of event details will be
   * retrieved -- this will be slow.
   *
   * @param eventId A valid event identifier.
   *                See {@link com.jrfom.gw2.ApiClient#getEventNamesForLang(String)}
   *                for a way to get a list of valid identifiers.
   * @param lang A valid language abbreviation. For example, "en" for English or
   *             "de" for German. An invalid abbreviation will equate to using
   *             "en".
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.events.EventDetails}
   * wrapped in an {@link com.google.common.base.Optional}. If there was an
   * error, then the {@code Optional} will be empty. In this case, a
   * {@link com.jrfom.gw2.api.model.GwApiError} will be thrown. You can trap
   * that exception if you need more details.
   */
  public Optional<EventDetails> getEventDetailsForEventIdInLang(String eventId, String lang) {
    log.debug("Attempting to get event details with [eventId: `{}`, lang: `{}`]", eventId, lang);
    Optional<EventDetails> result = Optional.absent();
    HashMap<String, String> params = new HashMap<>(0);
    String url = "event_details.json";

    if (eventId != null) {
      url = url + "?event_id={event_id}";
      params.put("event_id", eventId);
    }

    if (lang != null) {
      url = url + ((url.indexOf("?") != -1) ? "&lang={lang}" : "?lang={lang}");
      params.put("lang", lang);
    }

    try {
      EventDetails details =
        this.getForObject(this.baseUrl + url, EventDetails.class, params);
      result = Optional.of(details);
    } catch (RestClientException e) {
      log.error("Retrieving event details failed: `{}`", e.getMessage());
      log.debug(e.toString());
    }

    return result;
  }

  /**
   * Retrieve a list of event names localized to the English language.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.events.EventNamesList}.
   */
  public EventNamesList getEventNames() {
    return this.getEventNamesForLang(null);
  }

  /**
   * Retrieve a list of event names localized to the given language abbreviation.
   * If no abbreviation is given (i.e. {@code null}), or the abbreviation is
   * invalid, then the names will be localized to English ("en").
   *
   * @param lang A valid language abbreviation. For example, "en" for English or
   *             "de" for German. An invalid abbreviation will equate to using
   *             "en".
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.events.EventNamesList}.
   */
  public EventNamesList getEventNamesForLang(String lang) {
    log.debug("Attempting to get the list of event names with lang: `{}`", lang);
    String url = "event_names.json";
    if (lang != null) {
      url = url + "?lang={lang}";
    }

    return this.getForObject(this.baseUrl + url, EventNamesList.class, lang);
  }

  /**
   * <p>Retrieve a list of event statuses. See
   * {@link com.jrfom.gw2.ApiClient#getEventStatusesForWorldAndMapAndEvent(int, int, String)}
   * for full details.</p>
   *
   * <p><strong>Important:</strong> this method literally returns all possible
   * event statuses across all game worlds and game maps. It is extremely
   * unlikely that you want this, and it is a very slow process. You should
   * really use one of the narrower methods for getting event statuses.</p>
   *
   * @return A list of all possible statuses.
   */
  public WorldEventsStatusList getEventStatuses() {
    return this.getEventStatusesForWorldAndMapAndEvent(-1, -1, null);
  }

  /**
   * <p>Retrieve a list of event statuses. See
   * {@link com.jrfom.gw2.ApiClient#getEventStatusesForWorldAndMapAndEvent(int, int, String)}
   * for full details.</p>
   *
   * <p>The result of this method will be a list that is constrained to only
   * one world. It will be all events for that world across all game maps.</p>
   *
   * @param worldId A valid world id or {@code -1} for all worlds.
   *
   * @return Either a list of statuses or an empty list.
   */
  public WorldEventsStatusList getEventStatusesForWorld(int worldId) {
    return this.getEventStatusesForWorldAndMapAndEvent(worldId, -1, null);
  }

  /**
   * <p>Retrieve a list of event statuses. See
   * {@link com.jrfom.gw2.ApiClient#getEventStatusesForWorldAndMapAndEvent(int, int, String)}
   * for full details.</p>
   *
   * <p>The result of this method will be list that is constrained to only
   * a certain game map. The list will consists of all events in that map
   * <em>for all game worlds</em>.</p>
   *
   * @param mapId A valid map id or {@code -1} for all maps.
   *
   * @return Either a list of statuses or an empty list.
   */
  public WorldEventsStatusList getEventStatusesForMap(int mapId) {
    return this.getEventStatusesForWorldAndMapAndEvent(-1, mapId, null);
  }

  /**
   * <p>Retrieve a list of event statuses. See
   * {@link com.jrfom.gw2.ApiClient#getEventStatusesForWorldAndMapAndEvent(int, int, String)}
   * for full details.</p>
   *
   * <p>The result of this method will be a list that is constrained to a
   * specific map on a specific world. Thus, this should be a fast response.</p>
   *
   * @param worldId A valid world id or {@code -1} for all worlds.
   * @param mapId A valid map id or {@code -1} for all maps.
   *
   * @return Either a list of statuses or an empty list.
   */
  public WorldEventsStatusList getEventStatusesForWorldAndMap(int worldId, int mapId) {
    return this.getEventStatusesForWorldAndMapAndEvent(worldId, mapId, null);
  }

  /**
   * <p>Retrieve a list of event statuses. See
   * {@link com.jrfom.gw2.ApiClient#getEventStatusesForWorldAndMapAndEvent(int, int, String)}
   * for full details.</p>
   *
   * <p>The result of this method will be a list of event statuses for a single
   * event across <em>all</em> game worlds and, potentially, all game maps.</p>
   *
   * @param eventId A valid event identifier.
   *                See {@link com.jrfom.gw2.ApiClient#getEventNamesForLang(String)}
   *                for a way to get a list of valid identifiers.
   *
   * @return A list of statuses for the given {@code eventId} or a list of all
   * possible event statuses if the {@code eventId} is invalid. Hint: use a
   * valid event identifier. You don't want the full list.
   */
  public WorldEventsStatusList getEventStatusesForEvent(String eventId) {
    return this.getEventStatusesForWorldAndMapAndEvent(-1, -1, eventId);
  }

  /**
   * <p>Retrieve a list of event statuses. See
   * {@link com.jrfom.gw2.ApiClient#getEventStatusesForWorldAndMapAndEvent(int, int, String)}
   * for full details.</p>
   *
   * <p>The result of this method will be a list that is constrained to a
   * specific game world, but will contain all events with the given event
   * identifier.</p>
   *
   * @param worldId A valid world id or {@code -1} for all worlds.
   * @param eventId A valid event identifier.
   *                See {@link com.jrfom.gw2.ApiClient#getEventNamesForLang(String)}
   *                for a way to get a list of valid identifiers.
   *
   * @return Either a list of statuses or an empty list.
   */
  public WorldEventsStatusList getEventStatusesForWorldAndEvent(int worldId, String eventId) {
    return this.getEventStatusesForWorldAndMapAndEvent(worldId, -1, eventId);
  }

  /**
   * <p>Retrieve a list of event statuses. See
   * {@link com.jrfom.gw2.ApiClient#getEventStatusesForWorldAndMapAndEvent(int, int, String)}
   * for full details.</p>
   *
   * <p>The result of this method will be list of event statuses that are
   * constrained to a specific game map but across <em>all game worlds</em>.</p>
   *
   * @param mapId A valid map id or {@code -1} for all maps.
   * @param eventId A valid event identifier.
   *                See {@link com.jrfom.gw2.ApiClient#getEventNamesForLang(String)}
   *                for a way to get a list of valid identifiers.
   *
   * @return Either a list of statuses or an empty list.
   */
  public WorldEventsStatusList getEventStatusesForMapAndEvent(int mapId, String eventId) {
    return this.getEventStatusesForWorldAndMapAndEvent(-1, mapId, eventId);
  }

  /**
   * <p>Retrieve a list of event statuses from
   * <a href="http://wiki.guildwars2.com/wiki/API:1/events">/v1/events</a>. This
   * list can be filtered by any combination of the method parameters.</p>
   *
   * <p><strong>Note:</strong> if you give any parameter that is invalid, no
   * error will be returned by the remote API. If either the {@code worldId}
   * or {@code mapId} are incorrect then an empty list will be returned. If
   * the only invalid parameter is the {@code eventId} then it will be ignore.
   * If the only given parameter is the {@code eventId}, that is {@code worldId}
   * and {@code mapId} are {@code -1}, then the whole list of events for every
   * world and map will be retrieved.</p>
   *
   * @param worldId A valid world id or {@code -1} for all worlds.
   * @param mapId A valid map id or {@code -1} for all maps.
   * @param eventId A valid event identifier.
   *                See {@link com.jrfom.gw2.ApiClient#getEventNamesForLang(String)}
   *                for a way to get a list of valid identifiers.
   *
   * @return Either a list of statuses or an empty list.
   */
  public WorldEventsStatusList getEventStatusesForWorldAndMapAndEvent(int worldId, int mapId, String eventId) {
    log.debug(
      "Attempting to get event statuses with [world: `{}`, map: `{}`, event: `{}]",
      worldId,
      mapId,
      eventId
    );

    HashMap<String, String> params = new HashMap<>(0);
    String url = "events.json";

    if (worldId != -1) {
      url = url + "?world_id={world_id}";
      params.put("world_id", worldId + "");
    }

    if (mapId != -1) {
      url = url + ((url.indexOf("?") != -1) ? "&map_id={map_id}" : "?map_id={map_id}");
      params.put("map_id", mapId + "");
    }

    if (eventId != null) {
      url = url + ((url.indexOf("?") != -1) ? "&event_id={event_id}" : "?event_id={event_id}");
      params.put("event_id", eventId);
    }

    return this.getForObject(this.baseUrl + url, WorldEventsStatusList.class, params);
  }
}