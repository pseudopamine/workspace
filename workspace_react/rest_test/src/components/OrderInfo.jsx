import React, { useState } from "react";
import './OrderInfo.css'
import OrderList from "./OrderList";
import OrderDetail from "./OrderDetail";

const OrderInfo = () => {
  const [productInfo, setProductInfo] = useState({});

  const [isShow, setIsShow] = useState(false);
  

  return (
    <>
      <div className="container">
          <OrderList setProductInfo={setProductInfo} setIsShow={setIsShow}/>
          {
            isShow ? <OrderDetail productInfo={productInfo}/> : null
          }
          
      </div>
    </>
  );
};

export default OrderInfo;
