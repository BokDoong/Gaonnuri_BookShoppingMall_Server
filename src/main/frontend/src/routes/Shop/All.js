import axios from 'axios';
import { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Axios } from '../../CustomAxios';
import Paging from '../paging';
import ShopPaging from './ShopPaging';

const All = () =>{
    const {id} = useParams();
    const [data,setData] = useState([]);
    

    useEffect(()=>{
        getProduct();
    },[])
    
    //상품데이터 GET
    const getProduct =async() =>{
        const uri = `/api/v1/item?page=${id-1}`;
        await Axios.get(uri)
        .then((response)=>{
            return response.data.content.filter((product)=>product.itemStatus==="FOR_SALE")
        })
        .then((data)=>setData(data));
    }
    return (
        <div className="product_container">
            <div className="product_content">
                <div className='category_list'>
                    <h2 className='category_title'>SHOP</h2>
                    <ul>
                        <li>
                            <a id="selected" href="/category/0/1">ALL</a>
                        </li>
                        
                    </ul>
                </div>
                <div className='product_list'>
                    <ul className='product_ul'>
                        {
                        data.map((data)=> {
                            console.log(data);
                            const detailurl = `/detail/${data.id}`;
                            return (
                                <li>
                                <a className="product_list_a"href={detailurl}>
                                    <div>
                                    <img style={{width:"100%"}} src={data.imageUrl}/>
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
                    <ShopPaging page={id}category={0}/>
                </div>
            </div>
        </div>
    )
}

export default All;