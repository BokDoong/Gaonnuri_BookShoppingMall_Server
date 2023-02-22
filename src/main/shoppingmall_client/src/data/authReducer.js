import { createSlice } from "@reduxjs/toolkit";

const initUser = {
  userId: -1,
  userEmail: "",
  role: "",
};

//초기 상태
const initialState = {
  isLoggedIn: true,
  info: initUser,
};

const authReducer = createSlice({
  name: "authReducer",
  initialState: initialState,
  reducers: {
    login_success: (state, action) => {
      state.isLoggedIn = true;
      state.info = action.payload.user;
      //state에 관련한 것만
      localStorage.setItem("accessToken",action.payload.accessToken);
      sessionStorage.setItem("refreshToken",action.payload.refreshToken); //쿠키나 세션스토리지로 이동
    },
    remove_userInfo: (state) => {
      state.isLoggedIn = false;
      state.info = initUser;
      localStorage.removeItem("accessToken");
      sessionStorage.removeItem("refreshToken");
    },
  },
});


export const { login_success, remove_userInfo } = authReducer.actions;
export {authReducer};


