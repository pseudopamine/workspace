package com.green.board.mapper;

import com.green.board.dto.BoardDTO;
import com.green.board.dto.SearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

  //모든 목록 조회 쿼리
  public List<BoardDTO> selectBoardList(SearchDTO searchDTO);

  //새 글 등록 쿼리
  public void insertBoard(BoardDTO boardDTO);

  //특정 목록 상세조회 쿼리
  public BoardDTO selectBoard(int boardNum);

  //특정 목록 삭제 쿼리
  public void deleteBoard(int boardNum);

  //특정 목록 수정 쿼리
  public void updateBoard(BoardDTO boardDTO);

  //조회수 증가 수정 쿼리
  public void updateReadCnt(int boardNum);
}
