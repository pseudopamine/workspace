import "./Test3.css"
import React, { useState } from "react";

const Test3 = () => {
  const [show, setShow] = useState('광고닫기');
  //보이는 상태 : 보이면 true ->  안보이게 false로
  const [isShow, setIsShow] = useState(true);
  return (
    <>
      <div>Test3</div>
      <button type="button" onClick={() => {
        setShow(show === '광고닫기' ? '광고보기' : '광고닫기');
        setIsShow(!isShow);
      }}>{show}</button>
      {
        isShow ? <div className="sale">오늘 구매하시면 99% SALE!!!</div> : null
      }
      
    </>
  );
};

export default Test3;
