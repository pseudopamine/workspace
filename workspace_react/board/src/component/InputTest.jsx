import React, { useState } from "react";

const InputTest = () => {
  //id input 태그에 입력한 데이터를 저장할 변수를 만들고
  //input 태그의 초기값은 화면이 뜰 때 보여주고 싶은 데이터로 초기화
  const [data, setData] = useState({
    id : '',
    age : '',
    name : ''
  });

  const changeData = (e) => {
    setData({
      ...data,
      [e.target.name] : e.target.value
    })
  }
  console.log(data)

  return (
    <>
      <div>InputTest</div>

      <div>id : 
        <input type="text" name="id" value={data.id} onChange={(e) => {changeData(e)}} /></div>




      <div>나이 : 
        <input type="text" name="age" value={data.age} onChange={(e) => {changeData(e)}}/></div>


      <div>이름 : <input type="text" name="name" value={data.name} onChange={(e) => {changeData(e)}}/></div>

    </>
  );
};

export default InputTest;
