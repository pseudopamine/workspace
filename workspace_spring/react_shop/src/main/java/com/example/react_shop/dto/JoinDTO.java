package com.example.react_shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JoinDTO {
  private int empno;
  private String ename;
  private int sal;
  private int deptno;
  private String dname;
  private String loc;
}
