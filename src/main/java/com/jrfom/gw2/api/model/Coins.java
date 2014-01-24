package com.jrfom.gw2.api.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jrfom.gw2.jackson.serializers.CoinsToValueSerializer;

/**
 * <p>Represents in-game currency. This object handles all currency conversion.
 * For example, given 15310 copper:</p>
 *
 * <pre>
 * {@code
 * Coins coins = new Coins(15310);
 * System.out.println(coins.goldValue()); // 1.0
 * System.out.println(coins.silverValue()); // 53.0
 * System.out.println(coins.copperValue()); // 10.0
 * }
 * </pre>
 */
@JsonSerialize(using = CoinsToValueSerializer.class)
public class Coins {
  private double amount;

  /**
   * Create an instance with the value set to {@code 0}.
   */
  public Coins() {
    this.amount = 0;
  }

  /**
   * Create an instance with the value set to {@code amount}.
   *
   * @param amount The initial number of coins in "copper".
   */
  public Coins(int amount) {
    this.amount = amount;
  }

  /**
   * Same as {@link com.jrfom.gw2.api.model.Coins#Coins(int)}.
   * Only used for deserialization.
   *
   * @param amount The initial number of coins in "copper".
   */
  public Coins(String amount) {
    this.amount = Double.parseDouble(amount);
  }

  /**
   * Retrieve the actual value the instance represents. For example,
   * if you initialized the object with 15310 coins, and didn't add
   * or subtract any afterward, then this method will return 15310.
   *
   * @return The actual value of coins the instance represents.
   */
  public double getValue() {
    return this.amount;
  }

  /**
   * The number of in-game gold the instance represents.
   *
   * @return A number representing the gold (e.g. 1.0).
   */
  public double goldValue() {
    return Math.floor(this.amount / 10000);
  }

  /**
   * The number of in-game silver the instance represents.
   *
   * @return A number representing the silver (e.g. 53.0).
   */
  public double silverValue() {
    return Math.floor(this.amount / 100) % 100;
  }

  /**
   * The number of in-game copper the instance represents.
   *
   * @return A number representing the copper (e.g. 10.0).
   */
  public double copperValue() {
    return (this.amount % 1000) % 100;
  }

  /**
   * Increase the instance's value by the specified number of
   * in-game gold.
   *
   * @param gold A number of gold coins.
   */
  public void addGold(int gold) {
    this.amount = this.amount + (gold * 10000);
  }

  /**
   * Increase the instance's value by the specified number of
   * in-game silver.
   *
   * @param silver A number of silver coins.
   */
  public void addSilver(int silver) {
    this.amount = this.amount + (silver * 100);
  }

  /**
   * Increase the instance's value by the specified number of
   * in-game copper.
   *
   * @param copper A number of copper coins.
   */
  public void addCopper(int copper) {
    this.amount = this.amount + copper;
  }

  /**
   * Decrease the instance's value by the specified number of
   * in-game gold.
   *
   * @param gold A number of gold coins.
   */
  public void subtractGold(int gold) {
    this.addGold(-1 * gold);
  }

  /**
   * Decrease the instance's value by the specified number of
   * in-game silver.
   *
   * @param silver A number of silver coins.
   */
  public void subtractSilver(int silver) {
    this.addSilver(-1 * silver);
  }

  /**
   * Decrease the instance's value by the specified number of
   * in-game copper.
   *
   * @param copper A number of copper coins.
   */
  public void subtractCopper(int copper) {
    this.addCopper(-1 * copper);
  }

  /**
   * Set the instance's actual value to a specific number of coins. This
   * overwrites any previously stored amount.
   *
   * @param amount The amount of coins this instance should represent.
   */
  public void setAmount(int amount) {
    this.amount = amount;
  }

  /**
   * Set the instance's actual value to a specific number of coins as
   * represented by the in-game currency denominations. This overwrites
   * any previously stored value.
   *
   * @param gold The number of gold coins this instance should represent.
   * @param silver The number of silver coins this instance should represent.
   * @param copper The number of copper coins this instance should represent.
   */
  public void setAmount(int gold, int silver, int copper) {
    this.amount = 0;
    this.addGold(gold);
    this.addSilver(silver);
    this.addCopper(copper);
  }
}