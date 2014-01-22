package com.jrfom.gw2.api.model;

import com.jrfom.gw2.annotations.Gw2ApiVersion;

/**
 * Represents the error object returned from the Guild Wars 2 API when an
 * invalid parameter is passed to a method. This error will be thrown whenever
 * such a response is received from the remote service. You can either ignore
 * it, or use a {@code try...catch} to trap it.
 */
@Gw2ApiVersion("v1")
public class GwApiError extends Error {
  private int error;
  private int product;
  private int module;
  private int line;
  private String text;

  public GwApiError() {}

  /**
   * Retrieve the error number associated with the instance.
   *
   * @return A generic error number.
   */
  public int getError() {
    return this.error;
  }

  public void setError(int error) {
    this.error = error;
  }

  /**
   * Retrieve the product identifier associated with the instance.
   *
   * @return Some product id used by ArenaNet.
   */
  public int getProduct() {
    return this.product;
  }

  public void setProduct(int product) {
    this.product = product;
  }

  /**
   * Retrieve the module identifier associated with the instance.
   *
   * @return Some module id used by ArenaNet.
   */
  public int getModule() {
    return this.module;
  }

  public void setModule(int module) {
    this.module = module;
  }

  /**
   * Retrieve the line number in ArenaNet's remote code that generated
   * this error.
   *
   * @return The ArenaNet code's line number.
   */
  public int getLine() {
    return this.line;
  }

  public void setLine(int line) {
    this.line = line;
  }

  /**
   * Retrieve a text description of the error.
   *
   * @return The error description as returned by ArenaNet.
   */
  public String getText() {
    return this.text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public String getMessage() {
    return this.text;
  }
}