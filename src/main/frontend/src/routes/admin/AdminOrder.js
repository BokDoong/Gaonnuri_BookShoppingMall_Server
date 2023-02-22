import axios from "axios";
import { useEffect, useState } from "react";

const AdminOrder = ({data,getData,checkedItemHandler}) => {

   
    const onConfirmClick = () => {
        let del = window.confirm("입금이 확인되었습니까?");
        if(del){
            const putdata = {}
            const uri = "http://localhost:3001/member_order/"+data.id;
            axios.put(uri,putdata);
            getData();
            alert("입금확인 처리되었습니다");
        }
        
    }
    return (
        <div className="order_table_bottom">
            <div className="order_table_cell_info">
                {data.userName}
            </div>
            <div className="order_table_cell_date">
                <div style={{width:"108px", margin:"auto"}}>
                    {data.orderDate}
                </div>
            </div>
            <div className="cart_table_cell_price">{data.orderPrice}원</div>
            <div className="order_table_cell_status">{data.orderStatus}</div>
            <div className="order_table_cell_choice"><button onClick={onConfirmClick}>입금확인 처리</button></div>
        </div>
    )
}

export default AdminOrder;