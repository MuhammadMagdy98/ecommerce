// src/slices/userSlice.js
import { createSlice } from '@reduxjs/toolkit';
import { UserDetails } from '@/types/user';


interface UserState {
    isAuthenticated: boolean;
    userDetails: UserDetails | null;
  }

const initialState: UserState = {
  isAuthenticated: false,
  userDetails:  null
};

const userSlice = createSlice({
  name: 'user',
  initialState,
  reducers: {
    login: (state, action) => {
      console.log("actions ya magdy", action.payload);
      state.isAuthenticated = true;
      state.userDetails = action.payload;
    },
    logout: (state) => {
      state.isAuthenticated = false;
      state.userDetails = null;
    },
  },
});

export const { login, logout } = userSlice.actions;
export default userSlice.reducer;
