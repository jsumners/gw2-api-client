package com.jrfom.gw2.api.model.colors;

import java.util.ArrayList;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class RGBTest {
  private static final Logger log = LoggerFactory.getLogger(RGBTest.class);

  @Test
  public void emptyConstructor() {
    log.info("Running RGB empty constructor test");
    RGB rgb = new RGB();

    assertTrue(rgb.size() == 3);
    assertTrue(rgb.getRed() == -1);
    assertTrue(rgb.getGreen() == -1);
    assertTrue(rgb.getBlue() == -1);
  }

  @Test
  public void nonemptyConstructor() {
    log.info("Running RGB non-empty constructor test");
    ArrayList<Integer> list = new ArrayList<>(3);
    list.add(1);
    list.add(2);
    list.add(3);

    RGB rgb = new RGB(list);

    assertTrue(rgb.size() == 3);
    assertTrue(rgb.getRed() == 1);
    assertTrue(rgb.getGreen() == 2);
    assertTrue(rgb.getBlue() == 3);
  }

  @Test
  public void getterSetters() {
    log.info("Running RGB getters and setter test");
    RGB rgb = new RGB();

    rgb.setRed(1);
    assertTrue(rgb.getRed() == 1);

    rgb.setGreen(2);
    assertTrue(rgb.getGreen() == 2);

    rgb.setBlue(3);
    assertTrue(rgb.getBlue() == 3);
  }
}