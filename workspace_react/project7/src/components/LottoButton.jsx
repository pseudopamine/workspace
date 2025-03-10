import axios from "axios";
import React from "react";

const LottoButton = ({setCnt}) => {
  
    const callLotto = () => {
    axios.get('/api/getLottoNum')
        .then((res) => {
          console.log('통신성공')
          console.log(res.data)
          setCnt(res.data)
        })
        .catch((error) => {console.log(error)})
  }

  return (
    <>
      <div>
        <button type="button" onClick={(e) => {callLotto()}}>생성</button>
        <button type="button" onClick={(e) => {callLotto()}}>생성</button>
      </div>
    </>
  );
};

export default LottoButton;
