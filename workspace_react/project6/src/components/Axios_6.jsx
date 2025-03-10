import axios from "axios";
import React from "react";

const Axios_6 = () => {

  //axios post()함수의 두번째 매개변수로
  //자바로 전달할 데이터를 작성할 수 있다
  //자바로 전달할 데이터는 객체형식으로 지정해야 한다.
  axios.post('/api/t6', {name : 'kim', age : 20}).then().catch();

  return (
    <>
      <div>Axios를  사용하여 서버에 데이터 전달하기</div>
    </>
  );
};

export default Axios_6;
