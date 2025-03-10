import axios from "axios";
import React, { useEffect, useState } from "react";

const OrderList = ({setProductInfo, setIsShow}) => {
  const [orderList, setOrderList] = useState([])

  useEffect(() => {
    axios.get('/api/orders')
        .then((res) => {
          console.log('통신성공')
          console.log(res.data)
          setOrderList(res.data)
        })
        .catch((error) => {console.log(error)})
  }, []);

  return (
    <>
      <div>
        <h2 className="orderList">주 문 목 록</h2>
        <table className="orderList-table">
          <colgroup>
            <col width={'10%'}/>
            <col width={'30%'}/>
            <col width={'25%'}/>
            <col width={'15%'}/>
            <col width={'20%'}/>
          </colgroup>
          <thead>
          <tr>
              <td>No</td>
              <td>상품명</td>
              <td>상품가격</td>
              <td>수량</td>
              <td>총구매가격</td>
            </tr>
          </thead>
          <tbody className="orderList-table-body">
          {
              orderList.map((order, i) => {
                return(
                  <tr key={i} onClick={(e) => {
                    for(let i = 0 ; i < orderList.length ; i++){
                      if(orderList[i].itemCode == order.itemCode){
                        setProductInfo(orderList[i])
                      }
                    }
                    setIsShow(true)
                  }}>
                    <td>{orderList.length - i}</td>
                    <td>{order.itemName}</td>
                    <td>{order.itemPrice}원</td>
                    <td>{order.itemCount}</td>
                    <td>{order.itemPrice * order.itemCount}원</td>
                  </tr>
                )
              })
            }
            
          </tbody>
        </table>
      </div>
    </>
  );
};

export default OrderList;
