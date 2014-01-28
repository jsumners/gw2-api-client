package com.jrfom.gw2.api.model;

import java.util.HashMap;

import com.google.common.base.Optional;

/**
 * Represents a list of {@link com.jrfom.gw2.api.model.GameAssetFile}s as is
 * returned by
 * <a href="http://wiki.guildwars2.com/wiki/API:1/files">/v1/files</a>.
 */
public class Files extends HashMap<String, GameAssetFile> {
  public Files() {
    super(0);
  }

  /**
   * Retrieve a file that has the specified {@code id}.
   *
   * @param id A valid file identifier.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.GameAssetFile}
   * wrapped in an {@link com.google.common.base.Optional} or an empty
   * {@code Optional}.
   */
  public Optional<GameAssetFile> getFileWithId(int id) {
    Optional<GameAssetFile> result = Optional.absent();

    for (GameAssetFile file : this.values()) {
      if (file.getFileId() == id) {
        result = Optional.of(file);
        break;
      }
    }

    return result;
  }

  /**
   * Retrieve a file that has the specified {@code signature}.
   *
   * @param signature A valid file signature.
   *
   * @return An instance of {@link com.jrfom.gw2.api.model.GameAssetFile}
   * wrapped in an {@link com.google.common.base.Optional} or an empty
   * {@code Optional}.
   */
  public Optional<GameAssetFile> getFileWithSignature(String signature) {
    Optional<GameAssetFile> result = Optional.absent();

    for (GameAssetFile file : this.values()) {
      if (file.getSignature().equals(signature)) {
        result = Optional.of(file);
        break;
      }
    }

    return result;
  }
}