package com.example.react_shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class EmpDTO {
  private int empno;
  private String ename;
  private String job;
  private int mgr;
  private LocalDateTime hiredate;
  private int sal;
  private int comm;
  private int deptno;

  private DeptDTO deptDTO;

}
