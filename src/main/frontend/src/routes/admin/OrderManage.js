import axios from "axios";
import { useEffect, useState } from "react";
import AdminOrder from "./AdminOrder";
import AdminMenu from "./AdminMenu";
import { useParams } from "react-router-dom";
import { Axios } from "../../CustomAxios";

const OrderManage = () =>{
    const {id} = useParams();
    const [loading,setLoading] = useState(false);
    const [data,setData] = useState([]);
    const getData = () =>{
        const uri = `api/v1/admin/orders?page=${id-1}`;
        Axios.get(uri)
        .then((res)=>{setData(res.data.content);setLoading(true);console.log(res)});
    }
    useEffect(()=>{
        getData();
    },[])


    return (
        <div>
            <AdminMenu/>
            <div className="myorder_container">
            <div style={{fontSize:"30px",marginBottom:"20px"}}>주문관리</div>
            <div className="cart_table">
                <div className="cart_table_top">
                    <div style={{width: "150px"}}className="cart_table_top_cell">회원명</div>
                    <div style={{width: "150px"}}className="cart_table_top_cell">주문일</div>
                    <div style={{width: "150px"}}className="cart_table_top_cell">주문금액</div>
                    <div style={{width: "15%"}}className="cart_table_top_cell">진행상태</div>
                    <div style={{width: "15%"}}className="cart_table_top_cell"></div>
                </div>
                {console.log(loading)}
                {!loading?<></>:data.map((data)=>{
                    return(
                        <AdminOrder data={data} getData={getData}/>
                    )
                    
                })}
            </div>
        </div>
        </div>
    )
}

export default OrderManage;