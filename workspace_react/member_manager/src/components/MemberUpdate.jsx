import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

const MemberUpdate = () => {
  const nav = useNavigate();

  //구조분해할당으로 수정해야 할 회원의 ID를 받아옴
  const {memId} = useParams();


  //수정할 회원의 모든 정보를 저장할 state 변수 + 입력한 데이터도 저장
  const [memberInfo, setMemberInfo] = useState({});


  //input태그의 값이 변경될 때마다 실행하는 함수
  const changeMemberInfo = (e) => {
    setMemberInfo({
      ...memberInfo,
      [e.target.name] : e.target.value
    })
  }

  //마운트 시 spring에서 회원정보를 조회하여, 조회한 데이터를 memberInfo 변수에 저장
  useEffect(() => {
    axios.get(`/api/members/${memId}`)
        .then((res) => {
          setMemberInfo(res.data);
        })
        .catch((error)=> {console.log(error);})
  }, []);

  console.log(memberInfo)


  //회원 수정 기능 실행 함수
  const updateMember = () => {
    if(memberInfo.memPw !== memberInfo.pwConfirm){
      alert('비밀번호가 일치하지 않습니다.')
      return;
    }
    if(!confirm('수정하시겠습니까?')){
      return;
    }
    axios.put(`/api/members/${memberInfo.memId}`, memberInfo)
        .then((res) => {
          alert('수정되었습니다.');
          nav(`/detail/${memberInfo.memId}`);
        })
        .catch((error) => {console.log(error)})
  }

  return (
    <>
      <div>회원 정보 수정</div>
        <table>
          <tbody>
            <tr>
              <td>회원 이름</td>
              <td><input type="text" name="memName" value={memberInfo.memName} onChange={(e) => {changeMemberInfo(e);}}/></td>
            </tr>
            <tr>
              <td>바꿀 비밀번호</td>
              <td><input type="password" name="memPw" value={memberInfo.memPw} onChange={(e) => {changeMemberInfo(e);}}/></td>
            </tr>
            <tr>
              <td>비밀번호 확인</td>
              <td><input type="password" name="pwConfirm" value={memberInfo.pwConfirm} onChange={(e) => {changeMemberInfo(e);}}/></td>
            </tr>
            <tr>
              <td>회원 연락처</td>
              <td><input type="text" name="memTel" value={memberInfo.memTel} onChange={(e) => {changeMemberInfo(e);}}/></td>
            </tr>
            <tr>
              <td>회원 소개</td>
              <td><input type="text" name="memIntro" value={memberInfo.memIntro} onChange={(e) => {changeMemberInfo(e);}}/></td>
            </tr>
          </tbody>
        </table>
      <button type="button" onClick={(e) => {updateMember();}}>수정완료</button>
    </>
  );
};

export default MemberUpdate;
