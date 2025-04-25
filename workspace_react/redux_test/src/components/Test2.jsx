import React from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { handleMemberId } from '../redux/memberSlice';

const Test2 = () => {
  const member = useSelector(state => state.member)
  const dispatch = useDispatch();

  return (
    <div>
      <p>회원아이디 : {member.memId}</p>
      <p>회원 나이 : {member.memAge}</p>
      <button type='button' onClick={() => {
        dispatch(handleMemberId("kim"));
      }}>회원 아이디 변경</button>
    </div>
  )
}

export default Test2