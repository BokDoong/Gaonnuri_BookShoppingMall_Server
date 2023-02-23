import { useDispatch, useSelector } from 'react-redux';
import { Link, useNavigate } from 'react-router-dom';
import { remove_userInfo } from '../../data/authReducer';

const Header = () => {
    const dispatch = useDispatch();

    const user = useSelector(state=>state.user);

    //로그아웃 클릭
    const logoutClick = () =>{
        dispatch(remove_userInfo());
    }
    return (
       <div id="Header">
            <div className="Nav">
                <div className="Header">
                    <div className="logo">
                        <Link to="/">SHOPPING MALL</Link>
                    </div>
                    <div className="menu_list">
                        <ul className="menu">
                            <li className="drop">
                                <p>
                                    <Link to="/category/0/1">SHOP</Link>
                                </p>
                                <ul className="sub sub1">
                                    <div className="inner">
                                        <ul>
                                            <p>
                                                <a href='/category/0/1'>ALL</a>
                                            </p>
                                        </ul>
                                    </div>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <div className="membership_list">
                        <ul className="menu">
                            <li className="drop">
                                MEMBERSHIP
                                <ul className="sub sub2">
                                    <div className="inner">
                                        <ul>
                                            {user.isLoggedIn?(
                                                <div>
                                                    <p >
                                                        <a onClick={logoutClick} href="/">LOGOUT</a>
                                                    </p>
                                                    <p>
                                                        <Link to='/mypage'>MY PAGE</Link>
                                                    </p>
                                                </div>
                                                ):(
                                                <div>
                                                    <p>
                                                        <Link to='/login'>LOGIN</Link>
                                                    </p>
                                                    <p>
                                                        <Link to='/register'>REGISTER</Link>
                                                    </p>
                                                </div>
                                                )}
                                            
                                        </ul>
                                    </div>
                                </ul>
                            </li>
                            <li>
                                {user.isLoggedIn?<a href='/cart'>CART</a>:<a href='/login'>CART</a>}
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
       </div>
    )
}

export default Header;