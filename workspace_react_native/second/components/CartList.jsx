import { Image, Pressable, SafeAreaView, StyleSheet, Text, View } from 'react-native'
import React, { useState } from 'react'
import icon_edit from '@/assets/icons/edit.png'
import icon_delete from '@/assets/icons/delete.png'  //..대신 @을 사용할 수 있다 : 최상위 폴더를 찾는 문법
import { icon } from '../constants/icons'
import { data } from '../data/data'
import { TextInput } from 'react-native'
import Task from './Task'
//이미지가 다수일 경우 함수로 만들 수 있음

const CartList = () => {
  //각 상품이 수정 상태인지, 수정 상태가 아닌지 판단하는 변수
  // -> map으로 반복 돌리면 특정하기 어려우므로 컴포넌트로 따로 빼서 작성

  // console.log(Math.max(1, 5, 3))
  //장바구니 목록을 저장하는 state 변수
  const [cartList, setCartList] = useState(data);
  //iput태그에 새로 입력받은 데이터를 저장
  const [newItem, setNewItem] = useState('');

  //JS에서 반복문 돌릴 때 : for문, for each 함수, cartList.forEach((e, i) => {});, : return 없음
  //                       cartList.map((e, i) => {}) : map은 return할 수 있음


  const submitCartList = () => {
    //max id + 1 구하기
    // let max = cartList[0].id;
    // for(const e of cartList){
    //   if(e.id > max){
    //     max = e.id
    //   }
    // }
    //
    let max = Math.max(...cartList.map((e, i) => e.id))
      //저장할 객체 생성
    const newData = {
      id : max + 1, 
      item : newItem
    };
    setCartList([...cartList, newData])
    //cartList에 저장 : setCartList([...cartList, newData])
  }
  return (
    <View>
      <Text>CartList</Text>
      {/* <Image source={icon_edit} />
      <Image source={icon_delete}/>

      <Image source={icon.ICON_EDIT}/> */}

      <SafeAreaView style={styles.background}>
        {
          cartList.map((e, i) => {
            return(
              <Task key={i} e={e} cartList={cartList} setCartList={setCartList}/>
            )
          })
        }
        <View>
          <TextInput 
            style={styles.input}
            value={newItem}
            onChangeText={text => setNewItem(text)}
            //키보드의 완료, enter키를 눌렀을 때 실행하는 이벤트
            onSubmitEditing={() => {submitCartList()}}
            
          />
        </View>
      </SafeAreaView>
      
    </View>
  )
}

export default CartList

const styles = StyleSheet.create({

  background : {
    borderWidth : 1,
    backgroundColor : '#FFE3E1',
    padding : 6,
  },
  input : {
    borderWidth : 1,

  },
})