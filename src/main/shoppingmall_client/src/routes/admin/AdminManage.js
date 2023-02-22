import axios from "axios";
import { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import { useNavigate } from "react-router-dom/dist";
import { Axios } from "../../CustomAxios";
import AdminMenu from "./AdminMenu";
import AdminProduct from "./AdminProduct";
import ManagePaging from "./ManagePaging";

const AdminManage = () => {
  const {id} = useParams();
   const [data,setData] = useState([]);
  const navigate = useNavigate();
   //상품 데이터 GET
    //const uri = `/api/v1/admin?page=${id}`;
    const uri = `http://localhost:3001/api_v1_admin_page=${id-1}`;
    const getProduct =() =>{
      //Axios.get(uri)
      axios.get(uri)
      .then((response)=>{
      setData(response.data);
      console.log(response.data);
      })
    }
    
    useEffect(()=>{
        getProduct();
    },[]);

    const onRegisterClick = () => {
      navigate("/admin/product/register");
    }
    return (
      <>
        <AdminMenu menu={"상품관리"}/>
        <div className="adminProduct" style={{float:"right",width:"70%",marginBottom:"120px",marginTop:"50px",marginRight:"90px"}}>
            <div style={{marginTop:"30px"}}>
            <button onClick={onRegisterClick}>상품 등록</button>
            </div>
            <hr style={{width:"100%", float:"left"}}></hr>
            <div style={{textAlign:"center",marginTop:"50px",marginBottom:"50px"}}>
              <h3>상품 목록</h3>
            </div>
            <div className="product_table_container">
              <table className="product_table">
                <colgroup>
                </colgroup>
                <tbody>
                  <tr>  
                    <td></td>
                    <td className="">상품명</td>
                    <td className="">가격</td>
                    <td className="">상품 상태</td>
                    <td></td>
                    <td></td>
                  </tr>
                    {data.map((product)=>
                      <AdminProduct product={product} getProduct={getProduct}/>
                    )}
                </tbody>
              </table>
              <ManagePaging page={id}/>
            </div> 
        </div>
      </>
    )
}

export default AdminManage;