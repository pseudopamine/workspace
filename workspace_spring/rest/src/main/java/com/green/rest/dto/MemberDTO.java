package com.green.rest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//DTO(Data Transfer Object : 데이터 전송 객체)
@Getter
@Setter
@ToString
public class MemberDTO {
  private String memId;
  private String memName;
  private int memAge;
  private String memTel;
}
