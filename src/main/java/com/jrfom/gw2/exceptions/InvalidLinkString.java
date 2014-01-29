package com.jrfom.gw2.exceptions;

/**
 * Thrown when a {@link com.jrfom.gw2.util.ChatLink} is provided
 * with an invalid link string. The proper format is "[&AgEAWgAA]".
 */
public class InvalidLinkString extends RuntimeException {
  public InvalidLinkString() {
    this("The provided string was not formatted correctly.");
  }

  public InvalidLinkString(String message) {
    super(message);
  }
}