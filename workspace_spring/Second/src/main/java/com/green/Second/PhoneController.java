package com.green.Second;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhoneController {
  private Phone phone;
  private BoardService boardService;

  @Autowired
  public PhoneController(Phone phone, BoardService boardService){
    this.phone = phone;
    this.boardService = boardService;
  }
}
