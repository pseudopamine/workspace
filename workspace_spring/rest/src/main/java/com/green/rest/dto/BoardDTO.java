package com.green.rest.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {
  private int boardNum;
  private String title;
  private String content;
  private String writer;
}
