package com.example.java.gettingstarted;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Car {
  @Id public String vin; // Can be Long, long, or String
  public String color;

  public Car(String vin, String color) {
    this.vin = vin;
    this.color = color;
  }
}
