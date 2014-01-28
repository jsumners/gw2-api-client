package com.jrfom.gw2.api.model.wvw;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * {@inheritDoc}
 */
@JsonDeserialize(using = JsonDeserializer.None.class)
@Gw2ApiVersion("v1")
public class BlueHomeMap extends RedHomeMap {
  public BlueHomeMap() {
    this.type = "BlueHome";
  }
}