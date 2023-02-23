import { combineReducers, configureStore } from "@reduxjs/toolkit";
import persistReducer from "redux-persist/es/persistReducer";
import { authReducer } from "./authReducer";
import storage from "redux-persist/lib/storage";

const reducers = combineReducers({
  user : authReducer.reducer
})

const persistConfig = {
  key:'root', 
  storage,
  whitelist: ['user']
}

const persistedReducer = persistReducer(persistConfig,reducers);

const store = configureStore({
  reducer:persistedReducer
})

export default store;