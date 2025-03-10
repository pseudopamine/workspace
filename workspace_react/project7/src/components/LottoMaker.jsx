import React, { useState } from "react";
import LottoCounter from "./LottoCounter";
import LottoButton from "./LottoButton";

const LottoMaker = () => {
  const [cnt, setCnt] = useState([0, 0, 0, 0, 0, 0]);
  


  return (
    <>
      <div>로또번호 생성기</div>
      <LottoCounter cnt={cnt[0]} />
      <LottoButton setCnt={setCnt} />
    </>
  );
};

export default LottoMaker;
