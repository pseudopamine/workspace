import { Pressable, StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { COLOR } from '../constants/colors'

const MyButton = ({size = 'normal', onPress, title = '버튼'}) => {
  return (
    // <Pressable style={[styles.btnContainer, styles[size]]}>
    <Pressable 
      style={(e) => {
        console.log(e);

        //화살표 함수 만들어서 return값으로도 가져올 수 있음
        //단락평가 e.pressed가 false면 뒤에 해석
        return [styles.btnContainer, styles[size], e.pressed && styles.pressed];
      }}
    onPress={() => {onPress()}}
    >
      <Text style={styles.btn}>{title}</Text>
    </Pressable>
  )
}

export default MyButton

const styles = StyleSheet.create({
  btnContainer : {
    height : 34,
    borderRadius : 6,
    backgroundColor : COLOR.BTN_BACKGROUND_COLOR,
    justifyContent : 'center',
    alignItems : 'center',
    paddingVertical : 2,   //상하패딩
    paddingHorizontal : 4   //좌우패딩
  },
  btn : {
    color : 'white',
  },
  normal : {
    width : '30%'
  },
  large : {
    width : '100%'
  },
  pressed : {
    opacity : 0.8
  },
})