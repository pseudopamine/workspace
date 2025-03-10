import axios from 'axios'
import './MemberList.css'

import React, { useEffect, useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';

//REACT_MEMBER 테이블에 저장된 모든 회원 정보를 조회하여
//MemberList 컴포넌트에 table 형식으로 표현하세요
// 테이블의 컬럼 : No(행번호), 회원ID, 회원명, 회원연락처, 가입일
const MemberList = () => {
  //회원 목록 데이터를 저장할 state 변수
  const [memberList, setMemberList] = useState([]);
  const nav = useNavigate();

  

  //mount될 때만 실행시키기 위해 useEffect hook
  useEffect(() => {
    //회원 목록 조회
    axios.get('/api/members')
        .then((res) => {
          console.log(res.data)
          setMemberList(res.data)
        })
        .catch((error) => {})
  }, []);



  return (
    <>
      <div>회원 목록 페이지</div>
      <div className='container'>
        <table>
          <colgroup>
            <col width={'5%'}/>
            <col width={'20%'}/>
            <col width={'20%'}/>
            <col width={'20%'}/>
            <col width={'35%'}/>
          </colgroup>
          <thead>
            <tr>
              <td>No</td>
              <td>회원ID</td>
              <td>회원명</td>
              <td>회원연락처</td>
              <td>가입일</td>
            </tr>
          </thead>
          <tbody>
            {
              memberList.map((member, i) => {
                return(
                  <tr key={i}>
                    <td>{memberList.length - i}</td>
                    <td>
                      <Link to={`/detail/${member.memId}`}><span>{member.memId}</span></Link>
                      {/* <Link to={`/detail/${memberList[i].memId}`}><span>{memberList[i].memId}</span></Link> */}
                    </td>
                    <td>{member.memName}</td>
                    <td>{member.memTel}</td>
                    <td>{member.joinDate}</td>
                  </tr>
                )
              })
            }
          </tbody>
        </table>

        <button type='button' onClick={(e) => {nav('/insert');}}>회원등록</button>
      </div>
    </>
  )
}

export default MemberList