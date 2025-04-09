package com.green.security_exam.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@RequiredArgsConstructor
public class CustomUserDetails implements UserDetails {
  private final MemberDTO memberDTO;

  //계정의 권한정보를 리턴하는 메서드
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<GrantedAuthority> collection = new ArrayList<>();

    collection.add(new GrantedAuthority() {
      @Override
      public String getAuthority() {
        return memberDTO.getMemRole();
      }
    });

    return collection;
  }

  @Override
  public String getPassword() {
    return memberDTO.getMemPw();
  }

  @Override
  public String getUsername() {
    return memberDTO.getMemEmail();
  }

  //현재 로그인 계정이 만료되지 않았나?
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  //계정이 잠겼는가?
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  //자격증명 만료여부
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  //사용 가능 상태?
  @Override
  public boolean isEnabled() {
    return true;
  }
}
