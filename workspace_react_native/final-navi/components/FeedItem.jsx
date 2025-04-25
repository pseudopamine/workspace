import { Pressable, StyleSheet, Text, View } from "react-native";
import React, { useState } from "react";
import Entypo from "@expo/vector-icons/Entypo";
import Octicons from "@expo/vector-icons/Octicons";
import Feather from "@expo/vector-icons/Feather";
import { colors } from "../constants/colorConstant";
import Profile from '@/components/Profile';

const FeedItem = ({item}) => {
  const [isLike, setIsLike] = useState(false);

  const touch = () => {
    if(!isLike){
      setIsLike(true)
    }
    
  }

  return (
    <View style={styles.feedContainer}>
      <Profile writer={item.writer} createDate={item.createDate}/>
      <Text style={styles.title}>{item.title}</Text>
      <Text style={styles.content} numberOfLines={2}>
        {item.content}
      </Text>
      <View style={styles.menuContainer}>
          <Pressable style={styles.menu} onPress={() => {touch()}}>
          <Entypo name={isLike ? 'heart' : 'heart-outlined'} size={20} color={isLike ? 'red' : 'black'} />
          <Text style={isLike && {color : 'red'}}>{item.likeCnt}</Text>
        </Pressable>
        <Pressable style={styles.menu}>
          <Octicons name="comment" size={20} color="black" />
          <Text>{item.replyCnt}</Text>
        </Pressable>
        <Pressable style={styles.menu}>
          <Feather name="eye" size={20} color="black" />
          <Text>{item.readCnt}</Text>
        </Pressable>
      </View>
    </View>
  );
};

export default FeedItem;

const styles = StyleSheet.create({
  feedContainer: {
    padding: 16,
    backgroundColor : "white",
  },
  menuContainer: {
    flexDirection: "row",
    justifyContent: "space-around",
    alignItems: "center",
    borderTopWidth: StyleSheet.hairlineWidth,
    borderTopColor: colors.GRAY_300,
  },
  menu: {
    flexDirection: "row",
    justifyContent: "center",
    alignItems: "center",
    paddingVertical: 16,
    gap: 5,
    width: "33.3%",
  },
  title: {
    fontSize: 20,
    marginBottom: 12,
  },
  content: {
    fontSize: 14,
    color: colors.GRAY_600,
    marginBottom: 14,
  },
});
