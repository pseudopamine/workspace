import axios from "axios";
import * as SecureStore from 'expo-secure-store';
import { Platform } from "react-native";

export const axiosInstance = axios.create({
  baseURL : Platform.OS === 'ios' ? 'http://localhost:8080': 'http://10.0.2.2:8080'
});

//모든 요청 진행 전 토큰을 헤더에 담는 코드
axiosInstance.interceptors.request.use( 
  async (config) => {

    config.headers.clientType = 'app';

    const token = await SecureStore.getItemAsync('accessToken');

    //token이 있으면 요청 헤더에 토큰 담아서 서버로 보내기
    if(token){
      config.headers.Authorization = token;
    }
    return config;
  }
  , error => Promise.reject(error) 
);