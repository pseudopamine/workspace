import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import styles from './ItemList.module.css'
import ItemDetail from "./ItemDetail";
import dayjs from "dayjs";

const ItemList = () => {
  //조회한 상품 목록 데이터를 저장할 state 변수
  const [itemList, setItemList] = useState([]);

  //상품 상세 조회 데이터 저장할 state 변수
  const [viewItem, setViewItem] = useState(null);

  //상세 정보 내용 노출여부 변수
  // const [isShow, setIsShow] = useState(false);

  const [cnt, setCnt] = useState(0);

  //mount될 때만 정보를 받아오겠다.
  useEffect(() => {
    axios
        .get('/api/items')
        .then((res) => {
          console.log(res.data);
          setItemList(res.data);
        })
        .catch(error => console.log(error));
  }, [cnt]);


  //상품명 클릭 시 상세 정보 조회 함수
  const getDetail = (itemNum) => {
    axios
        .get(`/api/items/${itemNum}`)
        .then((res) => {
          console.log(res.data);
          setViewItem(res.data);
          // setIsShow(true)
        })
        .catch(error => console.log(error))
  }


  //input 태그에 변경값 입력
  const changeItemInfo = (e) => {
    setViewItem({
      ...viewItem,
      [e.target.name] : e.target.value
    });
  }
  
  // //수정 버튼 클릭시 상품 수정 함수
  // const insertItem = (itemNum) => {
  //   if(!confirm('수정하시겠습니까?')){
  //     return;
  //   }
  //   axios
  //       .put(`/api/items/${itemNum}`, viewItem)
  //       .then(res => {
  //         alert('수정되었습니다.');
  //       })
  //       .catch(error => console.log(error));
  // }



  return (
    <>
      
      <div className={styles.item_list_container}>
        <div className={styles.list_div}>
          <h2>상품목록</h2>
          <table className={styles.list_table}>
            <colgroup>
            <col width={''}/>
            <col width={''}/>
            <col width={''}/>
            <col width={''}/>
            </colgroup>
            <thead>
              <tr>
                <td>No</td>
                <td>상품명</td>
                <td>상품가격</td>
                <td>판매자</td>
              </tr>
            </thead>
            <tbody>
              {
                itemList.map((item, i) => {
                  return(
                    <tr key={i}>
                      <td>{itemList.length - i}</td>
                      <td onClick={() => {
                        getDetail(item.itemNum)
                      }}>{item.itemName}</td>
                      <td>￦{item.itemPrice.toLocaleString()}G</td>
                      <td>{item.seller}</td>
                    </tr>
                  )
                })
              }
            </tbody>
          </table>
          <p>총 {itemList.length}개의 상품이 등록되었습니다.</p>
        </div>
        {
          // isShow ? <ItemDetail viewItem={viewItem} changeItemInfo={changeItemInfo} /> : null
          viewItem && <ItemDetail viewItem={viewItem} changeItemInfo={changeItemInfo} cnt={cnt} setCnt={setCnt}/>
        }
      </div>

    </>
  );
};

export default ItemList;
