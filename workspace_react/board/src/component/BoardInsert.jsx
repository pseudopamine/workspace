import axios from "axios";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

const BoardInsert = () => {
  const nav = useNavigate();
  const [newBoard, setNewBoard] = useState({});
  
  //input 태그에 값 입력시 실행 할 함수
  const changeNewBoard = (e) => {
    setNewBoard({
      ...newBoard,
      [e.target.name] : e.target.value
    })
  }

  //글등록 버튼 클릭시 서버의 insert api 실행
  const createBoard = () => {
    if(newBoard.title === ''){
      alert('제목을 입력하시오');
      return;
    }
    else if(newBoard.writer === ''){
      alert('작성자를 입력하시오');
      return;
    }
    axios.post('/api/boards', newBoard)
        .then((res) => {
          console.log(res.data);
          alert('글이 등록되었습니다.');
          nav('/');
        })
        .catch((error) => {console.log(error);})
  }

  console.log(newBoard);

  return (
    <>
      <h2>새 글 작성</h2>
      <div>
        <table>
          <colgroup>
            <col width={"10%"}/>
            <col width={"90%"}/>
          </colgroup>
          <tbody>
            <tr>
              <td>제목</td>
              <td>
                <input name="title" type="text" value={newBoard.title} onChange={(e) => {console.log(e); changeNewBoard(e);}} />
              </td>
            </tr>
            <tr>
              <td>작성자</td>
              <td>
                <input name="writer" type="text" value={newBoard.writer} onChange={(e) => {changeNewBoard(e);}} />
              </td>
            </tr>
            <tr>
              <td>내용</td>
              <td>
                <textarea name="content" cols={70} rows={7} value={newBoard.content} onChange={(e) => {changeNewBoard(e);}}/>
              </td>
            </tr>
          </tbody>
        </table>
        <button type="button" onClick={(e) => {createBoard();}}>글등록</button>
      </div>
    </>
  );
};

export default BoardInsert;
