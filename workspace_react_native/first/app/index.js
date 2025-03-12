
import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { Pressable } from 'react-native'
import Style2 from '../components/Style2'
import Style1 from '../components/Style1'

//rnfes : react native function export stylesheet
//View -> div
//Text -> p  *모든 글자는 반드시 Text 컴포넌트 안에서 작업
//button과 같이 터치 이벤트가 필요한 컴포넌트는 <Pressable> 컴포넌트를 사용한다. (iOS, 안드로이드 서로 버튼 모양 달라서) 
const MainScreen = () => {
  return (
    <Style2/>
  )
}

export default MainScreen

const styles = StyleSheet.create({})