import { Image, Pressable, StyleSheet, Text, TextInput, View } from 'react-native'
import React, { useEffect, useState } from 'react'
import { icon } from '../constants/icon'

const Task = ({e, doIt, setDoIt}) => {
  //수정 여부
  const [isEditing, setIsEditing] = useState(false);

  //input 태그로 받은 데이터를 저장하는 state 변수
  const [newText, setNewText] = useState('');

  useEffect(() => {
    setNewText(e.text);
  }, [e])

  //할 일 목록 데이터 수정
  const handleToDoList = () => {
    const copyToDoList = [...doIt]
    const findToDo = copyToDoList.find((doo)=> {return doo.id === e.id})
    findToDo.text = newText;
    setIsEditing(false)
    setDoIt(copyToDoList);
    alert("수정 완료")
  }

  //할 일 목록 데이터 삭제
  const deleteToDoList = () => {
    const copyToDoList = [...doIt]
    const filterToDo = copyToDoList.filter((doo) => {return doo.id !== e.id})
    setDoIt(filterToDo)
    setIsEditing(true)
    alert("삭제 완료")
    console.log(filterToDo)
  }

  return (
  <>
    {
      isEditing
      ?
      <View style={styles.toDoBlock}>
        <TextInput 
          style={styles.input} 
          onChangeText={text => setNewText(text)}
          value={newText}
          onSubmitEditing={() => {handleToDoList()}}
        />
      </View>
      :
        <>
        <View style={styles.toDoBlock}>
          <Text style={styles.toDoText}>{e.text}</Text>
          <Pressable onPress={() => {setIsEditing(true)}}>
            <Image source={icon.EDIT_ICON} style={styles.img}/>
          </Pressable>
          <Pressable onPress={() => {deleteToDoList()}}>
            <Image source={icon.DELETE_ICON} style={styles.img}/>
          </Pressable>
        </View>
        </>
    }
  </>
  )
}

export default Task

const styles = StyleSheet.create({
  
  toDoText : {
    flex : 1,
    fontSize : 20,
  },
  img : {
    width : 30,
    height : 30,
  },
  input : {
    borderWidth : 1,
    margin : 5,
    width : '90%',
  },
  toDoBlock : {
    backgroundColor : '#FFF5E4',
    flexDirection : 'row',
    margin : 6,
    borderRadius : 6,
    alignItems : 'center',
    padding : 10,
    gap : 8
  },
})