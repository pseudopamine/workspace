package com.example.react_shop.mapper;

import com.example.react_shop.dto.BoardDTO;
import com.example.react_shop.dto.JoinDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {

  public List<JoinDTO> getEmpList();

  public List<BoardDTO> getBoardList();

  public BoardDTO getBoard(int boardNum);

  public List<BoardDTO> joinList();
}
