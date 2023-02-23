import axios from "axios";

export const Axios = axios.create({
  baseURL: "http://localhost:8080",
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
    if (config.url === "http://localhost:8080/api/auth/v1/reissue" || status !== 500 || config.sent) {
      return Promise.reject(error);
  }

    config.sent = true;
    const accessToken = await getRefreshToken();

    if (accessToken) {
      config.headers.Authorization = "Bearer " + accessToken;
    }

    return Axios(config);
  }
);

const getRefreshToken = async() => {
  const refreshToken = localStorage.getItem("refreshToken");
  const accessToken = localStorage.getItem("accessToken");
  const data = {accessToken:accessToken,refreshToken:refreshToken}
  await Axios.post("/api/auth/v1/reissue",data)
    .then((res) => {
      console.log(res);
      const response = res.data;
      const newAccessToken = response.accessToken;
      localStorage.removeItem("accessToken");
      localStorage.setItem("accessToken", newAccessToken);
    })
    .catch((e) => {
      alert("로그아웃되었습니다");
      console.log("Token Reissue Fail : " + e);
      localStorage.removeItem('accessToken');
      localStorage.removeItem('refreshToken');
    });
}