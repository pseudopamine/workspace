import { StyleSheet, Text, View } from "react-native";
import React, { useState } from "react";
import CustomInput from "../../components/common/CustomInput";
import CustomButton from "../../components/common/CustomButton";
import { api_login } from "../../apis/memberApis";
import * as SecureStore from "expo-secure-store";
import { useRouter } from "expo-router";
import { useDispatch } from "react-redux";
import { loginReducer } from "../../redux/authSlice";

const LoginScreen = () => {
  const router = useRouter();
  const dispatch = useDispatch();

  const [loginData, setLoginData] = useState({
    memEmail: "",
    memPw: "",
  });

  const handleLoginData = (text, name) => {
    setLoginData({
      ...loginData,
      [name]: text,
    });
  };

  const login = () => {
    api_login(loginData)
      .then((res) => {
        const token = res.headers.authorization;

        SecureStore.setItemAsync("accessToken", token)
          .then(() => {
            dispatch(loginReducer(token));
            router.navigate("/");
          })
          .catch((e) => console.log(e));
      })
      .catch((e) => console.log(e));
  };

  return (
    <View style={styles.container}>
      <View style={styles.inputArea}>
        <CustomInput
          label={"아이디"}
          value={loginData.memEmail}
          onChangeText={(text) => handleLoginData(text, "memEmail")}
        />
      </View>
      <View style={styles.inputArea}>
        <CustomInput
          label={"비밀번호"}
          isPw={true}
          value={loginData.memPw}
          onChangeText={(text) => handleLoginData(text, "memPw")}
        />
      </View>
      <View style={styles.inputArea}>
        <CustomButton label="로그인" onPress={() => login()} />
      </View>
    </View>
  );
};

export default LoginScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
  },
  inputArea: {
    marginBottom: 14,
  },
});
