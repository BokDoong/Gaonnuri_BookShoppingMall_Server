import { Link } from "react-router-dom"

const AdminMenu = ({menu}) => {
   /* if(menu==="상품관리"){
        const target = document.querySelector(".product_manage");
    console.log(target);
    }
    */  
   
    return (
            <div className='admin_category_list'>
                    <h2 className='category_title'>관리자 메뉴</h2>
                    <ul>
                        <li >
                            <Link to='/admin/'>대시보드</Link>
                        </li>
                        <li>
                            <Link to='/admin/order/1'>주문관리</Link>
                        </li>
                        <li>
                        <div className="product_manage">
                            <Link to='/admin/product/1'>상품관리</Link>
                        </div>
                        </li>
                    </ul>
                </div>
    )
}

export default AdminMenu;