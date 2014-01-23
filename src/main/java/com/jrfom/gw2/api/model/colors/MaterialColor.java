package com.jrfom.gw2.api.model.colors;

import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * <p>Represents an {@link com.jrfom.gw2.api.model.colors.Color} after it has
 * been applied to an in-game material ("cloth", "leather", or "metal"). See
 * <a href="http://wiki.guildwars2.com/wiki/API:1/colors">/v1/colors</a> for
 * more information.
 */
@Gw2ApiVersion("v1")
public class MaterialColor {
  int brightness;
  double contrast;
  int hue;
  double saturation;
  double lightness;
  RGB rgb;

  MaterialColor() {};

  public int getBrightness() {
    return this.brightness;
  }

  public void setBrightness(int brightness) {
    this.brightness = brightness;
  }

  public double getContrast() {
    return this.contrast;
  }

  public void setContrast(double contrast) {
    this.contrast = contrast;
  }

  public int getHue() {
    return this.hue;
  }

  public void setHue(int hue) {
    this.hue = hue;
  }

  public double getSaturation() {
    return this.saturation;
  }

  public void setSaturation(double saturation) {
    this.saturation = saturation;
  }

  public double getLightness() {
    return this.lightness;
  }

  public void setLightness(double lightness) {
    this.lightness = lightness;
  }

  public RGB getRgb() {
    return this.rgb;
  }

  public void setRgb(RGB rgb) {
    this.rgb = rgb;
  }
}