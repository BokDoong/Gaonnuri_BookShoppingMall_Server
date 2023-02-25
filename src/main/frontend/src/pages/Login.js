import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Axios } from '../utils/CustomAxios';
import { useDispatch } from 'react-redux';
import { login_success } from '../store/authReducer';
import axios from 'axios';
const Login = () =>{
    const [ID,setID] = useState("");
    const [PW,setPW] = useState("");
    const navigate = useNavigate();
    const dispatch = useDispatch();
    
    //로그인 버튼 클릭했을 때
    const loginBtnClick = () =>{
        //알림 출력
        if(!ID){
            alert("아이디를 입력하십시오");
            return;
        }
        if (!PW){
            alert("비밀번호를 입력하십시오");
            return;
        }
        
        //아이디 및 비밀번호 POST
        const data = {email:ID,password:PW};;
        Axios.post("/api/v1/auth/login",data)
        .then((res)=>{
            dispatch(login_success(res.data));
            localStorage.setItem("accessToken",res.data.accessToken);
            localStorage.setItem("refreshToken",res.data.refreshToken);
            if(res.data.authority[0].authorityStatus==="ROLE_ADMIN"){
                console.log("관리자");
                alert("관리자계정으로 로그인합니다");
                navigate("/admin");
            }
            else {
                navigate("/");
            }
        })
        .catch((err)=>{
            alert("비밀번호가 일치하지 않습니다");
            console.log(err);    
        })    
    }
    
    return (
        <div id="container">
            <div id="content">
                <form>
                    <div className="login">
                        <div id='login'>
                            <h3 id='login-title'>로그인</h3>
                            <fieldset>
                                <ul className='form'>
                                    <li>
                                        <label><input id="member-id" type="text" onChange={(e)=>setID(e.target.value)}placeholder='회원 이메일'/></label>
                                    </li>
                                    <li>
                                        <label><input id="member-pw" type="password" onChange={(e) =>setPW(e.target.value)}placeholder='비밀번호'/></label>
                                    </li>
                                </ul>
                                <ul className='btn_login'>
                                    <li>
                                        <div onClick={loginBtnClick}><a>로그인하기</a></div>
                                    </li>
                                </ul>
                                <div className='btn_register'><a href='/register'>회원가입</a></div>
                            </fieldset>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default Login;