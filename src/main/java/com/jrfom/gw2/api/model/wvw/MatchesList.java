package com.jrfom.gw2.api.model.wvw;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.jackson.deserializers.MatchesListDeserializer;
import com.jrfom.gw2.jackson.serializers.MatchesListSerializer;

/**
 * Represents a list of World vs World matches as is returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/wvw/matches">/v1/wvw/matches</a>.
 */
@JsonDeserialize(using = MatchesListDeserializer.class)
@JsonSerialize(using = MatchesListSerializer.class)
@Gw2ApiVersion("v1")
public class MatchesList extends ArrayList<MatchInfo> {
  public MatchesList() {
    super(0);
  }
}