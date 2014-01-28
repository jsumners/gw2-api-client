package com.jrfom.gw2.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents an in-game asset file as returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/files">/v1/files</a>.
 */
@Gw2ApiVersion("v1")
public class GameAssetFile {
  @JsonProperty("file_id")
  private int fileId;
  private String signature;

  public GameAssetFile() {}

  /**
   * The unique identifier for the file.
   *
   * @return An integer identifier.
   */
  public int getFileId() {
    return this.fileId;
  }

  public void setFileId(int fileId) {
    this.fileId = fileId;
  }

  /**
   * The unique hash that represents the file.
   *
   * @return A string hash signature.
   */
  public String getSignature() {
    return this.signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  /**
   * A URL for fetching the file as a JPG from the render service.
   *
   * @return A string URL.
   */
  @JsonIgnore
  public String renderServiceJpgUrl() {
    return this.renderServiceUrl("jpg");
  }

  /**
   * A URL for fetching the file as a PNG from the render service.
   *
   * @return A string URL.
   */
  @JsonIgnore
  public String renderServicePngUrl() {
    return this.renderServiceUrl("png");
  }

  /**
   * A URL for fetching the file from the render service.
   *
   * @param type The type of file to fetch: "png" or "jpg".
   *
   * @return A string URL.
   */
  @JsonIgnore
  public String renderServiceUrl(String type) {
    return String.format(
      "https://render.guildwars2.com/file/%s/%s.%s",
      this.signature,
      this.fileId,
      type
    );
  }
}