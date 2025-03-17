import { Image, Pressable, StyleSheet, Text, TextInput, View } from 'react-native'
import React, { useEffect, useState } from 'react'
import { icon } from '../constants/icons'

const Task = ({e, cartList, setCartList}) => {
  //수정 상태 여부
  const [isEditing, setIsEditing] = useState(false);

  //수정을 위해서는 input 태그에 입력한 글자, id도 필요
  //전체 데이터도 필요 (부모컴포넌트의 cartList에 data가 담겨있으므로 props로 받아옴)

  //input태그에 입력한 데이터
  //주의!! props로 전달된 데이터를 state 변수의 초기값으로 주면 안됨!!!
  //const [newText, setNewText] = useState(e.item);
  const [newText, setNewText] = useState('');

  useEffect(() => {
    setNewText(e.item);
    //마운트 될 때에도 실행, 부모 컴포넌트의 e값이 변경될 때도 실행
  }, [e])

  //장바구니 목록 데이터 수정
  const handleCartList = () => {
    //상품 목록에서 현재 수정 중인 상품의 id 찾아서 
    //찾은 상품의 item 속성값을 input 태그에 입력한 글자로 바꿔준다.
    //----------------1번풀이----------------//
    // const copyCartList = [...cartList]
    // for(const cart of copyCartList){
    //   if(cart.id === e.id){
    //     cart.item = newText;
    //   }
    // }
    // //setCartList([...cartList]);
    // setCartList(copyCartList);
    //----------------1번풀이 끝!!----------------//

    //----------------2번풀이----------------//
    //find : 조건과 일치하는 데이터만 리턴한다.
    //filter : 조건과 일치하는 데이터만 필터링(제외)해서 리턴한다.
    const copyCartList = [...cartList]
    const findCart = copyCartList.find((cart) => {return cart.id === e.id})
    findCart.item = newText;
    setCartList(copyCartList)
    //----------------2번풀이 끝!!----------------//

  }

  /**
   * Task에서 new Text 값을 'java'로 변경
   * 이 상태에서 부모 컴포넌트인 cartList가 리렌더링 일어난다면,
   * (cartList data의 id가 3인 데이터의 item이 '안녕'으로 변경이 일어났다고 가정,)
   * 부모 컴포넌트가 리렌더링 일어나더라도 자식 컴포넌트의 state 변수값은 변경되지 않기 때문에 이전에 가진 최신값을 여전히 가지고있음
   * 
   * 
   */

  return (
    <View style={styles.container}>
      {
        isEditing
        ?
        <>
          <TextInput 
            style={styles.input} 
            autoFocus={true} 
            //focus out 이벤트
            onBlur={() => {
              setIsEditing(false);
              setNewText(e.item); //focus out 했다가 다시 수정 누르면 그 전에 입력하려했던 값이 나오던 것을 리셋
            }}
            onChangeText={text => setNewText(text)}
            value={newText}
            onSubmitEditing={() => {handleCartList()}}
          />
        </>
        :
        <>
          <Text style={styles.title}>{e.item}</Text>

          <Pressable onPress={() => {setIsEditing(true)}}>
            <Image source={icon.ICON_EDIT} style={styles.img}/>
          </Pressable>

          <Image source={icon.ICON_DELETE} style={styles.img}/>
        </>
      }

      
    </View>
  )
}

export default Task

const styles = StyleSheet.create({
  container : {
    borderWidth : 1,
    margin : 6,
    backgroundColor : '#FFF5E4',
    borderRadius : 5,
    flexDirection : 'row',
    alignItems : 'center',
    padding : 10,
    gap : 8,
  },
  title : {
    flex : 1,
    fontSize : 18
  },
  img : {
    width : 30,
    height : 30,
  },
  input : {
    borderWidth : 1,
    width : '100%'
  },
})