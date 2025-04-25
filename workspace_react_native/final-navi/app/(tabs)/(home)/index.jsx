import { FlatList, Pressable, StyleSheet, Text, View } from "react-native";
import React from "react";
import FeedItem from "../../../components/FeedItem";
import { dummyData } from "../../../apis/dummyData";
import { colors } from "../../../constants/colorConstant";
import MaterialCommunityIcons from '@expo/vector-icons/MaterialCommunityIcons';


const HomeScreen = () => {
  const data = dummyData;

  return (
    <View style={styles.container}>
      <FlatList 
        data={data}  // 반복할 데이터 
        renderItem={({item}) => <FeedItem item={item}/>}  // 반복 돌려서 data에서 하나씩 뺀 것을 item이라고 하겠다 (아이템마다 진행할 코드), item은 구조분해할당으로 전달
        keyExtractor={(item) => {item.id}}  // key
        contentContainerStyle={styles.listContainer} // 리스트 디자인
      />
      <Pressable style={styles.writeBtn}>
        <MaterialCommunityIcons name="pencil" size={24} color="white" />
      </Pressable>


    </View>
  );
};

export default HomeScreen;

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "white",
  },
  listContainer : {
    backgroundColor : colors.GRAY_200,
    gap : 10,
    paddingVertical : 8,
    paddingHorizontal : 4,
  },
  writeBtn : {
    position : 'absolute',
    width : 50,
    height : 50,
    backgroundColor : colors.ORANGE_600,
    borderRadius : 50,
    justifyContent : 'center',
    alignItems : 'center',
    bottom : 40,
    right : 30,

  },
});
