import {BrowserRouter as Router, Route,Routes} from 'react-router-dom';
import Header from './pages/Header/Header';
import All from './pages/Shop/All';
import Cart from './pages/Cart/Cart';
import Dashboard from './pages/admin/Dashboard';
import HeaderAdmin from './pages/Header/HeaderAdmin';
import Home from './pages/Home';
import Login from './pages/Login';
import Register from './pages/Register';
import ProductReg from './pages/admin/ProductReg';
import Info from './pages/Mypage/Info';
import MypageMain from './pages/Mypage/MypageMain';
import './styles/App.css';
import { useSelector } from 'react-redux';
import Detail from './pages/Shop/Detail';
import ProductEdit from './pages/admin/ProductEdit';
import AdminManage from './pages/admin/AdminManage';
import MyOrder from './pages/Mypage/MyOrder';
import OrderManage from './pages/admin/OrderManage';
import Reset from './pages/Reset';

function App() {
  // 유저 상태 가져옴
  const user = useSelector(state=>state.user);
  return ( 
    <Router>
      {/* 사용자 롤에 따른 헤더 및 페이지 */}
      {user.info.role==="ROLE_ADMIN"?<HeaderAdmin/>:<Header/>}
      <Routes>
        {user.info.role==="ROLE_ADMIN"?<>
          <Route path="/admin/" element={<Dashboard/>}></Route>
          <Route path="/admin/product/:id" element={<AdminManage/>}></Route>
          <Route path="/admin/product/register" element={<ProductReg/>}></Route>
          <Route path="/admin/order/:id" element={<OrderManage/>}></Route>
          <Route path="/admin/product/edit/:id" element={<ProductEdit/>}></Route>
        </>
        :<>
          <Route path="/" element={<Home />}></Route>
          <Route path="/reset" element={<Reset/>}></Route>
          <Route path="/category/0/:id" element={<All />}></Route>
          <Route path="/login" element={<Login />}></Route>
          <Route path="/register" element={<Register/>}></Route>
          <Route path="/mypage" element={<MypageMain/>}></Route>
          <Route path="/mypage/information" element={<Info />}></Route>
          <Route path="/mypage/order/:id" element={<MyOrder/>}></Route>
          <Route path="/cart/:id" element={<Cart/>}></Route>
          <Route path="/detail/:id" element={<Detail/>}></Route>
        </>}
      </Routes>
    </Router>
  )
}

export default App;
