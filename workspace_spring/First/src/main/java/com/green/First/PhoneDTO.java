package com.green.First;

public class PhoneDTO {
  private String brand;
  private int price;
  private String color;

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  @Override
  public String toString() {
    return "PhoneDTO{" +
            "brand='" + brand + '\'' +
            ", price=" + price +
            ", color='" + color + '\'' +
            '}';
  }



}
