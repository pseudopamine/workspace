import React from "react";

const Map_forEach = () => {
  function aaa(){
    console.log(3);
  }

  //반복문 : for, for of, forEach(), map()
    
  const arr = [1, 3, 5];
  //forEach
  //() 안의 내용을 반복 실행!
  //매개변수 값으로 함수 입력 가능!
  arr.forEach((item, index) => {
    console.log(`a = ${item}, b = ${index}`);
  });

  //() 안의 내용을 반복 실행
  const result = arr.map((e, i) => {
    console.log(`a = ${e}, b = ${i}`)
    return e * 2;
  });
  console.log(result)

  return (
    <>
      <div>Map_forEach</div>
    </>
  );
};

export default Map_forEach;
