import React, { useState } from "react";
import './Axios_8.css'
import axios from "axios";

const Axios_8 = () => {
  //0. table 및 전체 그림 제작
  //1. 모든 입력값을 저장할 state 변수 설정
  //2. input 태그에 name값과 value 속성값, onChange 작성
  //3. 모든 입력값이 변경될 때마다 실행시킬 함수
  //4. onChange에 3번 함수 설정
  const [orderInfo, setOrderInfo] = useState({
    menu : '',
    each : 1,
    add : '토핑',
    please : '',
    date : ''
  });

  //console.log(orderInfo) //제대로 실행되는지 확인

  //3.
  const changeOrderInfo = (e) => {
    //orderInfo state 변수의 변경 => setOrderInfo();
    setOrderInfo({
      ...orderInfo,
      [e.target.name] : e.target.value
    });
  }

  //5. 전송버튼 클릭 시 서버로 데이터를 보내는 기능의 함수
  //6. java에서 변수와 같은 이름으로 클래스 setter getter 생성자 등등 생성 후 확인
  const sendData = () => {
    axios.post('/api/t9', orderInfo)
        .then((res) => {
          alert('서버로 데이터를 전송하였습니다.')
        })
        .catch((error) => {console.log(error)});
  }

  return (
    <>
      <div>Axios_8</div>
      <table>
        <tbody>
          <tr>
            <td>음식선택</td>
            <td>
              <select name="menu" value={orderInfo.menu} onChange={(e) => {
                changeOrderInfo(e);
              }}>
                <option value="">선택</option>
                <option value="chicken">치킨</option>
                <option value="pizza">피자</option>
                <option value="pigFoot">족발</option>
              </select>
              </td>
          </tr>
          <tr>
            <td>수량</td>
            <td><input name="each" type="number" value={orderInfo.each} onChange={(e) => {
              changeOrderInfo(e);
            }}/></td>
          </tr>
          <tr>
            <td>추가선택</td>
            <td>
              <input name="add" type="radio"  value={'토핑'} checked={orderInfo.add === '토핑'} onChange={(e) => {changeOrderInfo(e);}}/>토핑추가
              <input name="add" type="radio" value={'음료'} checked={orderInfo.add === '음료'} onChange={(e) => {changeOrderInfo(e);}}/>음료추가
              <input name="add" type="radio" value={'공기밥'} checked={orderInfo.add === '공기밥'} onChange={(e) => {changeOrderInfo(e);}}/>공기밥추가
            </td>
          </tr>
          <tr>
            <td>요청추가</td>
            <td><textarea name="please" rows={5} cols={40} value={orderInfo.please} onChange={(e) => {changeOrderInfo(e);}}/></td>
          </tr>
          <tr>
            <td>주문일시</td>
            <td>
              <input name="date" type="date" value={orderInfo.date} onChange={(e) => {changeOrderInfo(e);}}/>
            </td>
          </tr>
        </tbody>
      </table>
      <div>
        <button type="button" onClick={(e) => {
            sendData();
          }}>전송</button>
      </div>
    </>
  );
};

export default Axios_8;
