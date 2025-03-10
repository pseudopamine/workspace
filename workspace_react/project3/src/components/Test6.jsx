import './Test6.css'
import React, { useState } from "react";

const Test6 = () => {
  const [count, setCount] = useState(0);

  const changeCount = (val) => {
    setCount(count + val)
  }

  return (
    <>
      <div>Test6</div>
      <div className='Simple'>
      Simple Counter <br />
      </div>
      <div className='Count'>
      <p>현재 카운트 : </p>
      <h3> {count} </h3>
      </div>

      <div className='Button'> 
      <button type="button" onClick={(e) => {
        changeCount(-1);
      }}>-1</button>
      <button type="button" onClick={(e) => {
        changeCount(-10);
      }}>-10</button>
      <button type="button" onClick={(e) => {
        changeCount(-100);
      }}>-100</button>
      <button type="button" onClick={(e) => {
        changeCount(100);
      }}>+100</button>
      <button type="button" onClick={(e) => {
        changeCount(10);
      }}>+10</button>
      <button type="button" onClick={(e) => {
        changeCount(1);
      }}>+1</button>
      </div>
    </>
  );
};

export default Test6;
