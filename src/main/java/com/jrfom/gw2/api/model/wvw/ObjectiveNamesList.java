package com.jrfom.gw2.api.model.wvw;

import java.util.ArrayList;

import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents a list of {@link com.jrfom.gw2.api.model.wvw.ObjectiveName}s as
 * is returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/wvw/objective_names">/v1/wvw/objective_names</a>.
 */
@Gw2ApiVersion("v1")
public class ObjectiveNamesList extends ArrayList<ObjectiveName> {
  public ObjectiveNamesList() {
    super(0);
  }
}