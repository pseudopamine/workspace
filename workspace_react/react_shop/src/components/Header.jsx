import React from "react";
import { Link, NavLink } from "react-router-dom";
import styles from './Header.module.css'

const Header = () => {
  return (
    <>
      <div className={styles.header}>
        <ul className={styles.menu}>
          <li>
            {/* 현재 선택된 link에 css 적용 기능 제공 */}
            <NavLink to={'/'} className={(data) => {return data.isActive ? styles.active : ''}}>상품정보</NavLink>
          </li>
          <li>
            <NavLink to={'/insert'} className={(data) => {return data.isActive ? styles.active : ''}}>상품등록</NavLink>
          </li>
          <li>
            <NavLink to={'/order'} className={(data) => {return data.isActive ? styles.active : ''}}>주문하기</NavLink>
          </li>
          <li>
            <NavLink to={'/order-list'} className={(data) => {return data.isActive ? styles.active : ''}}>주문목록</NavLink>
          </li>
        </ul>
      </div>
    </>
  );
};

export default Header;
