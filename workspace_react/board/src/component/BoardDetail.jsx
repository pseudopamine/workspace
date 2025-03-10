import axios from "axios";
import dayjs from "dayjs";
import React, { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import styles from './BoardDetail.module.css'

const BoardDetail = () => {
  const nav = useNavigate();
  const {boardNum} = useParams();

  //조회한 상세정보 데이터를 저장할 state 변수
  const [board, setBoard] = useState({});

  //해당 게시글의 댓글 목록 정보 저장할 state 변수
  const [replyList, setReplyList] = useState([]);

  //등록할 댓글 입력 정보를 저장할 state 변수
  const [replyInfo, setReplyInfo] = useState({
    writer : '',
    content : '',
    boardNum : boardNum
  });

  //댓글 입력시 실행되는 함수
  const changeReplyInfo = (e) => {
    setReplyInfo({
      ...replyInfo,
      [e.target.name] : e.target.value
    })
  }

  //특정 목록 상세조회 데이터 불러오기
  useEffect(() => {
    axios.get(`/api/boards/${boardNum}`)
        .then((res) => {
          console.log(res.data)
          setBoard(res.data)
        })
        .catch(error => console.log(error))
  }, []);
  

  const [num, setNum] = useState(0);
  //댓글 목록 정보 불러오기, 마운트 + num값이 변경될 때도 실행
  useEffect(() => {
    axios
        .get(`/api/replies/${boardNum}`)
        .then(res => {
          console.log(res.data);
          setReplyList(res.data); //satate변경함수 호출해서 replyList에 data담기
        })
        .catch(error => console.log(error));
  }, [num]);

  // //한 컴포넌트에서 하나의 useEffect로 조회 기능 여러 개 동시에 실행하기
  // useEffect(() => {
  //   axios
  //       .all([
  //         axios.get(`/api/boards/${boardNum}`), 
  //         axios.get(`/api/replies/${boardNum}`)
  //       ]) //여러개 들어가니까 배열 형태
  //       .then(axios.spread((res1, res2) => {
  //         console.log(res1.data);
  //         console.log(res2.data);
  //         setBoard(res1.data);
  //         setReplyList(res2.data);
  //       }))
  //       .catch(error => console.log(error));
  // }, []);

  //댓글 입력 데이터 보내는 기능
  const insertReply = () => {
    if(!confirm('댓글을 등록하시겠습니까?')){
      return;
    }
    axios
        .post('/api/replies', replyInfo)
        .then(res => {
          console.log(res.data);
          alert('댓글이 등록되었습니다.')
          setNum(num + 1)
          setReplyInfo({
            ...replyInfo,
            writer : '',
            content : ''
          });
        })
        .catch(error => console.log(error));  
  }

  //특정 목록 삭제 함수
  const deleteBoard = () => {
    if(!confirm('정말 삭제하시겠습니까?')){
      return;
    }
    axios.delete(`/api/boards/${boardNum}`)
        .then((res) => {
          alert('글이 삭제되었습니다.');
          nav('/');
        })
        .catch((error) => {console.log(error)})
  }

  //특정 댓글 삭제 기능 함수
  const deleteReply = (replyNum) => {
    if(!confirm('댓글 삭제하시겠습니까?')){
      return;
    }
    axios
      .delete(`/api/replies/${replyNum}`)
      .then(res => {
        //다시 댓글 목록을 조회 -> num값 변경
        setNum(num + 1);
      })
      .catch(error => console.log(error));
  }

  return (
    <div className={styles.detail_container}>
      <h2>게시글 상세보기</h2>
      <div className="table-div">
        <table className={styles.detail_table}>
          <colgroup>
            <col width={"10%"}/>
            <col width={"*"}/>
            <col width={"10%"}/>
            <col width={"15%"}/>
            <col width={"10%"}/>
            <col width={"15%"}/>
          </colgroup>
          <tbody>
            <tr>
              <td>작성일</td>
              <td>{dayjs(board.regDate).format('YYYY.MM.DD HH:mm:ss')}</td>
              <td>작성자</td>
              <td>{board.writer}</td>
              <td>조회수</td>
              <td>{board.readCnt}</td>
            </tr>
            <tr>
              <td>제목</td>
              <td colSpan={5} className={styles.letf_text}>{board.title}</td>
            </tr>
            <tr>
              <td>내용</td>
              <td colSpan={5} className={styles.letf_text}>{board.content}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <div className={styles.btn_div}>
        <button type="button" onClick={(e) => {nav('/');}}>목록가기</button>
        <button type="button" onClick={(e) => {nav(`/update/${boardNum}`)}}>수정</button>
        <button type="button" onClick={(e) => {deleteBoard();}}>삭제</button>
      </div>
      <div className={styles.reg_reply_div}>
        <input type="text" placeholder="작성자" name="writer" value={replyInfo.writer} onChange={e => changeReplyInfo(e)}/>
        <input type="text" placeholder="댓글 내용 입력" name="content" value={replyInfo.content} onChange={e => changeReplyInfo(e)}/>
        <button type="button" onClick={e => insertReply()}>댓글등록</button>
      </div>
      <div className={styles.reply_list_div}>
        {
          replyList.map((reply, i) => {
            return(
              <div key={i} className={styles.reply}>
                <span>{reply.writer}</span>
                <span>{dayjs(reply.regDate).format('YYYY.MM.DD HH:mm:ss')}</span>
                <div>
                  <p>{reply.content}</p>
                  <button type="button" onClick={e => {deleteReply(reply.replyNum)}}>삭제</button>
                </div>
              
              </div>
            );
          })
        }
        

      </div>
    </div>
  );
};

export default BoardDetail;
