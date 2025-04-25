import React, { useState } from 'react'
import styles from './Join.module.css'

const Join = () => {

  const [formData, setFormData] = useState({
    id: '',
    password: '',
    email: '',
    name: '',
    address: '',
    phone: '',
    agree: false,
  });

  const [errors, setErrors] = useState({});

  const handleChange = (field) => (e) => {
    const value = field === 'agree' ? e.target.checked : e.target.value;
    setFormData({ ...formData, [field]: value });

    // 에러 해제
    if (errors[field]) {
      setErrors({ ...errors, [field]: '' });
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const newErrors = {};
    if (!formData.id.trim()) newErrors.id = '아이디: 필수 정보입니다.';
    if (!formData.password.trim()) newErrors.password = '비밀번호: 필수 정보입니다.';
    if (!formData.name.trim()) newErrors.name = '이름: 필수 정보입니다.';
    if (!formData.phone.trim()) newErrors.phone = '휴대전화번호: 필수 정보입니다.';

    if (Object.keys(newErrors).length > 0) {
      setErrors(newErrors);
      return;
    }

    // 회원가입 로직 실행
    alert('회원가입이 완료되었습니다!');
  };


  return (
    <div>
      <div className={styles.container}>
      <h1 className={styles.logo}>FarmsEye</h1>
      <form className={styles.form} onSubmit={handleSubmit}>
        {/* 아이디 / 비밀번호 / 이메일 */}
        <div className={styles.inputGroup}>
          <div className={`${styles.inputLine} ${errors.id && styles.error}`}>
            <input
              type="text"
              placeholder="아이디"
              value={formData.id}
              onChange={handleChange('id')}
            />
            {/* <span className={styles.suffix}>@gmail.com</span> */}
          </div>
          {errors.id && <p className={styles.errorMsg}>{errors.id}</p>}

          <div className={`${styles.inputLine} ${errors.password && styles.error}`}>
            <input
              type="password"
              placeholder="비밀번호"
              value={formData.password}
              onChange={handleChange('password')}
            />
          </div>
          {errors.password && <p className={styles.errorMsg}>{errors.password}</p>}

          <div className={styles.inputLine}>
            <input
              type="email"
              placeholder="이메일주소 (비밀번호 찾기 등 본인 확인용)"
              value={formData.email}
              onChange={handleChange('email')}
            />
          </div>
        </div>

        {/* 이름 / 주소 / 전화번호 */}
        <div className={styles.inputGroup}>
          <div className={`${styles.inputLine} ${errors.name && styles.error}`}>
            <input
              type="text"
              placeholder="이름"
              value={formData.name}
              onChange={handleChange('name')}
            />
          </div>
          {errors.name && <p className={styles.errorMsg}>{errors.name}</p>}

          <div className={styles.inputLine}>
            <input
              type="text"
              placeholder="주소"
              value={formData.address}
              onChange={handleChange('address')}
            />
          </div>

          <div className={`${styles.inputLine} ${errors.phone && styles.error}`}>
            <input
              type="tel"
              placeholder="휴대전화번호"
              value={formData.phone}
              onChange={handleChange('phone')}
            />
          </div>
          {errors.phone && <p className={styles.errorMsg}>{errors.phone}</p>}
        </div>

        <button type="submit" className={styles.submitButton}>
          인증요청
        </button>
      </form>
    </div>
    </div>
  )
}

export default Join