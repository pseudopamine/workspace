package com.green.security_exam.service;

import com.green.security_exam.dto.MemberDTO;
import com.green.security_exam.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
  private final UserMapper userMapper;

  @Override
  public void join(MemberDTO memberDTO) {
    userMapper.join(memberDTO);
  }

  @Override
  public MemberDTO getMemberForLogin(String memEmail) {
    return userMapper.getMemberForLogin(memEmail);
  }
}
