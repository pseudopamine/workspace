package com.green.basic_board.mapper;


//해당 인터페이스가 board-mapper.xml에 작성한 쿼리를 실행하는 인터페이스
//쿼리를 실행하는 인터페이스는 @Mapper 어노테이션을 사용해야한다.
//@Mapper 어노테이션이 붙은 인터페이스를 쿼리 실행 메서드가 존재하는 파일임을 인지 + 객체도 자동 생성
//해당 mapper 인터페이스와 연결될 xml 파일에 namespace 부분에 연결할 인터페이스명을 패키지까지 포함해서 작성

import com.green.basic_board.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
  /*
  * 1. 메서드명은 xml 파일에 작성함 쿼리 id와 일치!
  * 2. 메서드의 매개변수는 실행할 쿼리의 빈 값을 채울 용도로 사용.
  * 3. 메서드의 리턴타입은 쿼리 실행 결과 전체를 자바로 가져올 수 있는 자료형으로 선언
  *
  *
  * */

  //게시글 목록 조회 쿼리를 실행시킬 메서드
  public List<BoardDTO> selectBoardList();

  //하나의 게시글 정보를 조회하는 쿼리를 실행시킬 메서드
  public BoardDTO getBoard(int boardNum);

  //게시글 등록 쿼리 실행 메서드
  public int insertBoard(BoardDTO boardDTO);



}
