import axios from "axios";
import { useEffect, useState } from "react";
import { Axios } from "../../utils/CustomAxios";

const CartItem = ({data,getData,checkedItemHandler}) => {
    const [count,setCount] = useState(data.count);

    useEffect(()=>{
        const uri = `/api/v1/cartItem?cartItemsId=${data.cartItemsId}&count=${count}`;
        Axios.put(uri);
    },[count,data])

    const checkHandler = (e) => {
        checkedItemHandler(data.cartItemsId, e.target.checked);
    };

    const onMinusClick = () => {
        if(count!==1)setCount(count-1);
    }
    const onPlusClick = () => {
        setCount(count+1);
    }
    const onDeleteClick = () => {
        const uri = `/api/v1/cartItem?cartItemId=${data.cartItemsId}`;
        Axios.delete(uri);
        getData();
    }
    return (
        <div className="cart_table_bottom">
            <div className="cart_table_cell_check">
                <span style={{minWidth: "24px",minHeight: "24px", lineHeight: "24px"}}><input onChange={checkHandler}className="cart_checkbox" type="checkbox"/></span>
            </div>
            <div className="cart_table_cell_info">
                <div style={{display:"flex"}}>
                    <img className="cart_img" src={data.imageUrl}/>
                    <div style={{marginLeft:"10px"}}>
                        <div className="cart_item_name">{data.itemName}</div>
                        <div className="cart_item_price">{data.price}원</div>
                    </div>
                </div>
            </div>
            <div className="cart_table_cell_count">
                <div style={{width:"108px", margin:"auto"}}>
                    <div style={{display:"flex"}}>
                        <button onClick={onMinusClick} className="countbtn">-</button>
                        <button style={{color:"black"}}className="countbtn">{count}</button>
                        <button onClick={onPlusClick} className="countbtn">+</button>
                    </div>
                </div>
            </div>
            <div className="cart_table_cell_price">{count*data.price}원</div>
            <div className="cart_table_cell_choice"><button className="cart_delete_btn" onClick={onDeleteClick}>삭제하기</button></div>
        </div>
    )
}

export default CartItem;