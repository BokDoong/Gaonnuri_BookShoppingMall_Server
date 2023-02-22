import axios from "axios";
import { useEffect, useState } from "react";
import CartItem from "./CartItem";

const Cart = () =>{
    const [data,setData] = useState([]);
    const [list,setList] = useState(new Set());
    const getData = () =>{
        const uri = "http://localhost:3001/member_cart";
        axios.get(uri)
        .then((res)=>{setData(res.data);console.log(res)});
    }
    useEffect(()=>{
        getData();
    },[])

    const onOrderClick = () => {
        const uri = "http://localhost:3001/cart_order";
        let temp = [];
        list.forEach((product)=>{temp=[...temp,{cartItemId:product}]});
        console.log("temp:",temp);
        axios.post(uri,temp);
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
            </div>
            <div className="cart_order">
                <button onClick={onOrderClick}className="cart_order_btn">주문하기</button>
            </div>
        </div>
    )
}

export default Cart;