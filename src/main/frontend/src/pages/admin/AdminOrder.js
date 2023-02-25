import { Axios } from "../../utils/CustomAxios";

const AdminOrder = ({data,getData,checkedItemHandler}) => {
    let status;
    if(data.orderStatus==="ORDER"){
        status = "입금확인";
    }
    else if(data.orderStatus==="WAITING"){
        status = "입금대기";
    }
    else {
        status = "주문취소";
    }
    const onConfirmClick = () => {
        let del = window.confirm("입금이 확인되었습니까?");
        if(del){
            const uri = `/api/v1/admin/orders?orderId=${data.orderId}&status=2`;
            Axios.put(uri)
            .then(()=>{
                alert("입금확인 처리되었습니다");
                getData();
            })
        }
    }
    const onWaitClick = () =>{
        let del = window.confirm("입금대기 상태로 변경하시겠습니까?");
        if(del){
            const uri = `/api/v1/admin/orders?orderId=${data.orderId}&status=1`;
            Axios.put(uri)
            .then(()=>{
                alert("입금대기 처리되었습니다");
                getData();
            })
        }
    }
    const onCancelClick = () => {
        let del = window.confirm("주문을 취소하시겠습니까?");
        if(del){
            const uri = `/api/v1/admin/orders?orderId=${data.orderId}&status=0`;
            Axios.put(uri)
            .then(()=>{
                alert("주문취소 처리되었습니다");
                getData();
            })
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
            <div className="cart_table_cell_price">{data.orderPrice}</div>
            <div className="order_table_cell_status">{status}</div>
            <div className="admin_order_table_choice">
                <button className="order_table_btn"onClick={onConfirmClick}>입금확인</button>
                <button className="order_table_btn"onClick={onWaitClick}>입금대기</button>
                <button className="order_table_btn"onClick={onCancelClick}>주문취소</button>
            </div>
        </div>
    )
}

export default AdminOrder;