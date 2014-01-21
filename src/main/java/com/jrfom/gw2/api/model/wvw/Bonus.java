package com.jrfom.gw2.api.model.wvw;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.jackson.deserializers.WvwBonusDeserializer;

@JsonSubTypes({
  @JsonSubTypes.Type(BloodlustBonus.class)
})
@JsonDeserialize(using = WvwBonusDeserializer.class)
@Gw2ApiVersion("v1")
public interface Bonus {
  String getType();
  String getOwner();
}
