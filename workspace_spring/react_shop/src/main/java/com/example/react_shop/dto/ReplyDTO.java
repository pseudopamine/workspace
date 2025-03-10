package com.example.react_shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReplyDTO {
  private int replyNum;
  private String replyContent;
  private String replyWriter;
  private LocalDateTime replyRegDate;
  private int boardNum;
}
