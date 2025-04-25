import * as SecureStore from 'expo-secure-store';
import { jwtDecode } from 'jwt-decode';
import { loginReducer, logoutReducer } from './authSlice';


/*
  // falsy : null, undefined, 0, 빈문자, NaN
  // 기존 방식 (OR 연산자 사용) - 왼쪽 데이터가 falsy일 때 오른쪽 데이터 실행
  const username = user.name || 'Guest'

  // Nullish 병합 연산자 사용 - 왼쪽 데이터가 null 또는 undefined일 때만 오른쪽 데이터 실행
  const username = user.name ?? 'Guest'

*/

//회원의 아이디를 토큰의 subject에 저장하는데,
//토큰에서 로그인한 회원 email 추출
//토큰에서 email 못 빼면 리턴 null, 있으면 email 반환
export const getUserSubFromToken = (token) => {
  if (!token) return null;

  // 예외 처리 :  try 안에 있는 구문을 실행한다 
  // -> 오류가 없으면 catch를 실행하지 않는다
  // -> 오류가 생기면 try 실행을 멈추고 catch를 실행
  try {
    const decoded = jwtDecode(token);  //암호화 된 토큰을 추출 (payload만 해석, decoded는 객체형태가 됨)

    /*
    const result = '';
    //if(decoded !== null && decoded !== undefined){
    if(decoded){  //truthy falsy 문법
      result = decoded.name
    }
    else{
      result = null;
    }
    */

    // ? : 옵셔널 체이닝 연산자 - JS 문법. 객체에서 사용
    //decode.name : 만약 decoded가 null 혹은 undefined면 name 추출 불가 (오류)
    //decoded가 null 또는 undefined면 -> undefined
    //decoded가 null 또는 undefined 아니라면 -> sub을 추출
    //decoded가 null 또는 undefined면 undefined이기 떄문에 -> 리턴 null (단락평가)
    return decoded?.sub || null;  

  } catch (error) {
    console.log('jwtDecode 실패:', error);
    return null;
  }
};

export const getUserRoleFromToken = (token) => {
  if (!token) return null;

  try {
    const decoded = jwtDecode(token);
    return decoded?.role || null;
  } catch (error) {
    console.log('jwtDecode 실패:', error);
    return null;
  }
};