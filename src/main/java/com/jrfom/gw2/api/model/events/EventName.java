package com.jrfom.gw2.api.model.events;

import com.jrfom.gw2.annotations.Gw2ApiVersion;

@Gw2ApiVersion("v1")
public class EventName {
  private String id;
  private String name;

  public EventName() {}

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }
}