package com.green.shop.controller;

import com.green.shop.dto.ShopDTO;
import com.green.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {
  private ShopService shopService;

  @Autowired  //생략가능
  public ItemController(ShopService shopService){
    this.shopService = shopService;
  }

  //1. 상품 하나를 등록하는 기능 API
  @PostMapping("")
  public int regItem(@RequestBody ShopDTO shopDTO){
    System.out.println(shopDTO);
    //매개변수에 ShopDTO 자료형이 들어와야함
    //매개변수로 쿼리의 빈 값을 채워주는 것이 목적
    //-> 상품명, 상품가격, 상품설명 3개의 데이터가 들어있는 ShopDTO 객체를 매개변수로 전달해야함
    return shopService.insertItem(shopDTO);
  }

  //2. 모든 상품의 상품번호, 상품명, 가격, 상품등록일을 조회하는 기능 RESTful API
  @GetMapping("")
  public List<ShopDTO> getItemList(){
    return shopService.selectItemList();
  }

  //3. 상품번호를 통해 해당 상품의 모든 컬럼을 조회하는 기능 RESTful API
  @GetMapping("/{itemCode}")
  public ShopDTO getItem(@PathVariable("itemCode") int itemCode){
    return shopService.selectItem(itemCode);
  }

  //4. 특정 상품 번호에 해당하는 상품을 삭제하는 기능 RESTful API
  @DeleteMapping("/{itemCode}")
  public void deleteItem(@PathVariable("itemCode") int itemCode){
    shopService.deleteItem(itemCode);
  }

  //5. 특정 상품 번호에 해당하는 상품의 상품명, 가격, 상품설명을 수정하는 기능 RESTful API
  // (PUT) localhost:8080/items/3
  @PutMapping("/{itemCode}")
  public void updateItem(@PathVariable("itemCode") int itemCode, @RequestBody ShopDTO shopDTO){
    shopDTO.setItemCode(itemCode);
    shopService.updateItem(shopDTO);
  }

  //5 - 1. 특정 상품 번호에 해당하는 상품의 상품명, 가격, 상품설명을 수정하는 기능 API
  //REST 정형화 되기 이전 코드
  //localhost:8080/items/update
  @PostMapping("/update")
  public void updateItem(@RequestBody ShopDTO shopDTO){
    shopService.updateItem(shopDTO);
  }

}
