import { SafeAreaView, StyleSheet, Text, View } from 'react-native'
import React from 'react'
import ToDoList from '../components/ToDoList'

const MainScreen = () => {
  return (
    <SafeAreaView>

      <ToDoList />
    </SafeAreaView>
  )
}

export default MainScreen

const styles = StyleSheet.create({})