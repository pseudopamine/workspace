import { Keyboard, StatusBar, StyleSheet, Text, TouchableWithoutFeedback, View } from 'react-native'
import React from 'react'
import { SafeAreaView } from 'react-native'
import Login from '../components/Login'
import CartList from '../components/CartList'

const MainScreen = () => {
  return (
    //TouchableWithoutFeedback은 터치는 감지하되 아무 반응도 일어나지 않는 컴포넌트
    //주로 키보드를 감출 때 사용.
    //onPress={Keyboard.dismiss} 이 명령어가 키보드 숨김
    <TouchableWithoutFeedback onPress={Keyboard.dismiss}>
      <SafeAreaView style={styles.mainContainer}>
        <StatusBar 
          barStyle={'light-content'}
          backgroundColor={'#555555'}  //ios는 미적용
        />
  
        {/* <Login /> */}
        <CartList />
      </SafeAreaView>
    </TouchableWithoutFeedback>
  )
}

export default MainScreen

const styles = StyleSheet.create({
  mainContainer : {
    flex : 1,
  }
})