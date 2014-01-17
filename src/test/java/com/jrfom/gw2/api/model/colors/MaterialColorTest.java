package com.jrfom.gw2.api.model.colors;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class MaterialColorTest {
  private static final Logger log = LoggerFactory.getLogger(MaterialColorTest.class);

  @Test
  public void materialColorTest() {
    log.info("Running material color test");

    MaterialColor materialColor = new MaterialColor();
    materialColor.brightness = 1;
    materialColor.contrast = 0.314;
    materialColor.hue = 1;
    materialColor.lightness = 0.314;
    materialColor.rgb = new RGB();
    materialColor.saturation = 0.314;

    assertTrue(materialColor.brightness == 1);
    assertTrue(materialColor.contrast == 0.314);
    assertTrue(materialColor.hue == 1);
    assertTrue(materialColor.lightness == 0.314);
    assertTrue(materialColor.rgb.getRed() == -1);
    assertTrue(materialColor.rgb.getGreen() == -1);
    assertTrue(materialColor.rgb.getBlue() == -1);
    assertTrue(materialColor.saturation == 0.314);
  }
}