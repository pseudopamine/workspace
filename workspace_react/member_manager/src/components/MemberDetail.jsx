import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

const MemberDetail = () => {
  const [memberDetail, setMemberDetail] = useState({});
  const {memId} = useParams();
  const nav = useNavigate();

  useEffect(() => {
    axios.get(`/api/members/${memId}`)
      .then((res) => {
        console.log(res.data);
        setMemberDetail(res.data);
      })
      .catch((error) => {})
  }, []);


  
  //회원 삭제 함수
  const deleteMember = () => {
    //confirm : 확인 -> true
    //confirm : 취소 -> false
    // const result = confirm('정말 삭제하시겠습니까?');
    //취소 누르면 실행하지 마시오
      if(!confirm('정말 삭제하시겠습니까?')){
        return;
      }
    axios.delete(`/api/members/${memberDetail.memId}`)
        .then((res) => {
          alert('회원이 삭제되었습니다.');
          nav('/');
        })
        .catch((error) => {console.log(error)})
  }

  return (
    <>
      <div>회원 상세 정보 페이지</div>
      <table>
      <colgroup>
            <col width={'5%'}/>
            <col width={'10%'}/>
            <col width={'15%'}/>
            <col width={'15%'}/>
            <col width={'15%'}/>
            <col width={'15%'}/>
            <col width={'25%'}/>
          </colgroup>
          <thead>
            <tr>
              <td>No</td>
              <td>회원ID</td>
              <td>회원명</td>
              <td>회원비번</td>
              <td>회원연락처</td>
              <td>회원정보</td>
              <td>가입일</td>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>1</td>
              <td>{memberDetail.memId}</td>
              <td>{memberDetail.memName}</td>
              <td>{memberDetail.memPw}</td>
              <td>{memberDetail.memTel}</td>
              <td>{memberDetail.memIntro}</td>
              <td>{memberDetail.joinDate}</td>
            </tr>
          </tbody>
      </table>
      <button type="button" onClick={(e) => {nav('/');}}>목록으로 가기</button>
      <button type="button" onClick={(e) => {nav(`/update/${memberDetail.memId}`);}}>회원 수정</button>
      <button type="button" onClick={(e) => {deleteMember();}}>회원 삭제</button>
    </>
  );
};

export default MemberDetail;
