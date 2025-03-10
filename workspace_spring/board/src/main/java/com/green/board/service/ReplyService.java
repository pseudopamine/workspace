package com.green.board.service;

import com.green.board.dto.ReplyDTO;

import java.util.List;

public interface ReplyService {

  //댓글 목록 조회 기능
  public List<ReplyDTO> selectReplyList(int boardNum);

  //댓글 입력 기능
  public void insertReply(ReplyDTO replyDTO);

  //댓글 삭제 기능
  public void deleteReply(int replyNum);
}
