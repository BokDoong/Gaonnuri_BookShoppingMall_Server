import axios from "axios";
import { useEffect, useState } from "react";
import { Axios } from "../../utils/CustomAxios";

const Order = ({data,getData}) => {
    const onStatusHandler = () => {
        if(data.orderStatus==="ORDER"){
            return <>입금확인</>
        }
        else if(data.orderStatus==="WAITING"){
            return <>입금대기</>
        }
        else return <>주문취소</>
    }
    const onDeleteClick = () => {
        let del = window.confirm("정말 주문을 취소하시겠습니까?");
        if(del){
            const uri = "/api/v1/order/cancel?orderId="+data.orderId;
            Axios.post(uri)
            .then(()=>{
                window.location.reload();
                getData();
            });
            
        }
    }
    return (
        <div className="order_table_bottom">
            <div style={{width: "25%"}}className="order_table_cell_date">
                <div>{data.orderDate}</div>
            </div>
            <div style={{width: "30%"}}className="order_table_cell_info">
                <img style={{float:"left"}}className="order_img" src={data.orderItemDtoList[0].imageUrl}/>
                <div style={{marginTop:"20px",textAlign:"left"}}>
                    <div className="order_item_name">{data.orderItemDtoList[0].itemName}</div>
                    <div className="order_item_price">{data.orderItemDtoList[0].itemPrice}원 X {data.orderItemDtoList[0].count}</div>
                </div>      
            </div>
            <div style={{width: "15%"}}className="cart_table_cell_price">{data.orderPrice}원</div>
            <div style={{width: "15%"}}className="order_table_cell_status">{onStatusHandler()}</div>
            <div style={{width: "15%"}}className="order_table_cell_choice"><button onClick={onDeleteClick}>주문취소</button></div>
        </div>
    )
}

export default Order;