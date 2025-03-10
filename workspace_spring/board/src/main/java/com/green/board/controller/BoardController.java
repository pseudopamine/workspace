package com.green.board.controller;

import com.green.board.dto.BoardDTO;
import com.green.board.dto.SearchDTO;
import com.green.board.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
public class BoardController {
  private BoardService boardService;

  public BoardController(BoardService boardService){
    this.boardService = boardService;
  }

  //모든 목록 조회 기능 + 검색 버튼 클릭 시 목록 조회 기능 REST API
  @GetMapping("")
  public List<BoardDTO> getBoardList(SearchDTO searchDTO){
    System.out.println(searchDTO);
    return boardService.selectBoardList(searchDTO);
  }

  //새 글 등록 기능 REST API
  @PostMapping("")
  public void joinBoard(@RequestBody BoardDTO boardDTO){
    boardService.insertBoard(boardDTO);
  }

  //특정 목록 상세조회 기능 REST API
  @GetMapping("/{boardNum}")
  public BoardDTO getBoard(@PathVariable("boardNum") int boardNum){
    return boardService.selectBoard(boardNum);
  }

  //특정 목록 삭제 기능 REST API
  @DeleteMapping("/{boardNum}")
  public void deleteBoard(@PathVariable("boardNum") int boardNum){
    boardService.deleteBoard(boardNum);
  }

  //특정 목록 수정 기능 REST API
  @PutMapping("/{boardNum}")
  public void updateBoard(@PathVariable("boardNum") int boardNum, @RequestBody BoardDTO boardDTO){
    boardDTO.setBoardNum(boardNum);
    boardService.updateBoard(boardDTO);
  }

  //조회수 증가 기능 REST API
//  @PutMapping("/{boardNum}")
//  public void updateReadCnt(@PathVariable("boardNum") int boardNum){
//    boardService.updateReadCnt(boardNum);
//  }
}
