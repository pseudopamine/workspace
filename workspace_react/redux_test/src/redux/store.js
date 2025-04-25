import { configureStore } from "@reduxjs/toolkit";
import counterSlice from "./counterSlice";
import nameSlice from "./nameSlice";
import memberSlice from "./memberSlice";
import cartListSlice from "./cartListSlice";

//slice를 관리할 공용 저장소
export const store = configureStore({
  reducer : {
    counter : counterSlice.reducer,
    myName : nameSlice.reducer,
    member : memberSlice.reducer,
    cartList : cartListSlice.reducer
  }
});