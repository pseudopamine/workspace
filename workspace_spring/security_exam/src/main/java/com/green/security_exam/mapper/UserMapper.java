package com.green.security_exam.mapper;

import com.green.security_exam.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
 public void join(MemberDTO memberDTO);

 public MemberDTO getMemberForLogin(String memEmail);
}
