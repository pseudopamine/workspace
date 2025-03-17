import { StyleSheet, Text, TextInput, View } from 'react-native'
import React, { useState } from 'react'
import MyTextInput from './MyTextInput'
import MyButton from './MyButton'

const Login = () => {
  const [data, setData] = useState({
    id : '',
    pw : ''
  });

  //e.target.value 없음
  const changeData = (name, text) => {
    setData({
      ...data,
      [name] : text
    })
  }

  console.log(data)

  const printHello = () => {
    console.log('안녕하세요')
  }
  return (
    <View>
      <Text>Login</Text>
      {/* MyTextInput 컴포넌트로 만든 건 props로 넘겨주고 컴포넌트에서 받아야 됨 */}
      <MyTextInput placeholder={'Enter your ID'} value={data.id} onChangeText={(text) => {changeData('id', text)}}/>
      <MyTextInput placeholder={'Enter your PW'} value={data.pw} onChangeText={(text) => {changeData('pw', text)}}/>

      {/* alert으로 안녕하세요 */}
      <MyButton onPress={() => {alert('안녕하세요')}}/>

      {/* console에 안녕하세요 */}
      <MyButton size={'wide'} onPress={() => {printHello()}}/>

      <MyButton title='데이터확인' onPress={() => {alert(`ID = ${data.id}, PW = ${data.pw} `)}}/>

      
    </View>
  )
}

export default Login

const styles = StyleSheet.create({})