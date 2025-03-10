import './Test4.css'
import React, { useState } from "react";

const Test4 = () => {
  const [numArr, setNumArr] = useState([0, 0, 0]);

  function changeData(index){
    const copyArr = [...numArr];
    copyArr[index] = copyArr[index] + 1;
    setNumArr(copyArr);
  }

  return (
    <>
      <div>Test4</div>
      <span onClick={() => {
        changeData(0);
      }}>{numArr[0]}</span>

      <span onClick={() => {
        changeData(1);
      }}>{numArr[1]}</span>

      <span onClick={() => {
       changeData(2);
      }}>{numArr[2]}</span>
    </>
  );
};

export default Test4;
