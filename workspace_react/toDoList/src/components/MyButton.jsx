import React from "react"
import styles from './MyButton.module.css'

const MyButton = ({title, ...props}) => {
  return (
    <>
      <button 
        className={styles.button}
        {...props}
      
      >
      {title}</button>
    </>
  );
};

export default MyButton;
