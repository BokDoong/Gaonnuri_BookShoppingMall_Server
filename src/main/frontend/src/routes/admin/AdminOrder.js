import axios, { Axios } from "axios";
import { useEffect, useState } from "react";

const AdminOrder = ({data,getData,checkedItemHandler}) => {

    const onConfirmClick = () => {
        let del = window.confirm("입금이 확인되었습니까?");
        if(del){
            const uri = `api/v1/admin/orders?id=${data.id}&status=2`;
            Axios.put(uri);
            getData();
            alert("입금확인 처리되었습니다");
        }
    }
    const onWaitClick = () =>{
        let del = window.confirm("입금대기 상태로 변경하시겠습니까?");
        if(del){
            const uri = `api/v1/admin/orders?id=${data.id}&status=1`;
            Axios.put(uri);
            getData();
            alert("입금대기 처리되었습니다");
        }
    }
    const onCancelClick = () => {
        let del = window.confirm("주문을 취소하시겠습니까?");
        if(del){
            const uri = `api/v1/admin/orders?id=${data.id}&status=0`;
            Axios.put(uri);
            getData();
            alert("주문취소 처리되었습니다");
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
            <div className="admin_order_table_choice">
                <button className="order_table_btn"onClick={onConfirmClick}>입금확인</button>
                <button className="order_table_btn"onClick={onWaitClick}>입금대기</button>
                <button className="order_table_btn"onClick={onCancelClick}>주문취소</button>
            </div>
        </div>
    )
}

export default AdminOrder;