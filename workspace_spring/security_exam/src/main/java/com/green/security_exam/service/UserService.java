package com.green.security_exam.service;


import com.green.security_exam.dto.MemberDTO;

public interface UserService {

  public void join(MemberDTO memberDTO);

  public MemberDTO getMemberForLogin(String memEmail);
}
