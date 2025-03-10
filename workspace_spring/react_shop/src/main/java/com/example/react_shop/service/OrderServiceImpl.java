package com.example.react_shop.service;

import com.example.react_shop.dto.OrderDTO;
import com.example.react_shop.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
  private final OrderMapper orderMapper;


  @Override
  public void insertOrder(OrderDTO orderDTO) {
    orderMapper.insertOrder(orderDTO);
  }

  @Override
  public List<OrderDTO> selectOrderList() {
    return orderMapper.selectOrderList();
  }
}
