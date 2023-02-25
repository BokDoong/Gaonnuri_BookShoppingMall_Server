import { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Axios } from '../../CustomAxios';
import Paging from '../paging';
import ShopPaging from './ShopPaging';

const Shop1 = () =>{
    const {id} = useParams();
    const [data,setData] = useState([]);

    useEffect(()=>{
        getProduct();
    },[])
    
    //상품데이터 GET
    const getProduct =async() =>{
        const uri = `/product_${id}`;
        await Axios.get(uri)
        .then((response)=>{
            return response.data.filter((product)=>product.itemStatus==="판매 중"&&product.category==="CATEGORY1")
        })
        .then((data)=>setData(data));
    }
    return (
        <div className="product_container">
            <div className="product_content">
                <div className='category_list'>
                    <h2 className='category_title'>SHOP</h2>
                    <ul>
                        <li >
                            <a id="selected" href="/category/0/1">ALL</a>
                        </li>
                        <li>
                            <a href="/category/1/1">CATEGORY1</a>
                        </li>
                        <li>
                            <a href="/category/2/1">CATEGORY2</a>
                        </li>
                        <li>
                            <a href="/category/3/1">CATEGORY3</a>
                        </li>
                    </ul>
                </div>
                <div className='product_list'>
                    <ul className='product_ul'>
                        {
                        data.map((data)=> {
                            const detailurl = `/detail/${data.id}`;
                            return (
                                <li>
                                <a className="product_list_a"href={detailurl}>
                                    <div>
                                    <img style={{width:"100%"}} src="https://thumbs.dreamstime.com/b/transparent-designer-must-have-fake-background-39672616.jpg"/>
                                    </div>
                                    <div>
                                        <div style={{float:"left"}}>{data.itemName}</div>
                                        <div style={{float:"right"}}>{data.price}원</div>
                                    </div>
                                </a>
                            </li>
                            )
                        })}
                    </ul>
                    <ShopPaging page={id}category={1}/>
                </div>
            </div>
        </div>
    )
}

export default Shop1;