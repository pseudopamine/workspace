import { createSlice } from "@reduxjs/toolkit";

const nameSlice = createSlice({
  name : 'name',
  initialState : 'hong',
  reducers : {
    changeName : (state, action) => {
      return state + action.payload
    }
  }
})

export default nameSlice;