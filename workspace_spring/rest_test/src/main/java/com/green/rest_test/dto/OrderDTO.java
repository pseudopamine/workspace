package com.green.rest_test.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderDTO {
  private int itemCode;
  private String itemName;
  private int itemPrice;
  private int itemCount;
  private String orderId;
  private int buyPrice;

  public OrderDTO() {
  }

  public OrderDTO(int itemCode, String itemName, int itemPrice, int itemCount, String orderId) {
    this.itemCode = itemCode;
    this.itemName = itemName;
    this.itemPrice = itemPrice;
    this.itemCount = itemCount;
    this.orderId = orderId;
  }
}
