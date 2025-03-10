package com.example.react_shop.service;

import com.example.react_shop.dto.ShopDTO;
import com.example.react_shop.mapper.ShopMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService{
  private final ShopMapper shopMapper;


  @Override
  public void insertItem(ShopDTO shopDTO) {
    shopMapper.insertItem(shopDTO);
  }

  @Override
  public List<ShopDTO> selectItemList(ShopDTO shopDTO) {
    return shopMapper.selectItemList(shopDTO);
  }

  @Override
  public ShopDTO selectItem(int itemNum) {
    return shopMapper.selectItem(itemNum);
  }

  @Override
  public void updateItem(ShopDTO shopDTO) {
    shopMapper.updateItem(shopDTO);
  }
}
