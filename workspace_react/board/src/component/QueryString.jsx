import axios from "axios";
import React from "react";
import { useSearchParams } from "react-router-dom";

const QueryString = () => {
  
  //쿼리 스트링으로 넘어오는 데이터 받기
  //배열의 첫번째 인자 : 쿼리 스트링으로 넘어오는 데이터 (객체형식)
  //배열의 두번째 인자 : 전달받은 데이터를 변경하는 함수
  const [params, setParams] = useSearchParams();
  console.log(params);

  const data1 = params.get('age')
  console.log(data1)
  const data2 = params.get('name')
  console.log(data2)

  const sendData1 = () => {
    axios
        .get(`/api/replies/test?age=${data1}&name=${data2}`)
        .then()
        .catch();
  }


  return (
    <>
      <div>QueryString 연습</div>
      <button type="button" onClick={() => {sendData1()}}>데이터 전송</button>

    </>
  );
};

export default QueryString;
