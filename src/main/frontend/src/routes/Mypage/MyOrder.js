import axios from "axios";
import { useEffect, useState } from "react";
import Order from "./Order";
import MypageBar from "./MypageBar";

const MyOrder = () =>{
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


    return (
        <div>
            <MypageBar/>
            <div className="myorder_container">
            <div style={{fontSize:"30px",marginBottom:"20px"}}>주문/배송조회</div>
            <div className="cart_table">
                <div className="cart_table_top">
                    <div className="cart_table_top_cell">상품 정보</div>
                    <div style={{width: "200px"}}className="cart_table_top_cell">수량</div>
                    <div style={{width: "200px"}}className="cart_table_top_cell">주문금액</div>
                    <div style={{width: "15%"}}className="cart_table_top_cell">진행상태</div>
                    <div style={{width: "15%"}}className="cart_table_top_cell"></div>
                </div>
                {data.map((data)=>{
                    return(
                        <Order data={data} getData={getData}/>
                    )
                    
                })}
            </div>
        </div>
        </div>
    )
}

export default MyOrder;