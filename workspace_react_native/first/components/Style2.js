import { Pressable, SafeAreaView, StyleSheet, Text, TextInput, View } from 'react-native'
import React, { useState } from 'react'

const Style2 = () => {
  const [id, setId] = useState('');
  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.content}>
        <View>
          <Text style={styles.t1}>아이디</Text>
          <TextInput 
          style={styles.input}
          // onChangeText={(text) => {setId(text)}}
          onChange={(e) => {
            console.log(e.nativeEvent.text)
          }}
          />
        </View>

        <View>
          <Text style={styles.t1}>패스워드</Text>
          <TextInput style={styles.input}/>
        </View>
        
        <View style={styles.btnView}>
          <Pressable 
          style={styles.btnContainer}
          onPress={() => {
          console.log('클릭')
        }}>
          <Text style={styles.btn}>로그인</Text>
        </Pressable>
        </View>
      </View>
    </SafeAreaView>
  )
}

export default Style2

const styles = StyleSheet.create({
  container : {
    flex : 1,
    justifyContent : 'center',
    padding : 20,
  },
  content : {
    gap : 20
  },
  input : {
    borderWidth : 1,
    borderRadius : 6,
    padding : 10, 
    width : '100%',
    height : 40
    
  },
  btnContainer : {
    width : '50%',
    height : 34,
    borderRadius : 6,
    backgroundColor : 'skyblue',
    justifyContent : 'center',
    alignItems : 'center'
  },
  btn : {
    color : 'white'
  },
  btnView : {
    alignItems : 'center'
  }
  
})