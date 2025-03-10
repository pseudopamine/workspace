import React, { useEffect, useState } from "react";
import "./Board.css"
import axios from "axios";
import List from "./List";
import Detail from "./Detail";

const Board = () => {
  const [info, setInfo] = useState([{
    num : 0,
    title : "",
    name : "",
    cnt : 0,
    content : ""
  }]);

  const [detail, setDetail] = useState({
    num : 0,
    title : "",
    name : "",
    cnt : 0,
    content : ""
  });

  const getContent = (index) => {
    axios.get('/api/getContents')
        .then((res) => {
          console.log('통신성공')
          console.log(res.data)
          // const copyInfo = [...info]
          // copyInfo[index] = res.data
          // setInfo(copyInfo)
          setInfo(res.data)
        })
        .catch((error) => {
          console.log('오류발생')
          console.log(error)
        });
  }
  
  useEffect(() => {
    getContent();
  }, [])

  const getDetail = () => {
    for(let i = 0 ; i < info.length ; i++){
      if(info[i].num == info.num){
        setDetail(info[i])
      }
    }
  }
  


  return (
    <>
      <div className="container">
        <h2>게시글 목록</h2>
        <div className="top">
          <table>
            <thead className="head">
              <tr>
                <td>No</td>
                <td>제목</td>
                <td>작성자</td>
                <td>조회수</td>
              </tr>
            </thead>
            <tbody className="body">
              {/* {
                info.map((info1, i) => {
                  return (
                    <tr key={i}>
                      <td>{info1.num}</td>
                      <td type="button" onClick={(e) => {setInfo();}}>{info1.title}</td>
                      <td>{info1.name}</td>
                      <td>{info1.cnt}</td>
                    </tr>
                  )
                })
              } */}
            
              {
                info.map((info1, i) => {
                  return(
                    <List key={i} info={info} setInfo={setInfo} info1={info1} getContent={getContent} getDetail={getDetail} setDetail={setDetail}/>
                  )
                })
              }
            </tbody>
          </table>
        </div>
        <div className="bottom">
          <table>
            <tbody>
            <tr>
              <td>글번호</td>
              <td>{info.num}</td>
              <td>작성자</td>
              <td>{info.name}</td>
            </tr>
            <tr>
              <td>글제목</td>
              <td>{info.title}</td>
              <td>조회수</td>
              <td>{info.cnt}</td>
            </tr>
            <tr>
              <td>글내용</td>
              <td colSpan={3}>{info.content}</td>
            </tr>
              {/* <Detail info={info}/> */}
            </tbody>
          </table>
        </div>
      </div>
      
    </>
  );
};

export default Board;
