import React, { useState } from "react";

const Input_2 = () => {
  //모든 input 태그에 입력한 데이터를 저장할 state 변수
  const [person, setPerson] = useState({
    name : '',
    age : '',
    addr : '',
  });

  //input 태그의 값이 변경되는 실행시킬 함수
  function changeData(e){
    setPerson({
      ...person,
      [e.target.name] : e.target.value
    })
  };
  

  return (
    <>
      이름 : <input name="name" type="text" value={person.name} onChange={(e) => {
        //input 태그에 값이 변경될 때마다(input 태그에 입력할 때마다)
        //input 태그에 작성한 데이터를 name 변수에 저장한다.
        changeData(e);
      }}/> <br />
      나이 : <input name="age" type="text" value={person.age} onChange={(e) => {
        changeData(e);
      }}/> <br />
      주소 : <input name="addr" type="text" value={person.addr} onChange={(e) => {
        changeData(e);
      }}/> <br />

      <div>
        입력받은 이름 : {person.name}
      </div>
      <div>
        입력받은 나이 : {person.age}
      </div>
      <div>
        입력받은 주소 : {person.addr}
      </div>
    </>
  );
};

export default Input_2;
