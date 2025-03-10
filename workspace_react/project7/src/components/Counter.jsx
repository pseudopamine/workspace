import React, { useState } from "react";
import Display from "./Display";
import Controller from "./Controller";

const Counter = () => {
  const [cnt, setCnt] = useState(0);
  
  return (
    <>
      <h2>Simple Counter</h2>
      <Display cnt={cnt}/>
      <Controller setCnt={setCnt} cnt={cnt}/>
    </>
  );
};

export default Counter;
