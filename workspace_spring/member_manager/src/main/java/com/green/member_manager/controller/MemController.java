package com.green.member_manager.controller;

import com.green.member_manager.dto.MemDTO;
import com.green.member_manager.service.MemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/members")
public class MemController {
  private MemService memService;

  public MemController(MemService memService){
    this.memService = memService;
  }

  //회원 목록 조회 REST API
  @GetMapping("")
  public List<MemDTO> selectMemberList(){
    log.info("모든 회원 목록 정보 조회 시작");
    return memService.selectMemberList();
  }

  //특정 회원 상세 정보 조회 REST API
  @GetMapping("/{memId}")
  public MemDTO selectMember(@PathVariable("memId") String memId){
    System.out.println("특정 회원 정보 조회");
    return memService.selectMember(memId);
  }

  //회원 정보 등록 REST API
  @PostMapping("")
  public int insertMember(@RequestBody MemDTO memDTO){
    System.out.println("회원 정보 삽입");
    return memService.insertMember(memDTO);
  }

  //특정 회원 삭제 REST API
  @DeleteMapping("/{memId}")
  public void deleteMember(@PathVariable("memId") String memId){
    System.out.println("특정 회원 삭제");
    memService.deleteMember(memId);
  }

  //특정 회원 정보 수정 REST API
  @PutMapping("/{memId}")
  public void updateMember(@PathVariable("memId") String memId, @RequestBody MemDTO memDTO){
    memDTO.setMemId(memId); //memDTO의 memId에 받아오는 memId로 채우겠습니다. (순서 중요)
    memService.updateMember(memDTO); //memId 제외한 쿼리의 빈 값을 채우는 용도
  }

}
