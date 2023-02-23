const MypageBar = () => {
    return (
        <div className="mypage_container">
            <div className='mypage_list'>
                <h2>마이페이지</h2>
                <div style={{marginBottom:"40px"}}>
                    <p className='mypage_menuname'>쇼핑</p>
                    <ul>
                        <li>
                            <div className='mypage_menu'><a href="/mypage/order/1">주문/배송 조회</a></div>
                        </li>
                    </ul>
                </div>
                <div>
                    <p className='mypage_menuname'>계정 설정</p>
                    <ul>
                        <li>
                            <div className='mypage_menu'><a href='/mypage/information'>개인정보관리</a></div>
                            <div className='mypage_menu'><a>배송지 관리</a></div>
                            <div className='mypage_menu'><a>회원 탈퇴</a></div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    )
}

export default MypageBar;