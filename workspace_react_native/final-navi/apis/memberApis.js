import axios from "axios"
import { Platform } from "react-native";
import { axiosInstance } from "./axiosInstance";

export const api_join = (joinData) => {
  const response = axiosInstance.post(`/users/join`, joinData)
  return response;
}

export const api_login = (loginData) => {
  const response = axiosInstance.post(`/member/login`, loginData)
  return response;
}