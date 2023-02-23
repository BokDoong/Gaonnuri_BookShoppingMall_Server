import {BrowserRouter as Router, Route,Routes} from 'react-router-dom';
import Header from './routes/Header/Header';
import All from './routes/Shop/All';
import Cart from './routes/Cart/Cart';
import Dashboard from './routes/admin/Dashboard';
import HeaderAdmin from './routes/Header/HeaderAdmin';
import Home from './routes/Home';
import Login from './routes/Login';
import Register from './routes/Register';
import ProductReg from './routes/admin/ProductReg';
import Info from './routes/Mypage/Info';
import MypageMain from './routes/Mypage/MypageMain';
import './App.css';
import { useSelector } from 'react-redux';
import Detail from './routes/Shop/Detail';
import ProductEdit from './routes/admin/ProductEdit';
import AdminManage from './routes/admin/AdminManage';
import MyOrder from './routes/Mypage/MyOrder';
import OrderManage from './routes/admin/OrderManage';

function App() {
  // 유저 상태 가져옴
  const user = useSelector(state=>state.user);
  console.log(user);
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
