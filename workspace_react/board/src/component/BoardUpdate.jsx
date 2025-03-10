import axios from "axios";
import dayjs from "dayjs";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";

const BoardUpdate = () => {
  const nav = useNavigate();
  const {boardNum} = useParams();
  //상세 정보를 저장할 변수
  const [updateData, setUpdateData] = useState({});

  
  //일단 get으로 기존 정보 가져오기
  useEffect(() => {
    axios.get(`/api/boards/${boardNum}`)
    .then(res => {
      console.log(res.data);
      setUpdateData(res.data);
    })
    .catch(error => console.log(error))
  }, []);

  
  const changeNewBoard = (e) => {
    setUpdateData({
      ...updateData,
      [e.target.name] : e.target.value
    });
  }


  //수정 후 서버로 데이터 보내는 함수
  const updateNewData = () => {
    axios.put(`/api/boards/${boardNum}`, updateData)
        .then(res => {
          if(!confirm('수정하시겠습니까?')){
            return;
          }
          nav(`/detail/${updateData.boardNum}`);
        })
        .catch(error => console.log(error))  //매개변수가 하나뿐이면 소괄호 생략 가능, 받아오는 함수가 한 줄이면 중괄호도 생략 가능
  }

  return (
    <>
      <h2>수정하기</h2>
      <div>
        <table>
          <colgroup>
            <col width={"25%"}/>
            <col width={"25%"}/>
            <col width={"25%"}/>
            <col width={"25%"}/>
          </colgroup>
          <tbody>
            <tr>
              <td>작성일</td>
              <td>{dayjs(updateData.regDate).format('YYYY.MM.DD HH:mm:ss')}</td>
              <td>작성자</td>
              <td>{updateData.writer}</td>
            </tr>
            <tr>
              <td>제목</td>
              <td colSpan={4}>
                <input name="title" type="text" value={updateData.title} onChange={(e) => {changeNewBoard(e);}}/>
              </td>
            </tr>
            <tr>
              <td>내용</td>
              <td colSpan={4}>
                <textarea name="content" cols={70} rows={7} value={updateData.content} onChange={(e) => {changeNewBoard(e);}}/>
              </td>
            </tr>
          </tbody>
        </table>
        <button type="button" onClick={() => {nav(`/detail/${boardNum}`);}}>뒤로가기</button>
        <button type="button" onClick={() => {updateNewData();}}>수정</button>
      </div>
    </>
  );
};

export default BoardUpdate;
