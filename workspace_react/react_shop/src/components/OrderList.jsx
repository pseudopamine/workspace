import axios from "axios";
import React, { useEffect, useState } from "react";
import styles from './OrderList.module.css'
import dayjs from "dayjs";

const OrderList = () => {
  const [orderList, setOrderList] = useState([]);
  const [isShow, setIsShow] = useState(false);

  useEffect(() => {
    axios
        .get('/api/orders')
        .then(res => {
          console.log(res.data);
          setOrderList(res.data);
          setIsShow(true);
        })
        .catch(error => console.log(error));
  }, [])



  return (
    <>
      <div>OrderList</div>
      <div className={styles.orderList_container}>
        <table className={styles.orderList_table}>
          <thead>
            <tr>
              <td>No</td>
              <td>상품명</td>
              <td>상품단가</td>
              <td>구매수량</td>
              <td>구매가격</td>
              <td>주문자</td>
              <td>주문일</td>
            </tr>
          </thead>
          <tbody>
            {isShow
            ?
              orderList.map((order, i) => {
                return(
                  <tr key={i}>
                    <td>{orderList.length - i}</td>
                    <td>{order.itemList.itemName}</td>
                    <td>{order.itemList.itemPrice}</td>
                    <td>{order.buyCnt}</td>
                    <td>{order.buyPrice}</td>
                    <td>{order.buyer}</td>
                    <td>{dayjs(order.buyDate).format('YYYY.MM.DD HH:mm:ss')}</td>
                  </tr>
                  
                )
              })
              :
              <tr>
                <td colSpan={7}>등록된 주문 정보가 없습니다.</td>
              </tr>
            }
          </tbody>
        </table>
        <div>
          <span>총 주문 금액</span>
          <p>
            {
              
            }
          </p>
        </div>
      </div>
    </>
  );
};

export default OrderList;
