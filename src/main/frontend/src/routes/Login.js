import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Axios } from '../CustomAxios';
import { useDispatch } from 'react-redux';
import { login_success } from '../data/authReducer';
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
            alert("로그인 되었습니다.");
            navigate("/");
        })
        .catch((err)=>{
            alert("로그인에 실패했습니다");
            console.log(err.response);    
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