import React from 'react'

const Login = () => {
  return (
    <div>
      <div>
        아이디 : <input type='text'/>
      </div>
      <div>
        비밀번호 : <input type='password'/>
      </div>
      <button type='button'>로그인</button>
    </div>
  )
}

export default Login