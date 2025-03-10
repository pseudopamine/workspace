package com.example.react_shop.service;

import com.example.react_shop.dto.OrderDTO;

import java.util.List;

public interface OrderService {

  //주문 등록 기능
  public void insertOrder(OrderDTO orderDTO);

  //주문 목록 조회 기능
  public List<OrderDTO> selectOrderList();
}
