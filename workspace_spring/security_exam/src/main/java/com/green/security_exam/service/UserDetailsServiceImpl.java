package com.green.security_exam.service;

import com.green.security_exam.dto.CustomUserDetails;
import com.green.security_exam.dto.MemberDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserService userService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info("UserDetailsServiceImpl 클래스의 loadUserByUsername() 메서드 실행");

    //DB에서 로그인 검증을 시도하는 유저의 정보를 조회
    MemberDTO dto = userService.getMemberForLogin(username);

    if(dto == null){
      log.info("=======일치하는 아이디 없음==========");
      //401 상태코드 반환
      //로그인 시도 하려는 회원의 아이디가 없으면 더이상 로그인 검증을 진행하지 않고 멈춤
      throw new UsernameNotFoundException("없는 아이디 : " + username);
    }

    //조회한 로그인 정보를 UserDetails 인터페이스를 상속한 CustomUserDetails 클래스에 저장하여 리턴.
    //리턴된 UserDetails 객체를 AuthenticationManager가 전달받아 로그인 검증을 실행.
    return new CustomUserDetails(dto);
  }
}
