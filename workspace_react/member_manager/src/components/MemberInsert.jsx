import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const MemberInsert = () => {
  //입력한 모든 정보를 저장할 state 변수
  const [regData, setregData] = useState({
    //input tag에서 받은 정보를 입력하기 위한 변수 
    memId : "",
    memName : "",
    memPw : "",
    memTel : "",
    memIntro : "",
    pwConfirm : ""
  });
  const nav = useNavigate();

  //input 태그에 값 입력시 실행 할 함수
  const changeRegData = (e) => {
    setregData({
      ...regData,
      [e.target.name] : e.target.value
    })
  }

  const validateRegData = () => {
    let isValid = true;
    //데이터 유효성 검사 (validation 처리)
    if(regData.memId === ''){
      alert('아이디는 필수 입력입니다.');
      isValid = false;
    }
    if(regData.memName === ''){
      alert('이름은 필수 입력입니다.');
      isValid = false;
    }
    if(regData.memPw !== regData.pwConfirm){
      alert('비밀번호가 일치하지 않습니다.')
      isValid = false;
    }
    return isValid;
  }

  //가입버튼 클릭시 실행될 함수
  const regMember = () => {
    //데이터 유효성 검사 (validation 처리)
    if(!validateRegData()){
      return;
    }
    axios.post('/api/members', regData)
        .then((res) => {
          if(res.data !== 1){
            alert('알 수 없는 이유로 오류가 발생했습니다.');
            return;
          }
          
            alert(`${regData.memId}님 가입을 축하합니다`)
            nav('/');
          
        })
        .catch((error) => {console.log(error)})
  }
  
  console.log(regData)

  return (
    <>
      <div>회원 등록 페이지</div>
      <div>
        <table>
          <thead>
            <tr>
              <td>회원</td>
              <td>입력창</td>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>회원 아이디</td>
              <td><input type="text" name="memId" value={regData.memId} onChange={(e) => {changeRegData(e)}}/></td>
            </tr>
            <tr>
              <td>회원 이름</td>
              <td><input type="text" name="memName" value={regData.memName} onChange={(e) => {changeRegData(e)}}/></td>
            </tr>
            <tr>
              <td>비밀번호</td>
              <td><input type="password" name="memPw" value={regData.memPw} onChange={(e) => {changeRegData(e)}}/></td>
            </tr>
            <tr>
              <td>비밀번호 확인</td>
              <td><input type="password" name="pwConfirm" value={regData.pwConfirm} onChange={(e) => {changeRegData(e)}}/></td>
            </tr>
            <tr>
              <td>회원 연락처</td>
              <td><input type="text" name="memTel" value={regData.memTel} onChange={(e) => {changeRegData(e)}}/></td>
            </tr>
            <tr>
              <td>회원 소개</td>
              <td><input type="text" name="memIntro" value={regData.memIntro} onChange={(e) => {changeRegData(e)}}/></td>
            </tr>
          </tbody>
        </table>
        <button type="button" onClick={(e) => {
            regMember();
        }}>등록</button>
        
        <button type="button" onClick={(e) => {nav('/');}}>목록 페이지로 돌아가기</button>
      </div>
    </>
  );
};

export default MemberInsert;
