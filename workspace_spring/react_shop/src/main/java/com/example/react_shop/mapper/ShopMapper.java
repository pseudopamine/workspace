package com.example.react_shop.mapper;

import com.example.react_shop.dto.ShopDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShopMapper {

  //상품 등록 쿼리
  public void insertItem(ShopDTO shopDTO);

  //모든 상품 조회 쿼리
  public List<ShopDTO> selectItemList(ShopDTO shopDTO);

  //특정 상품 상세 조회 쿼리
  public ShopDTO selectItem(int itemNum);

  //특정 상품 수정 쿼리
  public void updateItem(ShopDTO shopDTO);

}
