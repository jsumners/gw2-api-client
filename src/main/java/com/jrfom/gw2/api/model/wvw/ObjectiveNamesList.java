package com.jrfom.gw2.api.model.wvw;

import java.util.ArrayList;

import com.jrfom.gw2.annotations.Gw2ApiVersion;

@Gw2ApiVersion("v1")
public class ObjectiveNamesList extends ArrayList<ObjectiveName> {
  public ObjectiveNamesList() {
    super(0);
  }
}