import React, { useState } from "react";

const Detail = ({board}) => {
  

  return (
    <div>
      <table className="detail-table">
        <colgroup>
          <col width={'20%'}/>
          <col width={'30%'}/>
          <col width={'20%'}/>
          <col width={'30%'}/>
        </colgroup>
        <tbody>
          <tr>
            <td>글번호</td>
            <td>{board.boardNum}</td>
            <td>작성자</td>
            <td>{board.writer}</td>
          </tr>
          <tr>
            <td>글제목</td>
            <td>{board.title}</td>
            <td>조회수</td>
            <td>{board.readCnt}</td>
          </tr>
          <tr>
            <td>글내용</td>
            <td colSpan={3}>{board.content}</td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};

export default Detail;
