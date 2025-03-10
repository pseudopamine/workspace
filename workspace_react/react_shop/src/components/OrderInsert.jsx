import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

const OrderInsert = () => {
  //상품 목록 리스트를 담을 state 변수
  const [itemList, setItemList] = useState([]);
  

  //입력한 주문 정보를 담을 state 변수
  const [orderInfo, setOrderInfo] = useState({
    itemNum : '',
    price : '상품을 선택하세요',
    buyer : '',
    buyCnt : 1
  });


  const nav = useNavigate();

  //상품 목록 리스트 불러오기
  useEffect(() => {
    axios
        .get('/api/items')
        .then(res => {
          console.log(res.data);
          setItemList(res.data);
        })
        .catch(error => console.log(error));
  }, []);

  //주문하기 클릭 시 input으로 받은 정보를 서버로 보내기
  const insertOrder = (e) => {
    if(!confirm('등록하시겠습니까?')){
      return;
    }
    axios
        .post('/api/orders', orderInfo)
        .then(res => {
          console.log(res.data)
          alert('등록되었습니다.')
          nav('/order-list');
        })
        .catch(error => console.log(error));
  }
  

  //input 태그에 변경값 넣기
  const changeOrderInfo = (e) => {
    if(e.target.name === 'itemNum'){
      let itemPrice = 0;

      for(const item of itemList){
        if(item.itemNum == e.target.value){
          itemPrice = item.itemPrice
        }
      }
      setOrderInfo({
        ...orderInfo,
        [e.target.name] : e.target.value,
        price : itemPrice
      });
    }

    else{
      setOrderInfo({
        ...orderInfo,
        [e.target.name] : e.target.value
      })
    }
  };





  return (
    <>
      <div>--주문등록--</div>
      <table>
        <tbody>
          <tr>
            <td>주문상품</td>
            <td>
              <select name="itemNum" defaultValue={"orderInfo.itemNum"} onChange={(e) => {
                changeOrderInfo(e);
                //상품 단가 input태그의 value도 변경
                
              }}>
                <option defaultValue={''}>----선택----</option>
                {
                  itemList.map((item, i) => {
                    return(
                      <option key={i} value={item.itemNum}>{item.itemName}</option>
                    )
                  })
                }
                
              </select>
            </td>
          </tr>
          <tr>
            <td>상품단가</td>
            <td>
              <input type="text" name="price" value={orderInfo.price} readOnly/>
            </td>
          </tr>
          <tr>
            <td>주문자</td>
            <td>
              <input type="text" name="buyer" value={orderInfo.buyer} onChange={(e) => {changeOrderInfo(e)}}/>
            </td>
          </tr>
          <tr>
            <td>주문수량</td>
            <td>
              <input type="number" name="buyCnt" value={orderInfo.buyCnt} onChange={(e) => {changeOrderInfo(e)}}/>
            </td>
          </tr>
        </tbody>
      </table>
      <button type="button" onClick={() => {insertOrder();}}>주문하기</button>
    </>
  );
};

export default OrderInsert;
