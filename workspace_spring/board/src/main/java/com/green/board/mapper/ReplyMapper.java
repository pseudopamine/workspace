package com.green.board.mapper;

import com.green.board.dto.ReplyDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper //객체생성 + 쿼리 실행 메서드 존재 인식
public interface ReplyMapper {

  //댓글 목록 조회 쿼리
  public List<ReplyDTO> selectReplyList(int boardNum);

  //댓글 입력 쿼리
  public void insertReply(ReplyDTO replyDTO);

  //댓글 삭제 쿼리
  public void deleteReply(int replyNum);
}
