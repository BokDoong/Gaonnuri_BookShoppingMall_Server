import {useCookies} from "react-cookie";

export const GetCookies=() =>{
    const [cookies, setCookie, removeCookie] = useCookies();
    return cookies.refreshToken;
}