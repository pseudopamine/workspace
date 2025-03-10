import React from 'react'
import { useParams } from 'react-router-dom'

//해당 컴포넌트가 실행될 때, num이라는 이름으로 데이터가 전달된다.
//URL Parameter로 전달되는 데이트를 받을 때는 useParams() hook을 사용하여 받는다. 
//useParams() hook은 URL Parameter로 전달되는 데이터를 객체 형식으로 리턴시켜준다. 
const BoardDetail = () => {
  const params = useParams();
  console.log(params);

  const {num, age} = useParams();

  return (
    <>
      <div>게시글 상세 페이지</div>
      <div>받은 num 데이터 : {age}</div>
  
    </>
  )
}

export default BoardDetail