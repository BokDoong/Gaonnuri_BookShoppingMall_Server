import { useDispatch } from 'react-redux';
import { Link, useNavigate } from 'react-router-dom';
import { remove_userInfo } from '../../store/authReducer';

const HeaderAdmin = () => {
    const navigate = useNavigate();
    const dispatch = useDispatch();
    const logoutClick = () =>{
        dispatch(remove_userInfo());
        localStorage.removeItem("accessToken");
        localStorage.removeItem("refreshToken");
        navigate(`/`);
    }
    return (
       <><div id="Header">
            <div className="Nav">
                <div className="Header">
                    <div className="logo">
                       UOU-MALL
                    </div>
                    <div onClick={logoutClick}className='logoutMenu'>
                        <a href='/'>LOGOUT</a>
                    </div>
                </div>
            </div>
        </div>
        </>
    )
}

export default HeaderAdmin;