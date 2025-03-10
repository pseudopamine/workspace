package com.green.member_manager.service;

import com.green.member_manager.dto.MemDTO;

import java.util.List;

public interface MemService {

  //모든 회원 정보 조회 기능
  public List<MemDTO> selectMemberList();

  //특정 회원 상세 정보 조회 기능
  public MemDTO selectMember(String memId);

  //회원 정보 삽입 기능
  public int insertMember(MemDTO memDTO);

  //특정 회원 정보 삭제 기능
  public void deleteMember(String memId);

  //특정 회원 정보 수정 기능
  public void updateMember(MemDTO memDTO);

}
