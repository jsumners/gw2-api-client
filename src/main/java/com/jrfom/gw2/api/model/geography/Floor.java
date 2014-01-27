package com.jrfom.gw2.api.model.geography;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;
import com.jrfom.gw2.api.model.Dimension;

/**
 * Represents an in-game map floor as returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/map_floor">/v1/map_floor</a>.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Gw2ApiVersion("v1")
public class Floor {
  @JsonProperty("texture_dims")
  private Dimension textureDimensions;
  @JsonProperty("clamped_view")
  private MapRectangle clampedView;
  private RegionList regions;

  public Floor() {}

  /**
   * The width and height of the map texture.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.Dimension}.
   */
  public Dimension getTextureDimensions() {
    return this.textureDimensions;
  }

  public void setTextureDimensions(Dimension textureDimensions) {
    this.textureDimensions = textureDimensions;
  }

  // TODO: find an instance of "clamped view" and add proper support for it
  /**
   * <p>According to the wiki:</p>
   *
   * <blockquote>"If present, it represents a rectangle of downloadable
   * textures. Every tile coordinate outside this rectangle is not available
   * on the tile server."</blockquote>
   *
   * <p>As far as the author of this model can discern, it's nothing more
   * than an instance of {@link com.jrfom.gw2.api.model.geography.MapRectangle}.</p>
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.MapRectangle}.
   */
  public MapRectangle getClampedView() {
    return this.clampedView;
  }

  public void setClampedView(MapRectangle clampedView) {
    this.clampedView = clampedView;
  }

  /**
   * Retrieve the list of {@link com.jrfom.gw2.api.model.geography.Region}s
   * contained in the floor.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.geography.RegionList}.
   */
  public RegionList getRegions() {
    return this.regions;
  }

  public void setRegions(RegionList regions) {
    this.regions = regions;
  }
}