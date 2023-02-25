const PhoneNumber = ({data,a,b,c,setA,setB,setC}) =>{
    const styleObj1 = {
		border: "1px solid #ddd",
        width: "7%",
        lineHeight: "28px",
        height: "30px",
        marginRight: "5px"
    }

        return (
                <div>
                    <input onChange={(e)=>setA(e.target.value)} style={styleObj1} type="text" value={a}/>
                    <> - </>
                    <input onChange={(e)=>setB(e.target.value)} style={styleObj1} type="text" value={b}/>
                    <> - </> 
                    <input onChange={(e)=>setC(e.target.value)} style={styleObj1} type="text" value={c}/>
                </div>
                )
}
export default PhoneNumber;