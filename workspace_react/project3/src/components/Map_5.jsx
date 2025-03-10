import React from "react";
import './Map_5.css'

const Map_5 = () => {
  const boardList = [
    {
      boardNum : 1,
      title : '첫번째 글',
      writer : '김자바',
      createDate : '2025-01-13',
      readCnt : 5
    },
    {
      boardNum : 2,
      title : '두번째 글',
      writer : '김자바',
      createDate : '2025-01-17',
      readCnt : 3
    },
    {
      boardNum : 3,
      title : '세번째 글',
      writer : '이자바',
      createDate : '2025-01-10',
      readCnt : 0
    },
    {
      boardNum : 4,
      title : '네번째 글',
      writer : '이자바',
      createDate : '2025-01-19',
      readCnt : 10
    },
    {
      boardNum : 5,
      title : '다섯번째 글',
      writer : '이자바',
      createDate : '2025-01-01',
      readCnt : 1
    },
  ];
  return (
    <>
      <div>Map_5</div>
      <table>
        <thead>
          <tr className="head">
            <td>No</td>
            <td>글번호</td>
            <td>제목</td>
            <td>작성자</td>
            <td>작성일</td>
            <td>조회수</td>
          </tr>
        </thead>
        <tbody>
          {
            boardList.map((boardInfo, i) => {
              return (
                <tr className="body" key={i}>
                  <td>{boardList.length - i}</td>
                  <td>{boardInfo.boardNum}</td>
                  <td>{boardInfo.title}</td>
                  <td>{boardInfo.writer}</td>
                  <td>{boardInfo.createDate}</td>
                  <td>{boardInfo.readCnt}</td>
                </tr>
              )
            })
          }
        </tbody>
      </table>
    </>
  );
};

export default Map_5;
