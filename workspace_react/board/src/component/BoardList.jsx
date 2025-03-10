import React, { useEffect, useState } from "react";
import styles from './BoardList.module.css'
import axios from "axios";
import { useNavigate, useParams, useSearchParams } from "react-router-dom";
import dayjs from "dayjs";

const BoardList = () => {
  const nav = useNavigate();

  //조회한 게시글 목록 데이터를 저장할 state 변수
  const [boardList, setBoardList] = useState([]);
  

  //mount될 때에만 데이터를 받아오겠다
  useEffect((e) => {
    axios
      .get('/api/boards')
      .then((res) => {
        console.log(res.data);
        setBoardList(res.data);
      })
      .catch((error) => {
        console.log(error);
      });
  }, []);

  //제목 클릭 시 조회수를 증가시키겠다.
  // const updateView= () => {
  //   axios
  //     .put(`/api/boards/${boardNum}`, view)
  //     .then((res) => {
  //       console.log(res.data);
  //     })
  //     .catch((error) => {console.log(error);});
  // }
  

  const [data, setData] = useState({
    name : "",
    age : null
  });

  const changeData = (e) => {
    setData({
      ...data,
      [e.target.name] : e.target.value
    })
  }

  //검색창에 입력한 데이터를 저장할 변수
  const [searchData, setSerchData] = useState({
    searchKeyword : 'WRITER',
    searchValue : ''
  });

  //검색창 내용 변경 시 실행되는 함수
  const changeSearchData = (e) => {
    setSerchData({
      ...searchData,
      [e.target.name] : e.target.value
    });
  }


  //검색 버튼 클릭 시 실행 함수
  const searchList = () => {
    axios
      .get(`/api/boards?searchKeyword=${searchData.searchKeyword}&searchValue=${searchData.searchValue}`)
      .then(res => {
        setBoardList(res.data)
      })
      .catch(error => console.log(error));
  }


  return (
    <>
      <div>
        <h2>자유게시판</h2>
        <div>
          <select name="searchKeyword" value={searchData.searchKeyword} onChange={(e) => {changeSearchData(e)}}>
            <option value='TITLE' >제목</option>
            <option value='WRITER'>작성자</option>
          </select>
          <input type="text" name="searchValue" value={searchData.searchValue} onChange={(e) => {changeSearchData(e)}}/>
          <button type="button" onClick={e => searchList()}>검색</button>
        </div>
        <table className={styles.list}>
          <colgroup>
            <col width={"5%"} />
            <col width={"50%"} />
            <col width={"15%"} />
            <col width={"20%"} />
            <col width={"10%"} />
          </colgroup>
          <thead>
            <tr>
              <td>No</td>
              <td>제목</td>
              <td>작성자</td>
              <td>작성일</td>
              <td>조회수</td>
            </tr>
          </thead>
          <tbody>
            { boardList.length === 0 
            ?
            <tr>
              <td colSpan={5}>등록된 게시글이 없습니다.</td>
            </tr>
            :
              boardList.map((board, i) => {
                return (
                  <tr key={i}>
                    <td>{boardList.length - i}</td>
                    <td className="title" onClick={(e) => {
                      
                      nav(`/detail/${board.boardNum}`);
                      }}>{board.title}</td>
                    <td>{board.writer}</td>
                    <td>{dayjs(board.regDate).format('YYYY년 MM월 DD일')}</td>
                    <td>{board.readCnt}</td>
                  </tr>
                );
            })}
          </tbody>
        </table>
        <button type="button" onClick={(e) => {nav('/insert');}}>글쓰기</button>

              {/* 아래 나이, 이름 입력값을 Query String 방식으로 */}
              {/* 버튼 클릭 시 /test url로 전달하세요 */}
              <div>Query String 연습</div>
                <div>나이 : <input type="text" name="age" value={data.age} onChange={(e) => {changeData(e)}}/></div>
                <div>이름 : <input type="text" name="name" value={data.name} onChange={(e) => {changeData(e)}}/></div>
                <button type="button" onClick={(e) => {nav(`/test?age=${data.age}&name=${data.name}`)}}>Query String 실행</button>
      </div>
    </>
  );
};

export default BoardList;
