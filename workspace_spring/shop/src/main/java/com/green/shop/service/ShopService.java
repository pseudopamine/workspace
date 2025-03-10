package com.green.shop.service;

import com.green.shop.dto.ShopDTO;

import java.util.List;

// 핵심 기능을 정의한 인터페이스
// 핵심 기능 : 쿼리 작업

public interface ShopService {

  //1. 상품 하나를 등록하는 기능을 실행시킬 메서드
  public int insertItem(ShopDTO shopDTO);

  //2. 모든 상품의 상품번호, 상품명, 가격, 상품등록일을 조회하는 기능을 실행시킬 메서드
  public List<ShopDTO> selectItemList();

  //3. 상품번호를 통해 해당 상품의 모든 컬럼을 조회하는 기능을 실행시킬 메서드
  public ShopDTO selectItem(int itemCode);

  //4. 특정 상품 번호에 해당하는 상품을 삭제하는 기능을 실행시킬 메서드
  public void deleteItem(int itemCode);

  //5. 특정 상품 번호에 해당하는 상품의 상품명, 가격, 상품설명을 수정하는 기능을 실행시킬 메서드
  public void updateItem(ShopDTO shopDTO);
}
