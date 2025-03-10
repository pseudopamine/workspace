import axios from "axios";
import React from "react";
import { useEffect } from "react";
import { useState } from "react";

const Axios_1 = () => {
  //1. 서버에서 받은 데이터를 저장할 state 변수 만들기
  const [num, setNum] = useState(0);

  //3. mount 될 때에만 서버에서 데이터를 받기 위해 useEffect 사용
  useEffect(() => {
     //2. 서버에서 데이터 받기
     //7. get 안에는 데이터를 요청할 url을 작성
  axios.get('/api/t2')
      //4.통신 성공 후 실행 내용 작성 (화살표함수)
      //5.res는 통신 성공에 대한 모든 정보가 담겨있는 객체
      .then((res) => {
        setNum(res.data); //8. 통신 성공시 num변수에 서버에서 받아온 데이터를 저장하겠다!
      })
      //4.통신 실패 후 실행 내용 작성 (화살표함수)
      //6.error는 통신 실패에 대한 모든 정보가 담겨있는 객체
      .catch((error) => {});
  }, []);

  
  return (
    <>
      <h3>Axios_1 : 데이터 받기1</h3>
      <p>받은 데이터 : {num}</p> 
    </>
  );
};

export default Axios_1;
