package com.green.member_manager.service;


import com.green.member_manager.dto.MemDTO;
import com.green.member_manager.mapper.MemMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//MemServiceImpl aa = new MemServiceImpl();
@Service
public class MemServiceImpl implements MemService{
  private MemMapper memMapper;

  public MemServiceImpl(MemMapper memMapper){
    this.memMapper = memMapper;
  }

  //회원 목록 조회 기능
  @Override
  public List<MemDTO> selectMemberList() {
    //회원 목록 조회 쿼리 실행
    return memMapper.selectMemberList();
  }

  //특정 회원 상세 정보 조회 기능
  @Override
  public MemDTO selectMember(String memId) {
    return memMapper.selectMember(memId);
  }

  //회원 정보 삽입 기능
  @Override
  public int insertMember(MemDTO memDTO) {
    return memMapper.insertMember(memDTO);
  }

  //특정 회원 삭제 기능
  @Override
  public void deleteMember(String memId) {
    memMapper.deleteMember(memId);
  }

  //특정 회원 정보 수정 기능
  @Override
  public void updateMember(MemDTO memDTO) {
    memMapper.updateMember(memDTO);

  }
}
