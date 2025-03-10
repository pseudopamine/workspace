package com.green.member_manager.mapper;

import com.green.member_manager.dto.MemDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//xml 파일에 작성한 쿼리문을 실행시키는 메서드를 정의
@Mapper
public interface MemMapper {

  //모든 회원 정보 조회 쿼리
  //쿼리의 실행결과를 전부 담을 자료형
  //매개변수 -> 쿼리의 빈값을 채울 데이터
  public List<MemDTO> selectMemberList();

  //특정 회원 상세 정보 조회 쿼리
  public MemDTO selectMember(String memId);

  //회원 정보 삽입 쿼리
  public int insertMember(MemDTO memDTO);

  //특정 회원 정보 삭제 쿼리
  public void deleteMember(String memId);

  //특정 회원 정보 수정 쿼리
  public void updateMember(MemDTO memDTO);
}
