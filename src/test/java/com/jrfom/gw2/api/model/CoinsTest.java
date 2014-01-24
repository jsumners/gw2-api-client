package com.jrfom.gw2.api.model;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class CoinsTest {
  private static final Logger log = LoggerFactory.getLogger(CoinsTest.class);

  @Test
  public void testEmptyConstructor() {
    log.info("Testing Coins empty constructor");
    Coins coins = new Coins();
    assertTrue(coins.getValue() == 0);
    assertTrue(coins.goldValue() == 0);
    assertTrue(coins.silverValue() == 0);
    assertTrue(coins.copperValue() == 0);
  }

  @Test
  public void testNonemptyConstructor() {
    log.info("Testing Coins non-empty constructor");
    Coins coins = new Coins(50);
    assertTrue(coins.getValue() == 50);
    assertTrue(coins.goldValue() == 0);
    assertTrue(coins.silverValue() == 0);
    assertTrue(coins.copperValue() == 50);

    coins = new Coins(100);
    assertTrue(coins.getValue() == 100);
    assertTrue(coins.goldValue() == 0);
    assertTrue(coins.silverValue() == 1);
    assertTrue(coins.copperValue() == 0);

    coins = new Coins(10000);
    assertTrue(coins.getValue() == 10000);
    assertTrue(coins.goldValue() == 1);
    assertTrue(coins.silverValue() == 0);
    assertTrue(coins.copperValue() == 0);

    coins.setAmount(10000);
    assertTrue(coins.getValue() == 10000);
    assertTrue(coins.goldValue() == 1);
    assertTrue(coins.silverValue() == 0);
    assertTrue(coins.copperValue() == 0);
  }

  @Test
  public void testCoinMaths() {
    log.info("Testing Coins math abilities");
    Coins coins = new Coins();
    coins.setAmount(1, 50, 25);

    assertTrue(coins.getValue() == 15025);
    assertTrue(coins.goldValue() == 1);
    assertTrue(coins.silverValue() == 50);
    assertTrue(coins.copperValue() == 25);

    coins.addCopper(15);
    assertTrue(coins.copperValue() == 40);

    coins.addCopper(60);
    assertTrue(coins.copperValue() == 0);
    assertTrue(coins.silverValue() == 51);

    coins.addSilver(9);
    assertTrue(coins.silverValue() == 60);

    coins.addSilver(40);
    assertTrue(coins.silverValue() == 0);
    assertTrue(coins.goldValue() == 2);

    coins.addGold(1);
    assertTrue(coins.goldValue() == 3);

    coins.subtractGold(1);
    assertTrue(coins.goldValue() == 2);

    coins.subtractSilver(49);
    assertTrue(coins.silverValue() == 51);

    coins.subtractCopper(60);
    assertTrue(coins.copperValue() == 40);
  }
}