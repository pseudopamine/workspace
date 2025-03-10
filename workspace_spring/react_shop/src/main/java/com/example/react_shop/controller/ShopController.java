package com.example.react_shop.controller;

import com.example.react_shop.dto.ShopDTO;
import com.example.react_shop.service.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/items")
@RequiredArgsConstructor
public class ShopController {
  private final ShopService shopService;

  //상품 등록 기능 REST API
  @PostMapping("")
  public void setItem(@RequestBody ShopDTO shopDTO){
    shopService.insertItem(shopDTO);
  }

  //모든 상품 조회 기능 REST API
  @GetMapping("")
  public List<ShopDTO> getItemList(ShopDTO shopDTO){
    return shopService.selectItemList(shopDTO);
  }

  //특정 상품 상세 조회 기능 REST API
  @GetMapping("/{itemNum}")
  public ShopDTO getItem(@PathVariable("itemNum") int itemNum){
    return shopService.selectItem(itemNum);
  }

  //특정 상품 수정 기능 REST API
  @PutMapping("/{itemNum}")
  public void updateItem(@PathVariable("itemNum") int itemNum, @RequestBody ShopDTO shopDTO){
    shopDTO.setItemNum(itemNum);
    shopService.updateItem(shopDTO);
  }
}
