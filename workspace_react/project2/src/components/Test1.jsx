import './Test1.css';
import React, { useState } from "react";

const Test1 = () => {
  //카운터 숫자 데이터를 저장할 state 변수 생성
  const [cnt, setCnt] = useState(0);
  
  //매개변수로 들어온 값으로 cnt 값을 변경

  return(
    <>
      <h2>Test1</h2>
      <div className='count'>{cnt}</div>
      <button type="button" onClick={() => {
        setCnt(cnt + 1);
      }}>클릭</button>
    </>
  );
};

export default Test1;
