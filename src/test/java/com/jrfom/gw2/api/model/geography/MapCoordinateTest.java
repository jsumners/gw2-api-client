package com.jrfom.gw2.api.model.geography;

import java.util.ArrayList;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class MapCoordinateTest {
  private static final Logger log = LoggerFactory.getLogger(MapCoordinateTest.class);

  @Test
  public void emptyConstructorTest() {
    log.info("Running MapCoordinate empty constructor test");
    MapCoordinate coordinate = new MapCoordinate();

    assertTrue(coordinate.size() == 2);
    assertTrue(coordinate.getX().intValue() == Integer.MIN_VALUE);
    assertTrue(coordinate.getY().intValue() == Integer.MIN_VALUE);
  }

  @Test
  public void nonemptyConstructorTest() {
    log.info("Running MapCoordinate non-empty constructor test");
    ArrayList<? extends Integer> list = new ArrayList<Integer>() {{
      add(1);
      add(2);
    }};
    MapCoordinate coordinate = new MapCoordinate(list);
    assertTrue(coordinate.size() == 2);
    assertTrue(coordinate.getX() == 1);
    assertTrue(coordinate.getY() == 2);
  }

  @Test
  public void floatTest() {
    log.info("Running MapCoordinate floating point test");
    MapCoordinate mapCoordinate = new MapCoordinate();
    mapCoordinate.setX(1.01);
    mapCoordinate.setY(2.02);

    assertTrue(mapCoordinate.getX().doubleValue() == 1.01);
    assertTrue(mapCoordinate.getY().doubleValue() == 2.02);
  }
}