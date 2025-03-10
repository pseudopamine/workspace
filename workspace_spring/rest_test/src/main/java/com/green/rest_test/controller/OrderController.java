package com.green.rest_test.controller;

import com.green.rest_test.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {

  //1. 모든 주문정보를 조회하는 기능을 제공하는 REST API
  @GetMapping("")
  public List<OrderDTO> getOrderList(){
    List<OrderDTO> orderDTO = new ArrayList<>();

    orderDTO.add(new OrderDTO(101, "데님 청바지", 15000, 2, "abc"));
    orderDTO.add(new OrderDTO(252, "맨투맨 반팔 티셔츠", 10000, 3, "JIN"));
    orderDTO.add(new OrderDTO(83, "오버핏 니트", 25000, 2, "HAN"));
    orderDTO.add(new OrderDTO(274, "롱 패딩", 55000, 1, "HONG"));
    orderDTO.add(new OrderDTO(235, "맨투맨 긴팔 티셔츠", 12000, 3, "KIM"));

    return orderDTO;
  }

  //2. 하나의 주문정보를 조회하는 기능을 제공하는 REST API
  @GetMapping("/{itemCode}")
  public void getOrder(@PathVariable("itemCode") int itemCode){
    log.info("하나의 주문정보 조회");
    log.info("itemCode = " + itemCode);
  }

  //3. 하나의 주문정보를 등록하는 기능을 제공하는 REST API
  @PostMapping("")
  public void insertOrder(@RequestBody OrderDTO orderDTO){
    log.info("하나의 주문정보 등록");
    log.info(orderDTO.toString());

  }

  //4. 하나의 주문정보를 삭제하는 기능을 제공하는 REST API
  @DeleteMapping("{itemCode}")
  public void deleteOrder(@PathVariable("itemCode") int itemCode){
    log.info("하나의 주문정보를 삭제");
    log.info("itemCode = " + itemCode);
  }

  //5. 하나의 주문정보에서 상품명과 상품가격을 수정하는 기능을 제공하는 REST API
  @PutMapping("/{itemCode}")
  public void updateOrder(@PathVariable("itemCode") int itemCode, @RequestBody OrderDTO orderDTO){
    log.info("상품명과 상품가격을 수정");
    log.info("itemCode = " + itemCode);
    log.info(orderDTO.toString());
  }




}
