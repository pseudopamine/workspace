package com.example.react_shop.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ShopDTO {
  private int itemNum;
  private String itemName;
  private int itemPrice;
  private String seller;
  private String itemIntro;
  private LocalDateTime regDate;
}
