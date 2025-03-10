package com.green.shop.mapper;

import com.green.shop.dto.ShopDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//객체생성 + 해당 파일에 쿼리를 실행시킬 메서드가 정의되어 있다는 것을 인지시켜줌

//메서드의 리턴타입 : 쿼리 실행 결과를 가져올 자료형
@Mapper
public interface ShopMapper {

  //1. 상품 하나를 등록하는 쿼리를 실행시킬 메서드
  public int insertItem(ShopDTO shopDTO);

  //2. 모든 상품의 상품번호, 상품명, 가격, 상품등록일을 조회하는 쿼리를 실행시킬 메서드
  public List<ShopDTO> selectItemList();

  //3. 상품번호를 통해 해당 상품의 모든 컬럼을 조회하는 쿼리를 실행시킬 메서드
  public ShopDTO selectItem(int itemCode);

  //4. 특정 상품 번호에 해당하는 상품을 삭제하는 쿼리를 실행시킬 메서드
  public void deleteItem(int itemCode);

  //5. 특정 상품 번호에 해당하는 상품의 상품명, 가격, 상품설명을 수정하는 쿼리를 실행시킬 메서드
  public void updateItem(ShopDTO shopDTO);
}
