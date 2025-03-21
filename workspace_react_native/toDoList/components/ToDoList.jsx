import { Image, Pressable, SafeAreaView, StyleSheet, Text, TextInput, View } from 'react-native'
import React, { useState } from 'react'
import MyTextInput from './MyTextInput'
import { icon } from '../constants/icon'
import { data } from '../data/todoList'
import Task from './Task'

const ToDoList = () => {
  //할 일 목록을 저장하는 state 변수
  const [doIt, setDoIt] = useState(data);

  //input 태그로 받은 데이터를 저장할 변수
  const [newToDo, setNewToDo] = useState('');

  //키보드 완료 버튼 눌렀을 때 데이터 입력 적용되는 함수
  const submitToDo = () => {
    const max = Math.max(...doIt.map(object => object.id));
    const newData = {
      id : max + 1,
      text : newToDo
    };
    setDoIt([...doIt, newData]);
    setNewToDo('')
  }

  return (
    <>
      <View style={styles.container}>
      <View>
        <Text style={styles.text}>
          To Do List
        </Text>
      </View>
      
      <MyTextInput
        placeholder={'+ Add a Task'}
        value={newToDo}
        onChangeText={text => setNewToDo(text)}
        onSubmitEditing={() => {submitToDo()}}
      />
      
      {
        doIt.map((e, i) => {
          return(
            <Task key={i} e={e} doIt={doIt} setDoIt={setDoIt} />
          )
        })
      }

      </View>
    </>
  )
}

export default ToDoList

const styles = StyleSheet.create({
  container : {
    backgroundColor : '#FFE3E1',
    padding : 10,
    margin : 20,
    borderRadius : 4
  },
  text : {
    fontSize : 50,
    fontWeight : 800,
    padding : 10,
    textAlign : 'center'
  },
  
})