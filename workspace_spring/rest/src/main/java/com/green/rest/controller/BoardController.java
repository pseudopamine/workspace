package com.green.rest.controller;

import com.green.rest.dto.BoardDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/boards")
public class BoardController {
  //1. 모든 게시글을 조회하는 기능을 제공하는 REST API
  @GetMapping("")
  public void getBoardList(){
    log.info("게시글 목록 조회");
  }

  //2. 하나의 게시글 정보를 조회하는 기능을 제공하는 REST API
  //(GET) localhost:8080/boards/3
  @GetMapping("/{boardNum}")
  public void getBoard(@PathVariable("boardNum") int boardNum){
    log.info("게시글 정보 조회");
    log.info("boardNum = " + boardNum);
  }


  //3. 하나의 게시글을 등록하는 기능을 제공하는 REST API
  //(POST) localhost:8080/boards
  @PostMapping("")
  public void insertBoard(@RequestBody BoardDTO boardDTO){
    log.info("게시글 등록");
    log.info(boardDTO.toString());
    log.info("boardDTO = " + boardDTO);
  }

}
