import React from "react";

const OrderDetail = ({productInfo}) => {
  return (
    <>
      <div>
        <h2 className="orderDetail">주문 상세 정보</h2>
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
              <td>{productInfo.itemCode}</td>
              <td className="orderDetail-table-td">상품명</td>
              <td>{productInfo.itemName}</td>
            </tr>
            <tr>
              <td className="orderDetail-table-td">가격</td>
              <td>{productInfo.itemPrice}원</td>
              <td className="orderDetail-table-td">수량</td>
              <td>{productInfo.itemCount}</td>
            </tr>
            <tr>
              <td className="orderDetail-table-td">주문자ID</td>
              <td>{productInfo.orderId}</td>
              <td className="orderDetail-table-td">구매금액</td>
              <td>{productInfo.itemPrice * productInfo.itemCount}원</td>
            </tr>
          </tbody>
        </table>
      </div>
    </>
  );
};

export default OrderDetail;
