const ShopPaging = ({page,category}) => {

    const rendering = () => {
        let start;
        let end;
        if(page%5==0){
            start = page-4;
            end = page/1;
        }
        else {
            start = page-page%5+1;
            end = start+4;
        }
        const backUrl=`/category/${category}/${start-5}`;
        let result = []; 
        if(start>5){
            result.push(<a href={backUrl}>&lt;</a>);
        }
        for (let i = start; i <= end; i++) {
            const url = `/category/${category}/${i}`;
            if(i>0){
                if(i===page){
                    result.push(<a href={url}>{i}</a>);    
                }
                else {
                    result.push(<a href={url}>{i}</a>);   
                }
            }
        }
        const frontUrl=`/category/${category}/${end+1}`;
        result.push(<a href={frontUrl}>&gt;</a>);
        return result;
    };

    return (
        <div className="numNav">
            {
                rendering()
            }
           
        </div>
    )
}

export {ShopPaging};


const CartPaging = ({page}) => {

    const rendering = () => {
        let start;
        let end;
        if(page%5===0){
            start = page-4;
            end = page/1;
        }
        else {
            start = page-page%5+1;
            end = start+4;
        }
        const backUrl=`/cart/${start-5}`;
        let result = []; 
        if(start>5){
            result.push(<a href={backUrl}>&lt;</a>);
        }
        for (let i = start; i <= end; i++) {
            const url = `/cart/${i}`;
            if(i>0){
                result.push(<a href={url}>{i}</a>);   
            }
        }
        const frontUrl=`/cart/${end+1}`;
        result.push(<a href={frontUrl}>&gt;</a>);
        return result;
    }

    return (
        <div className="numNav">
            {
                rendering()
            }
           
        </div>
    )
}

export {CartPaging};



const MyOrderPaging = ({page}) => {

    const rendering = () => {
        let start;
        let end;
        if(page%5===0){
            start = page-4;
            end = page/1;
        }
        else {
            start = page-page%5+1;
            end = start+4;
        }
        const backUrl=`/mypage/order/${start-5}`;
        let result = []; 
        if(start>5){
            result.push(<a href={backUrl}>&lt;</a>);
        }
        for (let i = start; i <= end; i++) {
            const url = `/mypage/order/${i}`;
            if(i>0){
                result.push(<a href={url}>{i}</a>);   
            }
        }
        const frontUrl=`/mypage/order/${end+1}`;
        result.push(<a href={frontUrl}>&gt;</a>);
        return result;
    }

    return (
        <div className="numNav">
            {
                rendering()
            }
           
        </div>
    )
}

export {MyOrderPaging};
