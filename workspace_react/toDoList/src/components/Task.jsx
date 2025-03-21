import React, { useEffect, useState } from "react";
import styles from "./ToDoList.module.css";
import { icon } from "../../constants/icon";
import MyButton from "./MyButton";

const Task = ({e, doList, setDoList, setNewDoIt}) => {
  //수정 상태 여부
  const [isEditing, setIsEditing] = useState(false);

  //수정할 때 열리는 input 태그에 입력할 데이터를 담을 state 변수
  const [newDo, setNewDo] = useState('');

  //input태그에 정보를 담은 후 리렌더링
  useEffect(() => {
    setNewDo(e.text)
  }, [e]);


  //할 일 목록 수정 기능 함수
  const handleToDoList = () => {
    const copyDoList = [...doList]
    const findDoList =  copyDoList.find((doIt) => {return doIt.id === e.id})
    findDoList.text = newDo
    setIsEditing(false)
    alert('수정 완료')
    setDoList(copyDoList);
  }

  //할 일 목록 삭제 기능 함수
  const deleteToDoList = () => {
    if(!confirm('삭제하시겠습니까?')){
      return;
    }
    const copyDoList = [...doList]
    const filterDoList = copyDoList.filter((doIt) => {return doIt.id !== e.id})
    setIsEditing(false)
    alert('삭제 완료')
    setDoList(filterDoList)
  }


  return (
    <>
      {
        isEditing
        ?
        <>
          <div className={styles.willDoIt}>
            <input 
              name="id" 
              value={newDo} 
              onChange={(e) => {setNewDo(e.target.value)}}
            />
            <MyButton title={"확인"} onClick={() => {handleToDoList()}}/>
            <MyButton title={"취소"} onClick={() => {setIsEditing(false)}}/>
          </div>
        </>
        :
        <>
        <div className={styles.willDoIt}>
          <p className={styles.text}>{e.text}</p>
          <img className={styles.img} src={icon.edit_icon} onClick={() => {setIsEditing(true)}}/>
          <img className={styles.img} src={icon.delete_icon} onClick={() => {deleteToDoList()}}/>
        </div>
      </>
  
      }
    </>
    
  );
};

export default Task;
