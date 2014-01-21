package com.jrfom.gw2;

import com.jrfom.gw2.api.model.Build;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiClient extends RestTemplate {
//  private RestTemplate restTemplate;
  private final String baseUrl = "https://api.guildwars2.com/v1/";

  public ApiClient() {
//    this.restTemplate = new RestTemplate();
  }

  public Build getBuild() {
    Build build = this.getForObject(
      this.baseUrl + "build.json",
      Build.class
    );

    return build;
  }

  public String getBaseUrl() {
    return baseUrl;
  }
}