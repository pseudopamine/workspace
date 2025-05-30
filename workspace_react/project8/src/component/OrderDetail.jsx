import React from "react";

const OrderDetail = ({productInfo}) => {
  return (
    <>
      <div>
        <h2 className="orderDetail">주문상세 정보</h2>
      </div>
      <div>
        <table className="orderDetail-table">
          <colgroup>
          <col width={'25%'}/>
          <col width={'25%'}/>
          <col width={'25%'}/>
          <col width={'25%'}/>
          </colgroup>
          <tbody>
            <tr>
              <td className="orderDetail-table-td">상품번호</td>
              <td>{productInfo.productNum}</td>
              <td className="orderDetail-table-td">상품명</td>
              <td>{productInfo.product}</td>
            </tr>
            <tr>
              <td className="orderDetail-table-td">가격</td>
              <td>{productInfo.price}원</td>
              <td className="orderDetail-table-td">수량</td>
              <td>{productInfo.cnt}</td>
            </tr>
            <tr>
              <td className="orderDetail-table-td">주문자ID</td>
              <td>{productInfo.orderId}</td>
              <td className="orderDetail-table-td">구매금액</td>
              <td>{productInfo.price * productInfo.cnt}원</td>
            </tr>
          </tbody>
        </table>
      </div>
    </>
  );
};

export default OrderDetail;
