import { Image, StyleSheet, Text, View } from 'react-native'
import React from 'react'
import face from '@/assets/images/face-01.jpg'
import FontAwesome6 from '@expo/vector-icons/FontAwesome6';
import { colors } from '../constants/colorConstant';

const Profile = ({writer, createDate}) => {
  return (
    <View style={styles.container}>
      <View style={styles.profileContainer}>
        <Image
          style={styles.img}
          source={face}
        />
        <View style={{gap : 4}}>
          <Text style={styles.writer}>{writer}</Text>
          <Text style={styles.createDate}>{createDate}</Text>
        </View>
      </View>
      <View style={{paddingRight : 20}}>
        <FontAwesome6 name="ellipsis" size={24} color="black" />
        <Text></Text>
      </View>
    </View>
  )
}

export default Profile

const styles = StyleSheet.create({
  container : {
    flexDirection : 'row',
    alignItems : 'center',
    justifyContent : 'space-between',
    marginBottom : 10,
  },
  img : {
    width : 50,
    height : 50,
    borderWidth : StyleSheet.hairlineWidth,
    borderColor : colors.GRAY_300,
    borderRadius : 50
  },
  profileContainer : {
    flexDirection : 'row',
    alignItems : 'center',
    gap : 8,

  },
  writer : {
    fontSize : 14,
    fontWeight : 'bold',
  },
  createDate : {
    fontSize : 13,
    color : colors.GRAY_500,
  },
})