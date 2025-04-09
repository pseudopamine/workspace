package com.green.security_exam.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MainController {

  //아무나 접근 가능
  @GetMapping("/test1")
  public String test1(){
    log.info("test1() 메서드 실행!");
    return "test1";
  }

  //인증된 사람만 접근 가능
  @GetMapping("/test2")
  public String test2(){
    log.info("test2() 메서드 실행!!");
    return "test2";
  }

  //관리자만 접근 가능
  @GetMapping("/test3")
  public String test3(){
    log.info("test3() 메서드 실행!!!");
    return "test3";
  }

  @PreAuthorize("isAuthenticated()")
  @GetMapping("/test4")
  public String test4(){
    log.info("test4() 메서드 실행!!!");
    return "test4";
  }

  @PreAuthorize("hasRole('ADMIN')")
  @GetMapping("/test5")
  public String test5(){
    log.info("test5() 메서드 실행!!!");
    return "test5";
  }
}
