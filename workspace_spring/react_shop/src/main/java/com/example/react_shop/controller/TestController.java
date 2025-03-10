package com.example.react_shop.controller;

import com.example.react_shop.dto.BoardDTO;
import com.example.react_shop.dto.JoinDTO;
import com.example.react_shop.service.TestService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
  private final TestService testService;

  @GetMapping("/1")
  public List<JoinDTO> test1(){
    return testService.getEmpList();
  }

  @GetMapping("/2")
  public List<BoardDTO> test2(){
    return testService.joinList();
  }
}
