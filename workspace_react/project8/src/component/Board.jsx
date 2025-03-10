import React, { useState } from "react";
import List from "./List";
import Detail from "./Detail";
import './Board.css'

const Board = () => {
  //게시글 하나의 정보를 담을 state 변수
  const [board, setBoard] = useState({});

  //상세정보가 보일지/안 보일지 결정하는 state 변수
  const [isShow, setIsShow] = useState(false);

  return (
    <>
      <div className="container">
        <div>
          <h2>게시글 제목</h2>
        </div>
          <List setBoard={setBoard} setIsShow={setIsShow}/>
          {
            isShow ? <Detail board={board}/> : null
          }
          
      </div>
    </>
  );
};

export default Board;
