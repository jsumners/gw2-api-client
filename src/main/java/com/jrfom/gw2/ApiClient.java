package com.jrfom.gw2;

import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.jrfom.gw2.api.model.Build;
import com.jrfom.gw2.api.model.Guild;
import com.jrfom.gw2.api.model.GwApiError;
import com.jrfom.gw2.api.model.WorldNamesList;
import com.jrfom.gw2.api.model.colors.ColorsList;
import com.jrfom.gw2.api.model.crafting.Recipe;
import com.jrfom.gw2.api.model.crafting.RecipesList;
import com.jrfom.gw2.api.model.events.EventDetails;
import com.jrfom.gw2.api.model.events.EventNamesList;
import com.jrfom.gw2.api.model.events.WorldEventsStatusList;
import com.jrfom.gw2.api.model.geography.*;
import com.jrfom.gw2.api.model.items.GenericItem;
import com.jrfom.gw2.api.model.items.Item;
import com.jrfom.gw2.api.model.items.ItemIdList;
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

  /**
   * <p>Retrieves details for a guild with the specified {@code guildId}.</p>
   *
   * <p>If the id you supply cannot be found, the remote service will return
   * an error object. Thus, a {@link com.jrfom.gw2.api.model.GwApiError} will
   * be thrown. You can trap that error if you would like more details.
   * Otherwise, simply check for the presence of a value in this method's
   * result.</p>
   *
   * @param guildId The UUID for the guild to lookup.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.Guild} wrapped in
   * an {@link com.google.common.base.Optional} on success. On failure, the
   * {@code Optional} will be empty.
   */
  public Optional<Guild> getGuildDetailsForGuildId(String guildId) {
    log.debug("Attempting to get guild information for guildId: `{}`", guildId);
    Optional<Guild> result = Optional.absent();
    String url = "guild_details.json?guild_id={guild_id}";

    try {
      Guild guild = this.getForObject(this.baseUrl + url, Guild.class, guildId);
      result = Optional.of(guild);
    } catch (RestClientException e) {
      log.error("Could not retrieve details for guild: `{}`", e.getMessage());
      log.debug(e.toString());
    }

    return result;
  }

  /**
   * <p>Retrieves details for a guild with the specified {@code name}. If the
   * name includes spaces, do <em>not</em> URI encode them. This method will
   * take care of encoding for you.</p>
   *
   * <p>If the name you supply cannot be found, the remote service will return
   * an error object. Thus, a {@link com.jrfom.gw2.api.model.GwApiError} will
   * be thrown. You can trap that error if you would like more details.
   * Otherwise, simply check for the presence of a value in this method's
   * result.</p>
   *
   * @param name The name of the guild to lookup.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.Guild} wrapped in
   * an {@link com.google.common.base.Optional} on success. On failure, the
   * {@code Optional} will be empty.
   */
  public Optional<Guild> getGuildDetailsForGuildName(String name) {
    log.debug("Attempting to get guild information for guild named: `{}`", name);
    Optional<Guild> result = Optional.absent();
    String url = "guild_details.json?guild_name={guild_name}";

    try {
      Guild guild = this.getForObject(this.baseUrl + url, Guild.class, name);
      result = Optional.of(guild);
    } catch (RestClientException e) {
      log.error("Could not retrieve details for guild: `{}`", e.getMessage());
      log.debug(e.toString());
    }

    return result;
  }

  /**
   * <p>Retrieves a list of all known item identifiers. Your application should
   * probably cache this result for some amount of time.</p>
   *
   * <p>See <a href="http://wiki.guildwars2.com/wiki/API:1/items">/v1/items</a>
   * for more details.</p>
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.items.ItemIdList}.
   */
  public ItemIdList getItems() {
    log.debug("Attempting to get the list of item identifiers");
    return this.getForObject(this.baseUrl + "items.json", ItemIdList.class);
  }

  /**
   * Retrieves details for a specified item localized to the English language.
   * See {@link com.jrfom.gw2.ApiClient#getItemDetailsInLang(int, String)} for
   * more information.
   *
   * @param itemId A valid game item identifier.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.items.Item} wrapped
   * in an {@link com.google.common.base.Optional} if successful, otherwise an
   * empty {@code Optional}.
   */
  public Optional<Item> getItemDetails(int itemId) {
    return this.getItemDetailsInLang(itemId, null);
  }

  /**
   * <p>Retrieves details for a specified item localized to the specified
   * language. If the given language identifier is invalid, the English
   * language will be used. Possible language identifiers are:</p>
   *
   * <ul>
   *   <li>"en" for English</li>
   *   <li>"de" for German</li>
   *   <li>"es" for Spanish</li>
   *   <li>"fr" for French</li>
   * </ul>
   *
   * <p>The result of this method will be wrapped in an
   * {@link com.google.common.base.Optional}. If an invalid item identifier is
   * given, then this {@code Optional} will be empty and a
   * {@link com.jrfom.gw2.api.model.GwApiError} will be thrown (trap it for
   * more details). Otherwise, the {@code Optional} will contain an instance
   * of {@link com.jrfom.gw2.api.model.items.Item}. Possible types for
   * {@code Item} are:</p>
   *
   * <ul>
   *   <li>{@link com.jrfom.gw2.api.model.items.ArmorItem}</li>
   *   <li>{@link com.jrfom.gw2.api.model.items.BagItem}</li>
   *   <li>{@link com.jrfom.gw2.api.model.items.ConsumableItem}</li>
   *   <li>{@link com.jrfom.gw2.api.model.items.ContainerItem}</li>
   *   <li>{@link com.jrfom.gw2.api.model.items.CraftingMaterialItem}</li>
   *   <li>{@link com.jrfom.gw2.api.model.items.GizmoItem}</li>
   *   <li>{@link com.jrfom.gw2.api.model.items.MiniPetItem}</li>
   *   <li>{@link com.jrfom.gw2.api.model.items.TrinketItem}</li>
   *   <li>{@link com.jrfom.gw2.api.model.items.TrophyItem}</li>
   *   <li>{@link com.jrfom.gw2.api.model.items.UpgradeComponentItem}</li>
   *   <li>{@link com.jrfom.gw2.api.model.items.WeaponItem}</li>
   * </ul>
   *
   * @param itemId A valid game item identifier.
   * @param lang A valid language abbreviation. For example, "en" for English or
   *             "de" for German. An invalid abbreviation will equate to using
   *             "en".
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.items.Item} wrapped
   * in an {@link com.google.common.base.Optional} if successful, otherwise an
   * empty {@code Optional}.
   */
  public Optional<Item> getItemDetailsInLang(int itemId, String lang) {
    log.debug("Attempting to get details for [itemId: `{}`, lang: `{}`]", itemId, lang);
    Optional<Item> result = Optional.absent();
    HashMap<String, String> params = new HashMap<>(1);
    String url = "item_details.json?item_id={item_id}";

    params.put("item_id", itemId + "");
    if (lang != null) {
      url = url + "&lang={lang}";
      params.put("lang", lang);
    }

    try {
      Item item = this.getForObject(this.baseUrl + url, GenericItem.class, params);
      result = Optional.of(item);
    } catch (RestClientException e) {
      log.error("Could not retrieve details for item: `{}`", e.getMessage());
      log.debug(e.toString());
    }

    return result;
  }

  /**
   * <p>Retrieve the details for a given {@code mapId} localized
   * to the English language. See
   * {@link com.jrfom.gw2.ApiClient#getMapInLang(int, String)} for
   * more detail.</p>
   *
   * @param mapId A valid map id.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.Map}
   * wrapped in an {@link com.google.common.base.Optional} or an empty
   * {@code Optional}.
   */
  public Optional<Map> getMap(int mapId) {
    return this.getMapInLang(mapId, null);
  }

  /**
   * <p>Retrieve the details for a given {@code mapId} localized to the
   * specified {@code lang}.</p>
   *
   * <p>If the {@code mapId} is invalid then an instance of
   * {@link com.jrfom.gw2.api.model.GwApiError} will be thrown and an empty
   * {@link com.google.common.base.Optional} will be returned.</p>
   *
   * <p>If an invalid {@code lang} is given, then the default language of
   * English ("en") will be used instead.</p>
   *
   * @param mapId A valid map id.
   * @param lang A valid language abbreviation. For example, "en" for English or
   *             "de" for German. An invalid abbreviation will equate to using
   *             "en".
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.Map}
   * wrapped in an {@link com.google.common.base.Optional} or an empty
   * {@code Optional}.
   */
  public Optional<Map> getMapInLang(int mapId, String lang) {
    Optional<Map> result = Optional.absent();
    Optional<MapsList> mapsListOptional = this.getMapsInLang(mapId, lang);

    if (mapsListOptional.isPresent()) {
      MapsList list = mapsListOptional.get();
      // There should only be one map that matches the given id
      result = list.getMapWithId(mapId);
    }

    return result;
  }

  /**
   * <p>Retrieve the complete list of maps localized to the English
   * language. See {@link com.jrfom.gw2.ApiClient#getMapsInLang(int, String)}
   * for more detail.</p>
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.MapsList}
   * wrapped in an {@link com.google.common.base.Optional} or an empty
   * {@code Optional}.
   */
  public Optional<MapsList> getMaps() {
    return this.getMapsInLang(-1, null);
  }

  /**
   * <p>Retrieve the complete list of maps localized to the specified
   * language. See {@link com.jrfom.gw2.ApiClient#getMapsInLang(int, String)}
   * for more detail.</p>
   *
   * @param lang A valid language abbreviation. For example, "en" for English or
   *             "de" for German. An invalid abbreviation will equate to using
   *             "en".
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.MapsList}
   * wrapped in an {@link com.google.common.base.Optional} or an empty
   * {@code Optional}.
   */
  public Optional<MapsList> getMapsInLang(String lang) {
    return this.getMapsInLang(-1, lang);
  }

  /**
   * <p>Retrieves a list of maps for the specified {@code mapId} and
   * localized to the specified {@code lang}. In theory, if you supply a
   * {@mapId} then you should get back a list with only one element. So, to
   * retrieve a list of <em>all</em> maps, supply a {@mapId} set to
   * {@code -1}.</p>
   *
   * <p>If the {@mapId} is invalid, a {@link com.jrfom.gw2.api.model.GwApiError}
   * will be thrown and an empty {@link com.google.common.base.Optional} will
   * be returned.</p>
   *
   * <p>If the specified {@code lang} is invalid, then the default language of
   * English ("en") will be used.</p>
   *
   * @param mapId A valid map id or {@code -1} for all maps.
   * @param lang A valid language abbreviation. For example, "en" for English or
   *             "de" for German. An invalid abbreviation will equate to using
   *             "en".
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.MapsList}
   * wrapped in an {@link com.google.common.base.Optional} or an empty
   * {@code Optional}.
   */
  public Optional<MapsList> getMapsInLang(int mapId, String lang) {
    log.debug("Attempting to get maps for [mapId: `{}`, lang: `{}`]", mapId, lang);
    Optional<MapsList> result = Optional.absent();
    HashMap<String, String> params = new HashMap<>(0);
    String url = "maps.json";

    if (mapId != -1) {
      url = url + "?map_id={map_id}";
      params.put("map_id", mapId + "");
    }

    if (lang != null) {
      url = url + ((url.indexOf("?") != -1) ? "&lang={lang}" : "?lang={lang}");
      params.put("lang", lang);
    }

    try {
      MapsList list = this.getForObject(this.baseUrl + url, MapsList.class, params);
      result = Optional.of(list);
    } catch (RestClientException e) {
      log.error("Could not retrieve maps list for [mapId: `{}`, lang: `{}`]", mapId, lang);
      log.debug(e.toString());
    }

    return result;
  }

  /**
   * Retrieve a list of map names and their associated map identifiers. The
   * names will be localized to the English language.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.MapNamesList}.
   */
  public MapNamesList getMapNames() {
    return this.getMapNamesInLang(null);
  }

  /**
   * Retrieve a list of map names and their associated map identifiers. The
   * names will be localized to the specified language.
   *
   * @param lang A valid language abbreviation. For example, "en" for English or
   *             "de" for German. An invalid abbreviation will equate to using
   *             "en".
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.MapNamesList}.
   */
  public MapNamesList getMapNamesInLang(String lang) {
    log.debug("Attemping to get map names for lang: `{}`", lang);
    MapNamesList list = new MapNamesList();
    String url = "map_names.json";

    if (lang != null) {
      url = url + "?lang={lang}";
    }

    list = this.getForObject(this.baseUrl + url, MapNamesList.class, lang);

    return list;
  }

  /**
   * <p>Retrieve details about a map floor on the "Tyria" continent. See
   * {@link com.jrfom.gw2.ApiClient#getFloorInLang(int, int, String)} for
   * more deatils.</p>
   *
   * @param floorId A valid {@link com.jrfom.gw2.api.model.geography.Floor}
   *                identifier.
   *
   * @return
   */
  public Optional<Floor> getTyriaFloor(int floorId) {
    return this.getFloorInLang(1, floorId, null);
  }

  /**
   * <p>Retrieve details about a map floor on the "Tyria" continent. See
   * {@link com.jrfom.gw2.ApiClient#getFloorInLang(int, int, String)} for
   * more deatils.</p>
   *
   * @param floorId A valid {@link com.jrfom.gw2.api.model.geography.Floor}
   *                identifier.
   * @param lang A valid language abbreviation. For example, "en" for English or
   *             "de" for German. An invalid abbreviation will equate to using
   *             "en".
   *
   * @return
   */
  public Optional<Floor> getTyriaFloorInLang(int floorId, String lang) {
    return this.getFloorInLang(1, floorId, lang);
  }

  /**
   * <p>Retrieve details about a map floor on the "Mists" continent. See
   * {@link com.jrfom.gw2.ApiClient#getFloorInLang(int, int, String)} for
   * more deatils.</p>
   *
   * @param floorId A valid {@link com.jrfom.gw2.api.model.geography.Floor}
   *                identifier.
   *
   * @return
   */
  public Optional<Floor> getMistsFloor(int floorId) {
    return this.getFloorInLang(2, floorId, null);
  }

  /**
   * <p>Retrieve details about a map floor on the "Mists" continent. See
   * {@link com.jrfom.gw2.ApiClient#getFloorInLang(int, int, String)} for
   * more deatils.</p>
   *
   * @param floorId A valid {@link com.jrfom.gw2.api.model.geography.Floor}
   *                identifier.
   * @param lang A valid language abbreviation. For example, "en" for English or
   *             "de" for German. An invalid abbreviation will equate to using
   *             "en".
   *
   * @return
   */
  public Optional<Floor> getMistsFloorInLang(int floorId, String lang) {
    return this.getFloorInLang(2, floorId, lang);
  }

  /**
   * <p>Retrieve details about a map floor given the appropriate
   * {@code continentId}, {@code floorId}, and {@code lang}. The given
   * {@code lang} will localize all strings to said language.</p>
   *
   * @param continentId A valid {@link com.jrfom.gw2.api.model.geography.Continent}
   *                    identifier.
   * @param floorId A valid {@link com.jrfom.gw2.api.model.geography.Floor}
   *                identifier.
   * @param lang A valid language abbreviation. For example, "en" for English or
   *             "de" for German. An invalid abbreviation will equate to using
   *             "en".
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.Floor}
   * wrapped in an {@link com.google.common.base.Optional} or an empty
   * {@code Optional}. The empty {@code Optional} will be returned when either
   * an invalid {@code continentId} or {@code floorId} is given. This method
   * will <em>not</em> throw an instance of
   * {@link com.jrfom.gw2.api.model.GwApiError} in such instances. This is
   * because the remote ArenaNet API does not generate an error in this case.
   */
  public Optional<Floor> getFloorInLang(int continentId, int floorId, String lang) {
    log.debug(
      "Attempting to get floor for [continentId: `{}`, floorId: `{}`, lang: `{}`]",
      continentId,
      floorId,
      lang
    );
    Optional<Floor> result = Optional.absent();
    HashMap<String, String> params = new HashMap<>(2);
    String url = "map_floor.json?continent_id={continent_id}&floor={floor_id}";

    params.put("continent_id", continentId + "");
    params.put("floor_id", floorId + "");
    if (lang != null) {
      url = url + "&lang={lang}";
      params.put("lang", lang);
    }

    Floor floor = this.getForObject(this.baseUrl + url, Floor.class, params);
    if (floor.getRegions() != null) {
      result = Optional.of(floor);
    }

    return result;
  }

  /**
   * Retrieve the list of {@link com.jrfom.gw2.api.model.crafting.Recipe}
   * identifiers.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.crafting.RecipesList}.
   */
  public RecipesList getRecipes() {
    log.debug("Attempting to get the list of recipe ids");
    return this.getForObject(this.baseUrl + "recipes.json", RecipesList.class);
  }

  /**
   * Retrieve details for a specific recipe localized to the English language.
   * See {@link com.jrfom.gw2.ApiClient#getRecipeInLang(int, String)} for more
   * details.
   *
   * @param recipeId A valid recipe identifier.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.crafting.Recipe}
   * wrapped in an {@link com.google.common.base.Optional} or an empty
   * {@code Optional}. If an empty {@code Optional} was returned, an instance
   * of {@link com.jrfom.gw2.api.model.GwApiError} was also thrown.
   */
  public Optional<Recipe> getRecipe(int recipeId) {
    return this.getRecipeInLang(recipeId, null);
  }

  /**
   * <p>Retrieve details about a specific recipe identified by {@code recipeId}.
   * The details will be localized to the specified {@code lang}. If an
   * invalid {@code recipeId} is given, a
   * {@link com.jrfom.gw2.api.model.GwApiError} will be thrown.</p>
   *
   * @param recipeId A valid recipe identifier.
   * @param lang A valid language abbreviation. For example, "en" for English or
   *             "de" for German. An invalid abbreviation will equate to using
   *             "en".
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.crafting.Recipe}
   * wrapped in an {@link com.google.common.base.Optional} or an empty
   * {@code Optional}. If an empty {@code Optional} was returned, an instance
   * of {@link com.jrfom.gw2.api.model.GwApiError} was also thrown.
   */
  public Optional<Recipe> getRecipeInLang(int recipeId, String lang) {
    log.debug("Attempting to get recipe with [recipeId: `{}`, lang: `{}`]", recipeId, lang);
    Optional<Recipe> result = Optional.absent();
    HashMap<String, String> params = new HashMap<>(1);
    String url = "recipe_details.json?recipe_id={recipe_id}";

    params.put("recipe_id", recipeId + "");
    if (lang != null) {
      url = url + "&lang={lang}";
      params.put("lang", lang);
    }

    try {
      Recipe recipe = this.getForObject(this.baseUrl + url, Recipe.class, params);
      result = Optional.of(recipe);
    } catch (RestClientException e) {
      log.error("Could not retrieve recipe details: `{}`", e.getMessage());
      log.debug(e.toString());
    }

    return result;
  }

  /**
   * Retrieve a list of available game world (server) names localized to
   * the English language.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.WorldNamesList}.
   */
  public WorldNamesList getWorldNames() {
    return this.getWorldNamesInLang(null);
  }

  /**
   * Retrieve a list of the available game world (server) names localized to
   * the specified {@code lang}.
   *
   * @param lang A valid language abbreviation. For example, "en" for English or
   *             "de" for German. An invalid abbreviation will equate to using
   *             "en".
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.WorldNamesList}.
   */
  public WorldNamesList getWorldNamesInLang(String lang) {
    log.debug("Attempting to get list of world names with lang: `{}`", lang);
    String url = "world_names.json";

    if (lang != null) {
      url = url + "?lang={lang}";
    }

    return this.getForObject(this.baseUrl + url, WorldNamesList.class, lang);
  }
}