import React from 'react'
import './Header.css'
import { Link, useNavigate } from 'react-router-dom'

const Header = () => {
  const nav = useNavigate();

  return (
    <div className='header'>
      <div>
        <Link to={''}>게시판</Link>
      </div>
      <div>
        <span>
          <Link to={'/login'}>LOGIN</Link>
        </span>
        {/* JOIN 글자 클릭 시 로그인 페이지로 이동 */}
        <span onClick={(e) => {nav('/login')}}>JOIN</span>
      </div>
    </div>
  )
}

export default Header