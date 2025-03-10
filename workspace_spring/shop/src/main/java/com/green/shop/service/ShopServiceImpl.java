package com.green.shop.service;

import com.green.shop.dto.ShopDTO;
import com.green.shop.mapper.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


//핵심 기능을 구현 : 핵심 기능은 쿼리 실행
//쿼리를 실행 하기 위해서는 mapper interface의 객체가 있어야 함
@Service
public class ShopServiceImpl implements ShopService {
  private ShopMapper shopMapper;  //shopMapper의 객체 선언

  @Autowired  //shopMapper의 객체를 생성하기 위해 생성자 주입
  public ShopServiceImpl(ShopMapper shopMapper){
    this.shopMapper = shopMapper;
  }


  // 1. 상품 하나를 등록하는 기능을 실행시킬 메서드
  @Override
  public int insertItem(ShopDTO shopDTO) {
    System.out.println("상품의 재고를 확인한다");
    System.out.println("상품에 이상이 없는지 확인한다");
    //상품 등록 -> 쿼리 실행
    System.out.println("잘 등록되었는지 확인한다");
    return shopMapper.insertItem(shopDTO);
  }

  //2. 모든 상품의 상품번호, 상품명, 가격, 상품등록일을 조회하는 기능을 실행시킬 메서드
  @Override
  public List<ShopDTO> selectItemList() {
    System.out.println("모든 상품을 조회한다.");
    return shopMapper.selectItemList();
  }

  //3. 상품번호를 통해 해당 상품의 모든 컬럼을 조회하는 기능을 실행시킬 메서드
  @Override
  public ShopDTO selectItem(int itemCode) {
    System.out.println("해당 상품의 모든 컬럼을 조회한다");
    return shopMapper.selectItem(itemCode);
  }

  //4. 특정 상품 번호에 해당하는 상품을 삭제하는 기능을 실행시킬 메서드
  @Override
  public void deleteItem(int itemCode) {
    System.out.println("해당 상품을 삭제합니다");
    shopMapper.deleteItem(itemCode);
  }

  //5. 특정 상품 번호에 해당하는 상품의 상품명, 가격, 상품설명을 수정하는 기능을 실행시킬 메서드
  @Override
  public void updateItem(ShopDTO shopDTO) {
    System.out.println("해당 상품의 상품명, 가격, 상품설명을 수정합니다");
    shopMapper.updateItem(shopDTO);
  }
}
