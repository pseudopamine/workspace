import { createSlice } from "@reduxjs/toolkit";

const memberSlice = createSlice({
  name : 'member',
  initialState : {
    memId : 'hong',
    memAge : 20
  },
  //상태를 변경하는 함수
  reducers : {
    handleMemberId : (state, action) => {
      //return {...state, memId : 'java'}
      state.memId = action.payload
    }
  }
});

export const {handleMemberId} = memberSlice.actions;
export default memberSlice;