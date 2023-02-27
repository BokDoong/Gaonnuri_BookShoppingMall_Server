import axios from "axios";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { remove_userInfo } from "../store/authReducer";
import {useCookies} from 'react-cookie';
import { GetCookies } from "../store/cookie";


export const refreshTokenAxios = axios.create({
  withCredentials: true,
});

refreshTokenAxios.interceptors.request.use((config) => {
  const token = localStorage.getItem("accessToken");
  if (config.headers !== undefined) {
    if (token !== null) {
      config.headers.Authorization = "Bearer " + token;
    }
  }
  return config;
});



export const formDataAxios = axios.create({
  withCredentials: true,
});

formDataAxios.interceptors.request.use((config) => {
  const token = localStorage.getItem("accessToken");
  if (config.headers !== undefined) {
    if (token !== null) {
      config.headers.Authorization = "Bearer " + token;
    }
  }
  return config;
});

formDataAxios.interceptors.response.use(
   (response) => response,
   async (error) => {
    const { config, response: { status } } = error;

    //1.refresh 요청자체의 에러인 경우
    //2.500 에러(토큰만료)가 아닌 경우
    //3. refresh하고 다시 요청을 보냈는데 에러인 경우
    if (config.url === "/api/auth/v1/reissue" || (status !== 500&&error.response.data.msg!=="인증이 실패하였습니다." ) || config.sent) {
      return Promise.reject(error);
  }

    config.sent = true;
    const accessToken = await getRefreshToken();

    if (accessToken) {
      config.headers.Authorization = "Bearer " + accessToken;
    }
    console.log("return request!!");
    return Axios(config);
  }
);


export const Axios = axios.create({
  //baseURL: "http://localhost:8080",
  headers: {
    "Content-Type": "application/json",
  },
  withCredentials: true,
});

Axios.interceptors.request.use((config) => {
  const token = localStorage.getItem("accessToken");
  if (config.headers !== undefined) {
    if (token !== null) {
      config.headers.Authorization = "Bearer " + token;
    }
  }
  return config;
});

Axios.interceptors.response.use(
   (response) => response,
   async (error) => {
    const { config, response: { status } } = error;

    //1.refresh 요청자체의 에러인 경우
    //2.500 에러(토큰만료)가 아닌 경우
    //3. refresh하고 다시 요청을 보냈는데 에러인 경우
    if (config.url === "/api/auth/v1/reissue" || (status !== 500&&error.response.data.msg!=="인증이 실패하였습니다." ) || config.sent) {
      return Promise.reject(error);
  }
    config.sent = true;
    const accessToken = await getRefreshToken();
console.log(accessToken);
    if (accessToken) {
      config.headers.Authorization = "Bearer " + accessToken;
    }
    console.log("return request!!");
    return Axios(config);
  }
);


const getRefreshToken = async() => {
  const refreshToken = GetCookies();
  const accessToken = localStorage.getItem("accessToken");
  const data = {"refreshToken":refreshToken,"accessToken":accessToken};
  await refreshTokenAxios.post("/api/v1/auth/reissue",data)
    .then((res) => {
      const response = res.data;
      const newAccessToken = response.accessToken;
      localStorage.removeItem("accessToken");
      localStorage.setItem("accessToken", newAccessToken);
    })
    .catch((e) => {
      const dispatch = useDispatch();
      const navigate = useNavigate();
      console.log("Token Reissue Fail : " + e);
      localStorage.removeItem('accessToken');
      dispatch(remove_userInfo());
      navigate("/");
    });
}