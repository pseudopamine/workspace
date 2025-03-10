import React, { useState } from "react";

const Test5 = () => {
  const [person, setPerson] = useState({
    name : '김자바',
    age : 20,
    address : '울산시'
  })
  const copyPerson ={
    ...person
  }

  return (
    <>
      <div>Test5</div>
      이름 : {person.name} <br />
      나이 : {person.age} <br />
      주소 : {person.address} <br />

      <button id='aaa' className="bbb" type="button" onClick={(e) => {
        //이벤트 발생시 실행 코드로 작성하는 함수의 매개변수로는
        //이벤트 객체를 전달받을 수 있다.
        //이벤트 객체는 이벤트와 관련된 모든 정보를 객체 형태로 갖고있다. 
       console.log(e);
        //e.target -> 이벤트가 발생한 태그
       console.log(e.target);
       //이벤트가 발생한 태그의 type 속성값
       console.log(e.target.type);
       console.log(e.target.id);
       console.log(e.target.className);
      }}>이름을 홍길동으로 변경</button>

      <button type="button" onClick={() => {
        setPerson({
          ...person,
          age : 30
        });
      }}>나이를 30으로 변경</button>

      <button type="button" onClick={() => {
        setPerson({
          ...person,
          address : '서울시'
        })
      }}>주소를 서울시로 변경</button>

    </>
  );
};

export default Test5;
