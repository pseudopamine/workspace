import React, { useState } from "react";

const Input_3 = () => {
  //셀렉트박스에서 선택한 데이터를 저장하기 위한 state 변수
  //초기값은 여러 option들 중에 최초로 화면에 표시하고 싶은 값으로 설정
  const [fruit, setFruit] = useState('banana');
  return (
    <>
      <select value={fruit} onChange={(e) => {
        setFruit(e.target.value);
      }}>
        {/* option value값이 실제 선택되는 진짜 데이터 (고유데이터로 설정) */}
        {/* value 설정을 안하면 기본값이 사과, 바나나처럼 적혀있는 값 */}
        <option value="apple">사과</option>
        <option value="banana">바나나</option>
        <option value="orange">오렌지</option>
      </select>
    </>
  );
};

export default Input_3;
