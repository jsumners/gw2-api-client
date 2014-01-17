package com.jrfom.gw2.annotations;

import java.lang.annotation.*;

/**
 * This annotation is to be used on classes that describe Guild Wars 2
 * API results. That is, any class that models a GW2 API result should include
 * this annotation to describe the version of the API it is modeled for.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Gw2ApiVersion {
  /**
   * Defines the version of the Guild Wars 2 API. For example,
   * "v1" for the first version of the API. "v1" is also the
   * default value.
   *
   * @return A Guild Wars 2 API version string.
   */
  String value() default "v1";
}
