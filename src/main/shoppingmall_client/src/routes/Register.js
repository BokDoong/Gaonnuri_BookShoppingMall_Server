import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Axios } from '../CustomAxios';

const Register = () =>{
  const [PW,setPW] = useState("");
  const [PW2,setPW2] = useState("");
  const [name,setName] = useState("");
  const [PN1,setPN1] = useState("");
  const [PN2,setPN2] = useState("");
  const [PN3,setPN3] = useState("");
  const [Email,setEmail] = useState("");
  const [address,setAddress] = useState("");

  const navigate = useNavigate();
  
  //이메일 형식 확인 후 경고문 표시
  const onEmailBlur = (event) => {
    const RegExp = /^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*\.[A-Za-z]{2,3}$/;
    if(RegExp.test(event.target.value)){
      document.querySelector(".id_guide").classList.add("hidden");
      setEmail(event.target.value);
    }
    else{
        document.querySelector(".id_guide").classList.remove("hidden");
        setEmail("");
    }
  }

  // 이메일 형식 체크
  const EmailCheck = () => {
    const RegExp = /^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*\.[A-Za-z]{2,3}$/;
    if(RegExp.test(Email)) return true;
    else return false;
  }
  
  //비밀번호 형식 체크
  const PWCheck = () => {
    let RegExp =  /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d@$!%*#?&]{10,}$/;
    if(RegExp.test(PW))return true;
    else return false;
  }

  //비밀번호 일치 여부 확인 후 경고문 표시
  const onPW2Blur = (event) => {
    setPW2(event.target.value);
    if(event.target.value!==PW){
      document.querySelector(".pw_check_guide").classList.remove("hidden");
    }
    else {
      document.querySelector(".pw_check_guide").classList.add("hidden");
    }
  }
  
  //이름 형식 체크
  const nameCheck = () => {
    const RegExp = /^[가-희]{1,100}$/;
    if(RegExp.test(name))return true;
    else return false;
  }

  //전화번호 형식 체크
  const PNCheck = () =>{
    if(PN1===""&&PN2===""&&PN3===""){
      return true;
    }
    const RegExp1 = /^[0-9]{3}$/;
    const RegExp2 = /^[0-9]{3,4}$/;
    const RegExp3 = /^[0-9]{4}$/;
    if(RegExp1.test(PN1)&&RegExp2.test(PN2)&&RegExp3.test(PN3)){
      return true;
    }
    else return false
  }

  //주소
  const addressCheck = () =>{
    if(address==="")return true;
    const RegExp = /^[가-희]{1,}$/;
    if(RegExp.test(address))return true;
    else return false;
  }

  //입력된 정보  POST
  const requestReg = (data) =>{
    const uri = "/api/v1/auth/join";
    Axios.post(uri, data).then(response => response.data)
    .then((res) => {
      console.log(res);
      alert("가입되었습니다");
      navigate("/");
    })
    .catch((err)=>{
     console.log(err.response);
     alert("회원가입에 실패했습니다");
    });
  }
  //회원가입 클릭
  const onRegClick = () => {
    if(!EmailCheck())alert("이메일을 확인해주십시오");
    if(!PWCheck())alert("비밀번호를 확인해주십시오");
    if(PW!==PW2)alert("비밀번호가 일치하지 않습니다");
    if(!nameCheck())alert("이름을 확인해주십시오");
    if(!PNCheck())alert("휴대전화번호를 확인해주십시오");
    //if(!addressCheck())alert("주소를 확인해주십시오");
    if(EmailCheck()&&PWCheck()&&(PW===PW2)&&nameCheck()&&PNCheck){
      const data = {address:address,email:Email,name:name,password:PW,phone_number:`${PN1}-${PN2}-${PN3}`};
      requestReg(data);
    }
  }

    return (
        <div id="container">
            <div id="reg_contents">
                <div className="titleArea"><h2>회원 가입</h2></div>
                <form>
                    <div id='joinForm'>
                        <table className="tg">
                            <colgroup>
                            <col style={{width:"180px"}}/>
                            <col style={{width:"auto"}}/>
                            </colgroup>
                            <tbody>
                                <tr>
                                  <th className="tg-0lax">* 이메일</th>
                                  <td className="tg-0lax">
                                    <input className="ID_input"onChange={(e)=>setEmail(e.target.value)} onBlur={onEmailBlur} type="text"/>
                                    <p className='text_guide'>
                                    <span className='hidden id_guide'>이메일을 정확하게 입력해 주세요.</span>
                                    </p>
                                  </td>
                                </tr>
                                <tr>
                                  <th className="tg-0lax">* 비밀번호</th>
                                  <td className="tg-0lax">
                                    <input className="PW_input"onChange={(e) =>setPW(e.target.value)} type="password"/>
                                    <p className='text_guide'>
                                      특수문자 포함 10자리 이상
                                    </p>
                                  </td>
                                </tr>
                                <tr>
                                  <th className="tg-0lax">* 비밀번호 확인</th>
                                  <td className="tg-0lax">
                                    <input className="PW_input" onBlur={onPW2Blur}type="password"/>
                                    <p className='text_guide'>
                                    <span className='hidden pw_check_guide'>비밀번호가 일치하지 않습니다</span>  
                                    </p>
                                  </td>
                                </tr>
                                <tr>
                                  <th className="tg-0lax">* 이름</th>
                                  <td className="tg-0lax"><input  onChange={(e)=>setName(e.target.value)} className='name_input'type="text"/></td>
                                </tr>
                                <tr>
                                  <th className="tg-0lax">휴대전화</th>
                                  <td className="tg-0lax">
                                    <input className="phone_input first_num" maxLength="3" onChange={(e) =>setPN1(e.target.value)} type="text"/>-
                                    <input style={{marginLeft:"10px"}} className="phone_input second_num" maxLength="4" onChange={(e) =>setPN2(e.target.value)} type="text"/>-
                                    <input style={{marginLeft:"10px"}} className="phone_input third_num" maxLength="4"onChange={(e) =>setPN3(e.target.value)} type="text"/>
                                  </td>
                                </tr>
                                <tr>
                                  <th className="tg-0lax">주소</th>
                                  <td className="tg-0lax"><input className="address_input" onChange={(e)=>setAddress(e.target.value)} type="text"/></td>
                                </tr>
                                
                          </tbody>
                        </table>
                        <div className='reg_btn' onClick={onRegClick}>
                          <a className='btn_text'>가입하기</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default Register;