import axios from "axios";
import React from "react";

const LottoNum = (props) => {
  //버튼 클릭 시 자바에서 1 ~ 45의 랜덤한 정수 받아오는 함수
    const getLottoNum = (index) => {
      axios.get('/api/getLottoNum')
          .then((res) => {
            console.log(res.data);
            const copyLotto = [...props.lotto]
            copyLotto[index] = res.data;
            props.setLotto(copyLotto)
          })
          .catch((error) => {
            console.log('오류발생');
            console.log(error);
          });
    }

  return (
    <>
      <div className="num">
        <div className="display">{props.num}</div>
        <div className="btn-div">
          <button type="button"onClick={(e) => {
            getLottoNum(props.i);
          }}>생 성</button>
        </div>
      </div>
    </>
  );
};

export default LottoNum;
