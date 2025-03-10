import axios from "axios";
import React, { useEffect, useState } from "react";

const List = ({setBoard, setIsShow}) => {
  //서버에서 받아오는 게시글 목록 데이터를 저장할 state변수
  //List에 모든 정보가 담기고 부모 컴퍼넌트인 Board에서는 데이터를 사용할 일이 없으니 
  // List 컴퍼넌트에서 state 함수 사용
  const [boardList, setBoardList] = useState([]);

  //그림 다 그린 후(마운트, 컴포넌트가 리렌더 될 때)
  useEffect(() => {
    axios.get('/api/getBoardList')
        .then((res) => {
          console.log(res.data)
          setBoardList(res.data);
        })
        .catch((error) => {
          console.log('오류발생')
          console.log(error)
        })
  } , []);
  //의존성 배열을 넣어서 어떻게 작성하냐에 따라 useEffect의 리렌더링 시점의 실행 여부를 결정
  //의존성 배열을 빈 배열로 선언하면 useEffect의 내용은 mount될 때에만 실행!


  return (
    <div>
      <table className="list-table">
        <colgroup>
          <col width={'10%'}/>
          <col width={'*'}/>
          <col width={'20%'}/>
          <col width={'20%'}/>
        </colgroup>
        <thead>
          <tr>
            <td>No</td>
            <td>제목</td>
            <td>작성자</td>
            <td>조회수</td>
          </tr>
        </thead>
        <tbody>
          {//boardList에 있는 목록 하나를 board에 넣겠다
            boardList.map((board, i) => {
              return(
                <tr key={i}>
                  <td>{boardList.length - i}</td>
                  <td>
                    <span onClick={(e) => {
                      //제목 글자만을 클릭했을 때(여백말고)
                      //클릭한 제목의 게시글에서 글번호를 찾아서 boardList에 저장된 게시글 목록 중
                      //글번호가 일치하는 게시글 정보를 검색
                      // board.boardNum;
                      for(let i = 0 ; i < boardList.length ; i++){
                        if(boardList[i].boardNum == board.boardNum){
                          setBoard(boardList[i]);
                        }
                      }
                      setIsShow(true);
                    }}>{board.title}</span>
                  </td>
                  <td>{board.writer}</td>
                  <td>{board.readCnt}</td>
                </tr>
              )
            })
          }
        </tbody>
      </table>
    </div>
  );
};

export default List;
