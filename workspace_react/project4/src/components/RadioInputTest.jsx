import React, { useState } from "react";

const RadioInputTest = () => {
  const [student, setStudent] = useState({
    name : '',
    mail : '',
    password : '',
    major : '',
    gender : 'female',
    blood : 'A',
    self : ''
  });

  const setInfo = (e) => {
    setStudent({
      ...student,
      [e.target.name] : e.target.value
    })
  }

  return (
    <>
      <h2>신체포기각서</h2>
      <table>
        <thead>
          <tr>
            <td>정보</td>
            <td>기입란</td>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>이름</td>
            <td>
              <input name="name" type="text" value={student.name} onChange={(e) => {setInfo(e);}}/>
            </td>
          </tr>
          <tr>
            <td>이메일</td>
            <td>
              <input name="mail" type="text" value={student.mail} onChange={(e) => {setInfo(e);}}/>
            </td>
          </tr>
          <tr>
            <td>비밀번호</td>
            <td>
              <input name="password" type="password" value={student.password} onChange={(e) => {setInfo(e);}}/>
            </td>
          </tr>
          <tr>
            <td>학과</td>
            <td>
              <select name="major" value={student.major} onChange={(e) => {setInfo(e);}}>
                <option value=""> - 학과를 선택하세요 - </option>
                <option value="electronic">전기공학과</option>
                <option value="computer">컴퓨터공학과</option>
                <option value="chemistry">화학공학과</option>
                <option value="biology">생명공학과</option>
              </select>
            </td>
          </tr> 
          <tr>
            <td>성별</td>
            <td>
            <input name="gender" type="radio" value="male" checked={student.gender === "male"} onChange={(e) => {setInfo(e);}}/> 남자
            <input name="gender" type="radio" value="female" checked={student.gender === "female"} onChange={(e) => {setInfo(e);}}/> 여자
            </td>
          </tr>
          <tr>
            <td>혈액형</td>
            <td>
            <input name="blood" type="radio" value="A" checked={student.blood === "A"} onChange={(e) => {setInfo(e);}}/> A형
            <input name="blood" type="radio" value="B" checked={student.blood === "B"} onChange={(e) => {setInfo(e);}}/> B형
            <input name="blood" type="radio" value="AB" checked={student.blood === "AB"} onChange={(e) => {setInfo(e);}}/> AB형
            <input name="blood" type="radio" value="O" checked={student.blood === "O"} onChange={(e) => {setInfo(e);}}/> O형
            </td>
          </tr>
          <tr>
            <td>자기소개</td>
            <td>
            <textarea cols="50" rows="5" name="self" value={student.self} onChange={(e) => {setInfo(e);}}/>
            </td>
          </tr>
        </tbody>
      </table>
      {/* 이름 : <input name="name" type="text" value={student.name} onChange={(e) => {
        setInfo(e);
      }}/> <br />
      이메일 : <input name="mail" type="text" value={student.mail} onChange={(e) => {
        setInfo(e);
      }}/> <br />
      비밀번호 : <input name="password" type="password" value={student.password} onChange={(e) => {
        setInfo(e);
      }}/> <br />
      학과
      <select name="major" value={student.major} onChange={(e) => {
        setInfo(e);
      }}>
        <option value=""> - 학과를 선택하세요 - </option>
        <option value="electronic">전기공학과</option>
        <option value="computer">컴퓨터공학과</option>
        <option value="chemistry">화학공학과</option>
        <option value="biology">생명공학과</option>
      </select> <br />
      성별
      <input name="gender" type="radio" value="male" checked={student.gender === "male"} onChange={(e) => {setInfo(e);}}/> 남자
      <input name="gender" type="radio" value="female" checked={student.gender === "female"} onChange={(e) => {setInfo(e);}}/> 여자 <br />
      자기소개
      <input name="self" type="textarea" value={student.self} onChange={(e) => {setInfo(e);}}/> <br /> */}

        <h3>이름 : {student.name}</h3>
        <h3>이메일 : {student.mail}</h3>
        <h3>비밀번호 : {student.password}</h3>
        <h3>학과 : {student.major}</h3>
        <h3>성별 : {student.gender}</h3>
        <h3>혈액형 : {student.blood}</h3>
        <h3>자기소개 : {student.self}</h3>
    </>
  );
};

export default RadioInputTest;
