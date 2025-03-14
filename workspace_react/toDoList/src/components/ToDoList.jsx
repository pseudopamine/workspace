import React, { useState } from "react"
import MyButton from "./MyButton"
import { data } from "../../data/todoList"
import { icon } from "../../constants/icon";
import styles from './ToDoList.module.css'

const ToDoList = () => {
  const [doItList, newDoItList] = useState(data);
  return (
    <div className={styles.container}>
      <div>ToDoList</div>
      <div>
        <input type="text" />
        <MyButton title={'등록'}/>
      </div>
      <div>
        {
          doItList.map((doIt, i) => {
            return(
              <div key={i} className={styles.willDoIt}>
                <p>
                  {doIt.text}
                </p>
                <img className={styles.img} src={icon.edit_icon}/>
                <img className={styles.img} src={icon.delete_icon}/>
              </div>
            )
          })
        }
        
      </div>
    </div>
  );
};

export default ToDoList;
