import React, { useState } from "react";

const Input_Test1 = () => {
  const [person, setPerson] = useState({
    name : '',
    tel : '',
    mail : '',
    major : '국어'
  });

  //모든 input 태그의 값이 변경될 때 실행할 함수
  //input 태그에 입력한 정보를 person에 저장(변경)하는 기능
  const setData = (e) => {
    setPerson({
      ...person,
      [e.target.name] : e.target.value
    })
  }
  
  return (
    <>
      <h1>회원 가입서</h1>
      이름 : <input name="name" type="text" value={person.name} onChange={(e) => {
        setData(e);
      }}/> <br />
      연락처 : <input name="tel" type="text" value={person.tel} onChange={(e) => {
        setData(e);
      }}/> <br />
      이메일 : <input name="mail" type="text" value={person.mail} onChange={(e) => {
        setData(e);
      }}/> <br />
      희망과목
      <select name="major" value={person.major} onChange={(e) => {
        setData(e);
      }}>
        <option value="국어">국어</option>
        <option value="영어">영어</option>
        <option value="수학">수학</option>
      </select>
      <div>
        <h2>작성내용</h2>
        <h3>이름 : {person.name}</h3>
        <h3>연락처 : {person.tel}</h3>
        <h3>이메일 : {person.mail}</h3>
        <h3>희망과목 : {person.major}</h3>
      </div>
      <div>

      </div>
    </>
  );
};

export default Input_Test1;
