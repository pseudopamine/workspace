package com.green.board.service;

import com.green.board.dto.BoardDTO;
import com.green.board.dto.SearchDTO;

import java.util.List;

public interface BoardService {

  //모든 목록 조회 기능
  public List<BoardDTO> selectBoardList(SearchDTO searchDTO);

  //새 글 등록 기능
  public void insertBoard(BoardDTO boardDTO);

  //특정 목록 상세조회 기능
  public BoardDTO selectBoard(int boardNum);

  //특정 목록 삭제 기능
  public void deleteBoard(int boardNum);

  //특정 목록 수정 기능
  public void updateBoard(BoardDTO boardDTO);

}
