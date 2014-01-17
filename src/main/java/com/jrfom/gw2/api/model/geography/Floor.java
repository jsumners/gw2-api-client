package com.jrfom.gw2.api.model.geography;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.Dimension;

@Gw2ApiVersion("v1")
public class Floor {
  @JsonProperty("texture_dims")
  private Dimension textureDimensions;
  @JsonProperty("clamped_view")
  private MapRectangle clampedView;
  private RegionList regions;

  public Floor() {}

  public Dimension getTextureDimensions() {
    return this.textureDimensions;
  }

  public void setTextureDimensions(Dimension textureDimensions) {
    this.textureDimensions = textureDimensions;
  }

  public Object getClampedView() {
    return this.clampedView;
  }

  public void setClampedView(MapRectangle clampedView) {
    this.clampedView = clampedView;
  }

  public RegionList getRegions() {
    return this.regions;
  }

  public void setRegions(RegionList regions) {
    this.regions = regions;
  }
}