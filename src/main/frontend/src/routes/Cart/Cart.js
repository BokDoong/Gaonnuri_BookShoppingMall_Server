import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import { Axios } from "../../CustomAxios";
import { CartPaging } from "../Paging";
import CartItem from "./CartItem";

const Cart = () =>{
    const navigate = useNavigate();
    const {id} = useParams();
    const [data,setData] = useState([]);
    const [list,setList] = useState(new Set());
    const getData = () =>{
        const uri = `/api/v1/cart?page=${id-1}`;
        Axios.get(uri)
        .then((res)=>{setData(res.data);console.log(res)});
    }
    useEffect(()=>{
        getData();
    },[])

    const onOrderClick = () => {
        const uri = "/api/v1/cartItem/orders";
        let temp = [];
        list.forEach((product)=>{temp=[...temp,{cartItemId:product}]});
        console.log("temp:",temp);
        Axios.post(uri,temp)
        .then(()=>{
            alert("주문이 완료되었습니다");
            navigate("/mypage/order/1");
        })
    }

    const ontopCheck = (e) => {
            const checkboxes = document.querySelectorAll('.cart_checkbox');
            checkboxes.forEach((checkbox) => {
              checkbox.checked = e.target.checked;
            })
            if(e.target.checked)data.forEach((data)=>{
                list.add(data.id);
                setList(list);
            })    
            else {
                list.clear();
                setList(list);
            }
            console.log(list);
    }
    
    const checkedItemHandler = (id, isChecked) => {
        if (isChecked) {
          list.add(id);
          setList(list);
          console.log("listlength:",list.size," data:",data.length);
          if(list.size===data.length){
            const checkboxes = document.querySelector('.cart_topcheckbox');
            checkboxes.checked=true;
          }
          console.log("add!");
          console.log(list);
        } else if (!isChecked && list.has(id)) {
          list.delete(id);
          setList(list);
          console.log("listlength:",list.length," data:",data.length);
          if(list.size!==data.length){
            const checkboxes = document.querySelector('.cart_topcheckbox');
            checkboxes.checked=false;
          }
          console.log("delete!");
          console.log(list);
        }
      };

    return (
        <div className="cart_container">
            <div style={{fontSize:"30px",marginBottom:"20px"}}>장바구니</div>
            <div className="cart_table">
                <div className="cart_table_top">
                    <div style={{width: "4.3%"}} className="cart_table_top_cell">
                        <span><input onChange={ontopCheck}className="cart_topcheckbox"type="checkbox"/></span>
                    </div>
                    <div className="cart_table_top_cell">상품 정보</div>
                    <div style={{width: "200px"}}className="cart_table_top_cell">수량</div>
                    <div style={{width: "200px"}}className="cart_table_top_cell">주문금액</div>
                    <div style={{width: "15%"}}className="cart_table_top_cell">선택</div>
                </div>
                {data.map((data)=>{
                    return(
                        <CartItem data={data} getData={getData} checkedItemHandler={checkedItemHandler}/>
                    )
                    
                })}
                <CartPaging page={id}/>
            </div>
            <div className="cart_order">
                <button onClick={onOrderClick}className="cart_order_btn">주문하기</button>
            </div>
        </div>
    )
}

export default Cart;