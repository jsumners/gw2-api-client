package com.jrfom.gw2.api.model.wvw;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.jackson.deserializers.WvwMapDeserializer;

/**
 * <p>Represents the status of a World vs World map as returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/wvw/match_details">/v1/wvw/match_details</a>.</p>
 *
 * <p>The available map types are:</p>
 *
 * <ul>
 *   <li>{@link com.jrfom.gw2.api.model.wvw.RedHomeMap}</li>
 *   <li>{@link com.jrfom.gw2.api.model.wvw.BlueHomeMap}</li>
 *   <li>{@link com.jrfom.gw2.api.model.wvw.GreenHomeMap}</li>
 *   <li>{@link com.jrfom.gw2.api.model.wvw.EternalBattleGroundsMap}</li>
 * </ul>
 */
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
  /**
   * <p>The type of WvW map. Can be any of following:</p>
   *
   * <ul>
   *   <li>RedHome</li>
   *   <li>GreenHome</li>
   *   <li>BlueHome</li>
   *   <li>Center</li>
   * </ul>
   *
   * @return A string map type.
   */
  String getType();

  /**
   * The scores for each team on this specific map.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.wvw.MatchScores}.
   */
  MatchScores getScores();

  /**
   * A list of {@link com.jrfom.gw2.api.model.wvw.Objective}s, and their
   * statuses, for this specific map.
   *
   * @return An array of {@link com.jrfom.gw2.api.model.wvw.Objective}
   * instances.
   */
  ArrayList<Objective> getObjectives();

  /**
   * A list of bonuses being granted on this map.
   *
   * @return An array of {@link com.jrfom.gw2.api.model.wvw.Bonus} instances or
   * an empty array if no bonuses are being granted.
   */
  ArrayList<Bonus> getBonuses();
}