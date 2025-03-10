import "./Test2.css"
import React, { useState } from "react";

const Test2 = () => {
  const [onOff, setSwitch] = useState('ON');
  const [btnText, setBtnText] = useState('OFF')
  return (
    <>
      <h2>Test2</h2>
      <div className="size">{onOff}</div>
      <button type="button" onClick={() => {
        setSwitch(onOff === 'ON' ? 'OFF' : 'ON' );
        setBtnText(btnText === 'ON' ? 'OFF' : 'ON' );
      }}>{btnText}</button>
    </>
  );
};

export default Test2;
