import axios from "axios";
import { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import { Axios } from "../../CustomAxios";
import AdminMenu from "./AdminMenu";

const ProductReg = () =>{
    const [name,setName] = useState("");
    const [price,setPrice] = useState();
    const [detail,setDetail] = useState("");
    const [stock, setStock] = useState();
    const [status,setStatus] = useState("판매 중");
    const [image,setImage] = useState();
    const navigate = useNavigate();

    const priceCheck = () => {
        const check = /^[0-9]+$/;
        if(check.test(price)) return true;
        else return false;
    }
    const stockCheck = () => {
        const check = /^[0-9]+$/;
        if(check.test(stock)) return true;
        else return false;
    }
    const onImageHandler = (event) => {
        let formData = new FormData();
        console.log(event.target);
        formData.append("file",event.target.files[0]);
        for (var key of formData.keys()) {
            console.log(key);
          } 
          for (var value of formData.values()) {
            console.log(value);
          }
        setImage(formData);   
    }
    //등록버튼 클릭
    const onRegClick = async () => {
        if(!name||!detail||!price||!stock||!stockCheck()||!priceCheck()){
            alert("상품 정보를 정확하게 입력하세요");
            return;
        }
        
        //const uri = "/api/v1/admin/register";
        const uri = "http://localhost:3001/api_v1_admin_register";
        const data = {
            "itemName": name,
            "itemStatus": status,
            "itemDetail":detail,
            "price": price,
            "stockNumber": stock,
            "imageUrl":image
        }
        //await Axios.post(uri,data)
        await axios.post(uri,data)
        .then(()=>
            {navigate("/admin/product/1");}
        )
        .catch();
        
    }
    return (
        <>
            <AdminMenu menu={"product_manage"}/>
            <div style={{float:"right",width:"75%",marginBottom:"120px",marginTop:"50px"}}>
                <h2>상품등록</h2>
                <div style={{margin:"auto"}}>
                    <table className="product_register_table">
                        <tr>
                            <td>상품명</td>
                            <td><input onChange={(e)=>setName(e.target.value)} type="text"/></td>
                        </tr>
                        <tr>
                            <td>가격</td>
                            <td><input onChange={(e)=>setPrice(e.target.value)} type="text"/></td>
                        </tr>
                        <tr>
                            <td>세부사항</td>
                            <td><textarea style={{width:"500px",height:"350px"}} onChange={(e)=>setDetail(e.target.value)}></textarea></td>
                        </tr>
                        <tr>
                            <td>수량</td>
                            <td><input onChange={(e)=>setStock(e.target.value)} type="text"/></td>
                        </tr>
                        <tr>
                            <td>상품 상태</td>
                            <td>
                                <select onChange={(e)=>setStatus(e.target.value)}>
                                    <option value="FOR_SALE">판매 중</option>
                                    <option value="품절">품절</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>상품 이미지</td>
                            <td><input onChange={onImageHandler} type="file"/></td>
                        </tr>
                    </table>
                    <div className="product_register_btns">
                        <button className="product_register_btn" onClick={onRegClick}>상품 등록하기</button>
                    </div>
                </div>
            </div>
        </>
    )
}

export default ProductReg;