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
}