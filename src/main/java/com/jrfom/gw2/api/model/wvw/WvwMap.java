package com.jrfom.gw2.api.model.wvw;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.jackson.deserializers.WvwMapDeserializer;

@JsonSubTypes({
  @JsonSubTypes.Type(RedHomeMap.class),
  @JsonSubTypes.Type(BlueHomeMap.class),
  @JsonSubTypes.Type(GreenHomeMap.class),
  @JsonSubTypes.Type(EternalBattleGroundsMap.class)
})
@JsonDeserialize(using = WvwMapDeserializer.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Gw2ApiVersion("v1")
public interface WvwMap {
  String getType();
  MatchScores getScores();
  ArrayList<Objective> getObjectives();
  ArrayList<Bonus> getBonuses();
}