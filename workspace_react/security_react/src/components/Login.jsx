import axios from 'axios';
import React, { useState } from 'react'
import { useDispatch } from 'react-redux';
import { useNavigate } from 'react-router-dom';
import { loginReducer } from '../redux/authSlice';
import { axiosInstance } from '../redux/axiosInstance';

const Login = () => {
  //로그인 성공 시 진행되어야 하는 코드를 authSlice에 reducer로 등록했고 이를 사용을 위한 useDispatch() 선언
  const dispatch = useDispatch();
  //로그인 성공시 메인페이지로 이동하기 위한 useNavigate() 선언
  const nav = useNavigate();

  const [loginInfo, setLoginInfo] = useState({
    userId : '',
    userPw : ''
  });

  const handleLoginInfo = (e) => {
    setLoginInfo({
      ...loginInfo,
      [e.target.name] : e.target.value
    });
  }

  //로그인 요청 함수
  const login = () => {
    axiosInstance.post('/user/login', loginInfo)
    .then(res => {
      alert('로그인 성공');
      console.log(res.headers['authorization']);
      dispatch(loginReducer(res.headers['authorization']));
      nav('/')
    })
    .catch(e => {
      //로그인 검증 실패 시 서버에서 401 상태코드를 응답
      if(e.status === 401){
        alert('로그인 실패');
      }
      else{
        console.log(e);
      }
    });
  }

  return (
    <div>
      <input type='text' name='userId' onChange={handleLoginInfo}/>
      <input type='password' name='userPw' onChange={handleLoginInfo}/>
      <button type='button' onClick={login}>로그인</button>
    </div>
  )
}

export default Login