import React, { useState } from "react";
import './Axios_5.css'
import axios from "axios";

const Axios_5 = () => {
  const [personList, setPersonList] = useState([]);
  const [studentList, setStudentList] = useState([]);

  //서버에서 사람 목록 데이터 가져오는 함수
  const getPersonList = () => {
    axios.get('/api/t4')
        .then((res) => {
          console.log(res.data)
          setPersonList(res.data)
        })
        .catch((error) => {console.log('오류 발생')})
  }

  const getStudentList =() => {
    axios.get('/api/t5')
        .then((res) => {
          console.log(res.data)
          setStudentList(res.data)
        })
        .catch((error) => {console.log('오류 발생')});
  }

  return (
    <>
      <div>Axios_5</div>
      <table className="t1">
        <colgroup>
          <col width={'50%'}/>
          <col width={'50%'}/>
        </colgroup>
        <tbody>
          <tr className="t1-tr">
            <td>
              <button type="button" onClick={(e) => {
                getPersonList();
              }}>버튼1</button>
              <table className="infoTable">
                <thead>
                  <tr>
                    <td>이름</td>
                    <td>나이</td>
                    <td>주소</td>
                  </tr>
                </thead>
                <tbody>
                  {
                    personList.length === 0 
                    ?  
                    <tr>
                      <td colSpan={3}>버튼1을 클릭하면 데이터가 조회됩니다.</td>
                    </tr>
                    :
                    personList.map((personInfo, i) => {
                      return (
                        <tr key={i}>
                          <td>{personInfo.name}님</td>
                          <td>{personInfo.age}세</td>
                          <td>{personInfo.addr}</td>
                        </tr>
                      );
                    })
                  }
                </tbody>
              </table>
            </td>
            <td>
              <button type="button" onClick={(e) => {
                getStudentList();
              }}>버튼2</button>
              <table className="infoTable">
                <thead>
                  <tr>
                    <td>이름</td>
                    <td>국어점수</td>
                    <td>영어점수</td>
                    <td>수학점수</td>
                    <td>총 점</td>
                  </tr>
                </thead>
                <tbody>
                  {
                    studentList.length === 0 
                    ?
                    <tr>
                      <td colSpan={5}>버튼2를 클릭하면 데이터가 조회됩니다.</td>
                    </tr>
                    :
                    studentList.map((studentInfo, i) => {
                      return (
                        <tr key={i}>
                          <td>{studentInfo.name}</td>
                          <td>{studentInfo.korScore}점</td>
                          <td>{studentInfo.engScore}점</td>
                          <td>{studentInfo.mathScore}점</td>
                          <td>{studentInfo.korScore + studentInfo.engScore + studentInfo.mathScore}점</td>
                        </tr>
                      )
                    })
                  }
                </tbody>
              </table>
            </td>
          </tr>
        </tbody>
      </table>
    </>
  );
};

export default Axios_5;
