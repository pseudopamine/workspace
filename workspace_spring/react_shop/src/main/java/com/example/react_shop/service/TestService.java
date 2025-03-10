package com.example.react_shop.service;

import com.example.react_shop.dto.BoardDTO;
import com.example.react_shop.dto.JoinDTO;

import java.util.List;

public interface TestService {

  public List<JoinDTO> getEmpList();

  public List<BoardDTO> joinList();
}
