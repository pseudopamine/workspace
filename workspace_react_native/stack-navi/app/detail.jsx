import { Pressable, StyleSheet, Text, View } from "react-native";
import React from "react";
import { useLocalSearchParams, useRouter } from "expo-router";

const DetailScreen = () => {
  const router = useRouter();

  // router 이동 시 전달되는 데이터 받기 (구조분해할당)
  const {id, age} = useLocalSearchParams();

  return (
    <View>
      <Text>디테일 스크린입니다. {id} / {age} </Text>

      <Pressable onPress={() => router.push("/myPage")}>
        <Text>마이페이지로 이동</Text>
      </Pressable>

      <Pressable onPress={() => router.replace("/myPage")}>
        <Text>마이페이지로 이동 replace</Text>
      </Pressable>



    </View>
  );
};

export default DetailScreen;

const styles = StyleSheet.create({});
