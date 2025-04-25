import React from 'react'
import { Link } from 'react-router-dom';
import { isAdmin, isAuthenticated } from '../redux/authCheck';
import { useSelector } from 'react-redux';

const Menu = () => {
  const token = useSelector(state => state.auth.token);

  return (
    <div style={{display:'flex', gap : '30px', marginBottom : '30px'}}>
        <div><Link to={''}>메인화면</Link></div>
        <div><Link to={'/anyone'}>아무나화면</Link></div>

        { isAuthenticated(token) && <div><Link to={'/user'}>인증된회원만</Link></div> }
        
        { isAdmin(token) && <div><Link to={'/admin'}>관리자만</Link></div> }
        
      </div>
  )
}

export default Menu