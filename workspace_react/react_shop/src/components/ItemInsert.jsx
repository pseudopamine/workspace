import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import styles from './ItemInsert.module.css'

const ItemInsert = () => {
  //입력받은 데이터를 저장할 state 변수
  const [itemInfo, setItemInfo] = useState({});
  const nav = useNavigate();

  //input 태그 값 변경 함수
  const changeInfo = (e) => {
    setItemInfo({
      ...itemInfo,
      [e.target.name] : e.target.value
    });
  }

  //클릭 시 상품 등록 기능 함수
  const insertItem = () => {
    if(!(itemInfo.itemName && itemInfo.itemPrice)){
      alert('상품명과 가격은 필수입력입니다.')
      return;
    }
    axios
        .post('/api/items', itemInfo)
        .then((res) => {
          console.log(res.data);
          if(!confirm('상품을 등록하시겠습니까?')){
            return;
          }
          nav('/');
        })
        .catch(error => console.log(error));
  }

  return (
    <>
      <h2>새 상품 등록</h2>
      <div className={styles.item_insert_container}>
        <div className="container">
          <table className={styles.item_insert_table}>
            <colgroup>
            </colgroup>
            <tbody>
              <tr>
                <td>상품명</td>
                <td>
                  <input className="my-input wide" type="text" name="itemName" value={itemInfo.itemName} onChange={(e) => {changeInfo(e)}} />
                </td>
              </tr>
              <tr>
                <td>상품가격</td>
                <td>
                  <input className="my-input wide" type="text" name="itemPrice" value={itemInfo.itemPrice} onChange={(e) => {changeInfo(e)}} />
                </td>
              </tr>
              <tr>
                <td>판매자</td>
                <td>
                  <input className="my-input wide" type="text" name="seller" value={itemInfo.seller} onChange={(e) => {changeInfo(e)}} />
                </td>
              </tr>
              <tr>
                <td>상품설명</td>
                <td>
                  <textarea cols={70} rows={7} type="text" name="itemIntro" value={itemInfo.itemIntro} onChange={(e) => {changeInfo(e)}} />
                </td>
              </tr>
            </tbody>
          </table>
          <div className={styles.btn_div}>
            <button className="btn btn-large" type="button" onClick={() => {insertItem();}}>등 록</button>
          </div>
        </div>
      </div>
    </>
  );
};

export default ItemInsert;
