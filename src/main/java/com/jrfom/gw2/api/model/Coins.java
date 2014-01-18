package com.jrfom.gw2.api.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.jrfom.gw2.jackson.serializers.CoinsToValueSerializer;

@JsonSerialize(using = CoinsToValueSerializer.class)
public class Coins {
  private double amount;

  public Coins() {
    this.amount = 0;
  }

  public Coins(int amount) {
    this.amount = amount;
  }

  public Coins(String amount) {
    this.amount = Double.parseDouble(amount);
  }

  public double getValue() {
    return this.amount;
  }

  public double goldValue() {
    return Math.floor(this.amount / 10000);
  }

  public double silverValue() {
    return Math.floor(this.amount / 100) % 100;
  }

  public double copperValue() {
    return (this.amount % 1000) % 100;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public void addGold(int gold) {
    this.amount = this.amount + (gold * 10000);
  }

  public void addSilver(int silver) {
    this.amount = this.amount + (silver * 100);
  }

  public void addCopper(int copper) {
    this.amount = this.amount + copper;
  }

  public void setAmount(int gold, int silver, int copper) {
    this.addGold(gold);
    this.addSilver(silver);
    this.addCopper(copper);
  }
}