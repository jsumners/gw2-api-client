package com.jrfom.gw2.api.model.colors;

import java.util.ArrayList;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Optional;
import com.jrfom.gw2.jackson.deserializers.ColorsListDeserializer;
import com.jrfom.gw2.jackson.serializers.ColorsListSerializer;

/**
 * Represents the list of colors returned by /v1/colors.json. The list is
 * represented by an {@link java.util.ArrayList} of
 * {@link Color} instances.
 */
@JsonDeserialize(using = ColorsListDeserializer.class)
@JsonSerialize(using = ColorsListSerializer.class)
public class ColorsList extends ArrayList<Color> {
  public ColorsList(int capacity) {
    super(capacity);
  }

  /**
   * Retrieve a {@link Color} from the list based on
   * the numeric identifier of that color.
   *
   * @param id The numeric identifier for the desired color.
   *
   * @return An empty {@link com.google.common.base.Optional} if the color is
   * not found.
   */
  public Optional<Color> getColorWithId(int id) {
    Optional<Color> result = Optional.absent();

    for (Color color : this) {
      if (color.getColorId() == id) {
        result = Optional.of(color);
        break;
      }
    }

    return result;
  }

  /**
   * Retrieve a {@link Color} from the list based on
   * the name of that color.
   *
   * @param name The name of the desired color.
   *
   * @return An empty {@link com.google.common.base.Optional} if the color is
   * not found.
   */
  public Optional<Color> getColorWithName(String name) {
    Optional<Color> result = Optional.absent();

    for (Color color : this) {
      if (color.getName().equals(name)) {
        result = Optional.of(color);
        break;
      }
    }

    return result;
  }
}