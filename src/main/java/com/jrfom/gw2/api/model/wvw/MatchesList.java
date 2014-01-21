package com.jrfom.gw2.api.model.wvw;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.jackson.deserializers.MatchesListDeserializer;
import com.jrfom.gw2.jackson.serializers.MatchesListSerializer;

@JsonDeserialize(using = MatchesListDeserializer.class)
@JsonSerialize(using = MatchesListSerializer.class)
@Gw2ApiVersion("v1")
public class MatchesList extends ArrayList<MatchInfo> {
  public MatchesList() {
    super(0);
  }
}