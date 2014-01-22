package com.jrfom.gw2;

import com.jrfom.gw2.api.model.Build;
import com.jrfom.gw2.api.model.colors.ColorsList;
import com.jrfom.gw2.api.model.geography.Continents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiClient extends RestTemplate {
  private static final Logger log = LoggerFactory.getLogger(ApiClient.class);
  private final String baseUrl = "https://api.guildwars2.com/v1/";

  public ApiClient() {}

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
}