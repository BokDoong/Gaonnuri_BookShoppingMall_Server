import { useEffect } from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom/dist";

const ManagePaging = ({page}) => {
    console.log("params로 들어온 페이지:",page);

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
        const backUrl=`/admin/product/${start-5}`;
        let result = []; 
        if(start>5){
            result.push(<a href={backUrl}>&lt;</a>);
        }
        for (let i = start; i <= end; i++) {
            const url = `/admin/product/${i}`;
            if(i>0){
                result.push(<a href={url}>{i}</a>);   
            }
        }
        const frontUrl=`/admin/product/${end+1}`;
        result.push(<a href={frontUrl}>&gt;</a>);
        console.log(result);
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

export default ManagePaging;