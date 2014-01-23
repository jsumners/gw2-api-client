package com.jrfom.gw2.api.model.items;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.Coins;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Gw2ApiVersion("v1")
public interface Item {
  @JsonProperty("item_id")
  Integer getItemId();

  @JsonProperty("name")
  String getName();

  @JsonProperty("description")
  String getDescription();

  @JsonProperty("type")
  String getType();

  @JsonProperty("level")
  Integer getLevel();

  @JsonProperty("rarity")
  String getRarity();

  @JsonProperty("vendor_value")
  Coins getVendorValue();

  @JsonProperty("icon_file_id")
  Integer getIconFileId();

  @JsonProperty("icon_file_signature")
  String getIconFileSignature();

  @JsonProperty("game_types")
  List<String> getGameTypes();

  @JsonProperty("flags")
  List<String> getFlags();

  @JsonProperty("restrictions")
  List<String> getRestrictions();
}
