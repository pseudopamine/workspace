import axios from "axios";
import dayjs from "dayjs";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import styles from './ItemDetail.module.css'

const ItemDetail = ({viewItem, changeItemInfo, cnt, setCnt}) => {
  // const [viewItem, setViewItem] = useState({});
  // const nav = useNavigate();

  // //상품 상세 조회 데이터 가져오기
  // useEffect(() => {
  //   axios
  //       .get(`/api/items/${itemNum}`)
  //       .then((res) => {
  //         console.log(res.data);
  //         setViewItem(res.data);
  //       })
  //       .catch(error => console.log(error))
  // }, []);

  //input 태그에 변경값 입력
  // const changeItemInfo = (e) => {
  //   setViewItem({
  //     ...viewItem,
  //     [e.target.name] : e.target.value
  //   });
  // }
  
  //수정 버튼 클릭시 상품 수정 함수
  const updateItem = () => {
    if(!confirm('수정하시겠습니까?')){
      return;
    }
    axios
        .put(`/api/items/${viewItem.itemNum}`, viewItem)
        .then(res => {
          alert('수정되었습니다.');
          //게시글 목록을 다시 조회(itemList 리렌더링)
          setCnt(cnt + 1);
        })
        .catch(error => console.log(error));
  }

  return (
    <>
      <div className={styles.detail_div}>
        <h2>상품 상세 정보</h2>
        <table className={styles.detail_table}>
          <tbody>
            <tr>
              <td>상품번호</td>
              <td>
                {/* 바뀌지 않을 값이니 name 태그 넣지 않아도 무방 */}
                <input className="my-input wide" type="text" value={viewItem.itemNum} readOnly={true} />
              </td>
            </tr>
            <tr>
              <td>상품명</td>
              <td>
                <input className="my-input wide" type="text" name="itemName"  value={viewItem.itemName} onChange={(e) => {changeItemInfo(e)}} />
              </td>
            </tr>
            <tr>
              <td>상품가격</td>
              <td>
                <input className="my-input wide" type="text" name="itemPrice"  value={viewItem.itemPrice} onChange={(e) => {changeItemInfo(e)}} />
              </td>
            </tr>
            <tr>
              <td>판매자</td>
              <td>
                <input className="my-input wide" type="text" name="seller" value={viewItem.seller} readOnly={true} />
              </td>
            </tr>
            <tr>
              <td>상품등록일</td>
              <td>
                <input className="my-input wide" type="text" name="regDate" value={viewItem.regDate ? dayjs(viewItem.regDate).format('YYYY.MM.DD HH:mm:ss') : ''} readOnly={true} />
              </td>
            </tr>
            <tr>
              <td>상품설명</td>
              <td>
                <textarea cols={50} rows={5} type="text" name="itemIntro" value={viewItem.itemIntro} onChange={(e) => {changeItemInfo(e)}} />
              </td>
            </tr>
          </tbody>
        </table>
        <button className="btn btn-large" type="button" onClick={() => {updateItem()}}>수정하기</button>
      </div>
    </>
  );
};

export default ItemDetail;
