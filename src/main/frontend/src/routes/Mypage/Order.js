import axios from "axios";
import { useEffect, useState } from "react";
import { Axios } from "../../CustomAxios";

const Order = ({data,getData,checkedItemHandler}) => {
    const [count,setCount] = useState(data.count);

    useEffect(()=>{
        const uri = "http://localhost:3001/member_cart/"+data.id;
        const temp = data;
        temp.count = count;
        axios.put(uri,temp);
        console.log(temp);
    },[count,data])

    const onDeleteClick = () => {
        let del = window.confirm("정말 주문을 취소하시겠습니까?");
        if(del){
            const uri = "api/v1/order/cancel?orderid="+data.id;
            Axios.post(uri);
            getData();
            alert("주문이 취소되었습니다");
        }
        
    }
    return (
        <div className="order_table_bottom">
            <div className="order_table_cell_info">
                <div style={{display:"flex"}}>
                    <img className="cart_img" src="https://thumbs.dreamstime.com/b/transparent-designer-must-have-fake-background-39672616.jpg"/>
                    <div>
                        <div className="order_item_name">{data.item_name}</div>
                        <div className="order_item_price">{data.price}원</div>
                    </div>
                </div>
            </div>
            <div className="order_table_cell_count">
                <div style={{width:"108px", margin:"auto"}}>
                    <div>
                       {count}
                    </div>
                </div>
            </div>
            <div className="cart_table_cell_price">{count*data.price}원</div>
            <div className="order_table_cell_status">{data.status}</div>
            <div className="order_table_cell_choice"><button onClick={onDeleteClick}>주문취소</button></div>
        </div>
    )
}

export default Order;