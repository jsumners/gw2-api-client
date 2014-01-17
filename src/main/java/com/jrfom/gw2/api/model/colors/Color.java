package com.jrfom.gw2.api.model.colors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents a color as returned by /v1/colors.json. This representation
 * includes the color's numeric identifier as a property of the
 * {@linkplain Color} object.
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

  public int getColorId() {
    return this.colorId;
  }

  public void setColorId(int id) {
    this.colorId = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RGB getBaseRgb() {
    return this.baseRgb;
  }

  public void setBaseRgb(RGB baseRgb) {
    this.baseRgb = baseRgb;
  }

  public MaterialColor getCloth() {
    return this.cloth;
  }

  public void setCloth(MaterialColor cloth) {
    this.cloth = cloth;
  }

  public MaterialColor getLeather() {
    return this.leather;
  }

  public void setLeather(MaterialColor leather) {
    this.leather = leather;
  }

  public MaterialColor getMetal() {
    return this.metal;
  }

  public void setMetal(MaterialColor metal) {
    this.metal = metal;
  }
}