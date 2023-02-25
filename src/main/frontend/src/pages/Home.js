import HomeImage from "../assets/HomeImage.jpg";


const Home = () =>{
    return (
        <div className="container">
           <div className="content">
                <div id="Main_wrap">
                    <div className="left">
                        <a><img src={HomeImage}/></a>
                    </div>
                    <div className='right'>
                    <pre>It refers to the actions, phenomena, and movements that produce valuable products and services. Pursuing good making and tireless design, we reinterpret the essence of the object we look at and worry about and reveal it in a natural and restrained form.</pre>
                    </div>
                </div>
           </div>
        </div>
    )
}

export default Home;