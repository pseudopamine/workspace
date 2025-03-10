package com.green.board.service;

import com.green.board.dto.ReplyDTO;
import com.green.board.mapper.ReplyMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReplyServiceImpl implements ReplyService{
  private ReplyMapper replyMapper;

  public ReplyServiceImpl(ReplyMapper replyMapper){
    this.replyMapper = replyMapper;
  }

  @Override
  public List<ReplyDTO> selectReplyList(int boardNum) {
    return replyMapper.selectReplyList(boardNum);
  }

  @Override
  public void insertReply(ReplyDTO replyDTO) {
    replyMapper.insertReply(replyDTO);
  }

  @Override
  public void deleteReply(int replyNum) {
    replyMapper.deleteReply(replyNum);
  }

}
