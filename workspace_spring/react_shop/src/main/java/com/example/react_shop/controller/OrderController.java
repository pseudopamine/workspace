package com.example.react_shop.controller;

import com.example.react_shop.dto.OrderDTO;
import com.example.react_shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
  private final OrderService orderService;

  //주문 등록 기능 API
  @PostMapping("")
  public void insertOrder(@RequestBody OrderDTO orderDTO){
    orderService.insertOrder(orderDTO);
  }

  //주문 목록 조회 기능 API
  @GetMapping("")
  public List<OrderDTO> getOrderList(){
    return orderService.selectOrderList();
  }


}
