package com.jrfom.gw2.api.model.wvw;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * See {@link com.jrfom.gw2.api.model.wvw.WvwMap}.
 */
@JsonDeserialize(using = JsonDeserializer.None.class)
@Gw2ApiVersion("v1")
public class GreenHomeMap extends RedHomeMap {
  public GreenHomeMap() {
    this.type = "GreenHome";
  }
}