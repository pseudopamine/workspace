package com.example.react_shop.service;

import com.example.react_shop.dto.BoardDTO;
import com.example.react_shop.dto.JoinDTO;
import com.example.react_shop.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService{
  private final TestMapper testMapper;

  @Override
  public List<JoinDTO> getEmpList() {
    return testMapper.getEmpList();
  }

  @Override
  public List<BoardDTO> joinList() {
    return testMapper.joinList();
  }
}
