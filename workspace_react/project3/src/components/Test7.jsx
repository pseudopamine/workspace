import './Test7.css'
import React, { useState } from "react";

const Test7 = () => {
  const [isShow, setIsShow] = useState(false);

  return (
    <>
      <div>Test7</div>
      <div className='test7' 
      onMouseEnter={(e) => {
        setIsShow(true)
      }} 
      onMouseLeave={(e) => {
        setIsShow(false)
      }}>마우스를 올리면 글자가 보여요</div>
      {
        isShow ? <div className='test7'>React</div> : null
      }
      

    </>
  );
};

export default Test7;
