import axios from "axios";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { useNavigate, useParams } from "react-router-dom";

const Detail = () => {
    const user = useSelector(state=>state.user);
    const navigate = useNavigate();
    const {id} = useParams();
    const [data,setData] = useState([]);
    const [count,setCount] = useState(1);
    //상품데이터 GET
    const getProduct =() =>{
        //const uri = `/api/v1/item/detail?id=${id}`;
        const uri = `http://localhost:3001/api_v1_item_detail_id=${id}`;
        //Axios.get(uri)
        axios.get(uri)
        .then((res)=>{setData(res.data);console.log(res.data)});
    }
    useEffect(()=>{
        getProduct();
    },[]);

    const onMinusClick = () => {
        if(count!==1)setCount(count-1);
    }
    const onPlusClick = () => {
        if(data.stockNumber>count){
            setCount(count+1);
        }
    }

    const onCartClick = () => {
        if(!user.isLoggedIn){
            navigate("/login");
        }
        const postdata = {"itemId":id,"count":count};
        const uri = "/cart"
        axios.post(uri,postdata);
        document.querySelector(".popup_container_hidden").classList.add("popup_container");
        document.querySelector(".popup_container").classList.remove("popup_container_hidden");
    }
    const onXbtnClick = () => {
        document.querySelector(".popup_container").classList.add("popup_container_hidden");
        document.querySelector(".popup_container_hidden").classList.remove("popup_container");
    }

    const onOrderClick = () => {
        let res = window.confirm("정말로 상품을 구매하시겠습니까?");
        if(res){
            const uri = "http://localhost:3001/order"
            let postdata = {itemId:data.id,count:count};
            axios.post(uri,postdata);
            navigate("/mypage/order");
        }
    }
    return (
        <div className="detail_container">
            <div className="detail_ImageTitle">
            <div className="detail_image">
                <img style={{width:"100%",height:"500px"}} src={data.imageUrl}/>
            </div>
            <div className="detail_title">
                <div className="detail_title_name">
                    {data.itemName}
                </div>
                <div className="detail_title_price">
                    <span className="detail_title_saleprice">판매가</span>
                    <span className="detail_title_pricedisplay">{data.price}원</span>
                </div>
                <div className="detail_title_countForm">
                    <div className="detail_title_countForm_text">수량</div>
                    <div>
                        <div className="detail_title_count"onClick={onMinusClick}>-</div>
                        <div className="detail_title_count">{count}</div>
                        <div className="detail_title_count"onClick={onPlusClick}>+</div>
                    </div>
                </div>
                <div className="detail_title_totalprice">
                    <h3 className="detail_title_totalprice_text">총 주문금액</h3>
                    <span className="detail_title_totalprice_price">{data.price*count}원</span>
                </div>
                <div className="detail_title_btns">
                    <div className="detail_title_btn"  onClick={onCartClick}style={{float:"left", marginLeft:"70px"}}><a>장바구니</a></div>
                    <div className="detail_title_btn" onClick={onOrderClick}style={{float:"right", backgroundColor:"black", color:"white"}}>바로 구매</div>
                </div>
            </div>
           
            </div>
            <div style={{marginTop:"100px"}}>
                <hr></hr>
                <div>상세정보</div>
                <div style={{marginTop:"30px"}}>
                    <pre>{data.itemDetail}</pre>
                </div>
            </div>
            <div className="popup_container_hidden">
                <div className="cart_popup">
                    <div className="cart_popup_x">
                    <button onClick={onXbtnClick}>
                        Ⅹ
                    </button>
                    </div>
                    장바구니에 상품을 담았습니다.
                    <div className="gocart_link">
                    <a href="/cart">
                        <div>
                            장바구니 바로가기
                        </div>
                    </a>
                    </div>
                    
                </div>
            </div>
        </div>
    )
}

export default Detail;