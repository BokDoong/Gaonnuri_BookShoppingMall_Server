import { useNavigate } from "react-router-dom/dist";
import { Axios } from "../../utils/CustomAxios";

const AdminProduct = ({product,getProduct}) =>{
    const uri = `/api/v1/admin/delete?id=${product.id}`
    const navigate = useNavigate();
    //삭제버튼 클릭
    const deleteClick = async(event)=>{
      await Axios.delete(uri);
      getProduct();
    }
    const editClick = () => {
        navigate(`/admin/product/edit/${product.id}`);
    }
    return (
        <>
            <tr> 
                <td><img style={{width:"100px",height:"100px"}} src={product.imageUrl}/></td>
                <td className="">{product.itemName}</td>
                <td className="">{product.price}</td>
                <td className="">{product.itemStatus}</td>
                <td>
                    <input onClick={editClick}type="button" value="수정"/>
                    <input onClick={deleteClick} type="button" value="삭제"/>
                </td>
            </tr>
        </>
    )
}

export default AdminProduct;