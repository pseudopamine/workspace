import axios from "axios";
import React, { useEffect, useState } from "react";

const Axios_7 = () => {
  const [totalScore, setTotalScore] = useState(0);

  const student = {
    name: "Jin",
    korScore: 80,
    engScore: 70,
    mathScore: 90,
  };

  //아래 코드의 결과 전달되는 데이터를 자바에서 받은 후
  //전달된 국, 영, 수 점수의 총점을 다시 리액트로 가져와서
  //리액트 화면에 총점을 보여주세요!

  useEffect(() => {
    axios
      .post("/api/t8", student)
      .then((res) => {
        console.log(res.data);
        setTotalScore(res.data);
      })
      .catch((error) => {});
  }, []);

  return (
    <>
      <div>Axios_7</div>
      <p>총점 : {totalScore}</p>
    </>
  );
};

export default Axios_7;
