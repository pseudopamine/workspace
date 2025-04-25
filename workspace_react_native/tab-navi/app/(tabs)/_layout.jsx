import { StyleSheet, Text, View } from 'react-native'
import React from 'react'
import { Tabs } from 'expo-router'
import MaterialIcons from '@expo/vector-icons/MaterialIcons';

// app/(tabs)/_layout.jsx
const TabLayout = () => {
  return (
    <Tabs screenOptions={{headerShown:false}}>
      <Tabs.Screen 
        name='index' //탭 터치 시 보여줄 파일명
        options={{
          title:'Home',
          tabBarIcon : () => <MaterialIcons name="home" size={24} color="black" />
        }}
      />

      <Tabs.Screen 
        name='myPage'
        options={{
          title:'MyPage',
          tabBarIcon : () => <MaterialIcons name="person" size={24} color="black" />
        }}
      />

      <Tabs.Screen 
        name='settings'
        options={{
          title:'Setting',
          tabBarIcon : () => <MaterialIcons name="settings" size={24} color="black" />
        }}
      />
    </Tabs>
  )
}

export default TabLayout

const styles = StyleSheet.create({})