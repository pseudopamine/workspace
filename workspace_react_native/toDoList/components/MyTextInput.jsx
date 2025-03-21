import { StyleSheet, Text, TextInput, View } from 'react-native'
import React from 'react'

const MyTextInput = ({placeholder, ...props}) => {
  return (
    <View style={styles.container}>
      <TextInput 
        style={styles.input}
        placeholder={placeholder}
        {...props}
      />
    </View>
  )
}

export default MyTextInput

const styles = StyleSheet.create({
  container : {
    alignItems : 'center',
  },
  input : {
    borderWidth : 1,
    margin : 5,
    width : '96%',
  }
})