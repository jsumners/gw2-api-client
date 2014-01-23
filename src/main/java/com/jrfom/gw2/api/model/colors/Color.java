package com.jrfom.gw2.api.model.colors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents a color as returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/colors">/v1/colors.json</a>.
 * This representation includes the color's numeric identifier as a property of
 * the {@linkplain Color} object.
 */
@Gw2ApiVersion("v1")
public class Color {
  @JsonIgnore
  private int colorId;
  private String name;
  @JsonProperty("base_rgb")
  private RGB baseRgb;
  private MaterialColor cloth;
  private MaterialColor leather;
  private MaterialColor metal;

  public Color() {}

  /**
   * Retrieve the color's numeric identifier.
   *
   * @return An {@code int} identifier for the color.
   */
  public int getColorId() {
    return this.colorId;
  }

  public void setColorId(int id) {
    this.colorId = id;
  }

  /**
   * Retrieve the color's in-game name.
   *
   * @return A string name for the color.
   */
  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Retrieve a triplet that represents the basic color value before it is
   * modified with materials.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.colors.RGB}.
   */
  public RGB getBaseRgb() {
    return this.baseRgb;
  }

  public void setBaseRgb(RGB baseRgb) {
    this.baseRgb = baseRgb;
  }

  /**
   * Retrieve details that describe how the
   * base RGB ({@link com.jrfom.gw2.api.model.colors.Color#getBaseRgb()}) is
   * modified when it is applied to an in-game "cloth" material.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.colors.MaterialColor}.
   */
  public MaterialColor getCloth() {
    return this.cloth;
  }

  public void setCloth(MaterialColor cloth) {
    this.cloth = cloth;
  }

  /**
   * Retrieve details that describe how the
   * base RGB ({@link com.jrfom.gw2.api.model.colors.Color#getBaseRgb()}) is
   * modified when it is applied to an in-game "leather" material.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.colors.MaterialColor}.
   */
  public MaterialColor getLeather() {
    return this.leather;
  }

  public void setLeather(MaterialColor leather) {
    this.leather = leather;
  }

  /**
   * Retrieve details that describe how the
   * base RGB ({@link com.jrfom.gw2.api.model.colors.Color#getBaseRgb()}) is
   * modified when it is applied to an in-game "metal" material.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.colors.MaterialColor}.
   */
  public MaterialColor getMetal() {
    return this.metal;
  }

  public void setMetal(MaterialColor metal) {
    this.metal = metal;
  }
}