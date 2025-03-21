import React, { useEffect, useState } from "react";
import MyButton from "./MyButton";
import { data } from "../../data/todoList";
import { icon } from "../../constants/icon";
import styles from "./ToDoList.module.css";
import Task from "./Task";

const ToDoList = () => {
  //할 일 목록 저장하는 state 변수
  const [doList, setDoList] = useState(data);

  //input 태그로 받은 데이터를 새로 저장
  const [newDoIt, setNewDoIt] = useState('')

  //데이터 유효성 검사
  const validateNewDoIt = () => {
    let isValid = true
    if(newDoIt === ''){
      alert('할 일은 필수 입력입니다.')
      isValid = false
    }
    return isValid;
  }

  //저장되어있는 id 중 가장 큰 id를 찾고 그 값에 +1 한 id를 key로 새로운 데이터를 받는다
  const submitToDoList = () => {
    //빈 칸 입력 방지
    if(!validateNewDoIt()){
      return;
    }
    let max = Math.max(...doList.map((e, i) => e.id)) + 1
    //저장할 객체
    const newData = {
      id : max,
      text : newDoIt
    };
    setDoList([...doList, newData])
    setNewDoIt('')
    alert('등록 완료')
  }

  return (
    <div className={styles.container}>
        <div>ToDoList</div>
      <div className={styles.sub_container}>
        <div>
          <input 
            type="text" 
            placeholder="+ Add a Task"
            className={styles.input}
            name="id"
            value={newDoIt}
            onChange={(e) => {setNewDoIt(e.target.value)}}
          />
          <MyButton title={"등록"} onClick={() => {submitToDoList()}}/>
        </div>
        <div className={styles.task}>
          {
            doList.map((e, i) => {
            return (
              <Task key={i} e={e} doList={doList} setDoList={setDoList} setNewDoIt={setNewDoIt}/>
              );
            })
          }
          
        </div>
      </div>
    </div>
  );
};

export default ToDoList;
