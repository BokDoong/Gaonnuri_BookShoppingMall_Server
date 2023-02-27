import axios from "axios";
import { useState } from "react";
import { useNavigate } from "react-router-dom";

const Reset = () => {
    const navigate = useNavigate();
    const [email,setEmail] = useState("");
    const [name, setName] = useState("");
    const [id,setId] = useState();
    const [PW,setPW] = useState("");
    const [PW2,setPW2] = useState("");
    const emailHandler = (e) => {
        setEmail(e.target.value);
    }
    const PWHandler = (e) => {
        setPW(e.target.value);
    }
    const onPW2Blur = (event) => {
        setPW2(event.target.value);
        if(event.target.value!==PW){
          document.querySelector(".pw_check_guide").classList.remove("hidden");
        }
        else {
          document.querySelector(".pw_check_guide").classList.add("hidden");
        }
      }
    const nameHandler = (e) => {
        setName(e.target.value);
    }
    const PWCheck = () => {
        let RegExp1 = /(?=.*?[a-z])(?=.*?[A-Z])/;
        let RegExp2 = /(?=.*?[0-9])(?=.*?[#?!@$%^&*-])/;
        let RegExp3 = /^.{8,18}$/;
        if(RegExp1.test(PW)&&RegExp2.test(PW)&&RegExp3.test(PW))return true;
        else return false;
      }
    const onResetClicked = () => {
        if(PWCheck&&PW===PW2){
            const uri = "api/v1/auth/reset";
            const data = {id:id, password:PW};
            axios.put(uri,data)
            .then(()=>{
                navigate("/login");
            })
            .catch((err)=>{
                alert("비밀번호를 재설정할 수 없습니다");
                console.log(err);
            });
        }
    }
    const onCheckClicked = ()=>{
        const uri = "/api/v1/auth/check";
        const data = {name:name,email:email};
        axios.post(uri,data)
        .then((res)=>{
            setId(res.data);
            const temp = document.querySelector(".reset_input");
            temp.classList.remove("hidden");
        })
        .catch((err)=>{
            alert("입력하신 정보와 일치하는 회원이 없습니다");
            console.log(err);
        })
    }
    return (
        <div id="container">
            <div id="content">
                <form>
                    <div className="login">
                        <div className='reset'>
                            <h3 id='login-title'>비밀번호 재설정</h3>
                            <div className="reset_notice">재설정할 계정의 이메일과 이름을 입력해주세요</div>
                            <fieldset>
                                <ul className='form'>
                                    <li>
                                        <label><input id="member-id" type="text" onChange={emailHandler}placeholder='회원 이메일'/></label>
                                    </li>
                                    <li>
                                        <label><input id="member-pw" type="text" onChange={nameHandler}placeholder='이름'/></label>
                                    </li>
                                </ul>
                                <ul className='btn_login'>
                                    <li>
                                        <div onClick={onCheckClicked}><a>확인</a></div>
                                    </li>
                                </ul>
                                <ul className='reset_input form hidden'>
                                    <li>
                                        <label><input id="member-id"onChange={PWHandler} type="password" placeholder='새 비밀번호'/></label>
                                    </li>
                                    <li>
                                        <label><input id="member-pw" onBlur={onPW2Blur}type="password"placeholder='비밀번호 확인'/></label>
                                        <span className="pw_check_guide hidden"tyle={{color:"blue",fontSize:"13px"}}>비밀번호가 일치하지 않습니다</span>  
                                        <p className='text_guide'>
                                    비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.
                                    </p>
                                    </li>
                                    <ul className='btn_login'>
                                        <li>
                                            <div onClick={onResetClicked}><a>재설정하기</a></div>
                                        </li>
                                    </ul>
                                </ul>
                                
                                
                            </fieldset>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default Reset;