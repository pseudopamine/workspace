package com.example.react_shop.service;

import com.example.react_shop.dto.ShopDTO;

import java.util.List;

public interface ShopService {

  //상품 등록 기능
  public void insertItem(ShopDTO shopDTO);

  //모든 상품 조회 기능
  public List<ShopDTO> selectItemList(ShopDTO shopDTO);

  //특정 상품 상세 조회 기능
  public ShopDTO selectItem(int itemNum);

  //특정 상품 수정 기능
  public void updateItem(ShopDTO shopDTO);

}
