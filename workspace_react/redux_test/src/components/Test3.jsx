import React, { useState } from 'react'
import { cartList } from '../redux/data'
import { useDispatch, useSelector } from 'react-redux'
import { addCart } from '../redux/cartListSlice';

const Test3 = () => {
  const dispatch = useDispatch();

  //store에 저장된 장바구니 목록 데이터
  const cartList =  useSelector(state => state.cartList)

  //입력 데이터 저장할 변수
  const [data, setData] = useState({
    cartNum : '',
    itemName : '',
    price : 0
  })

  const handleData = (e) => {
    setData({
      ...data,
      [e.target.name] : e.target.value
    })
  };

  return (
    <div>
      <div>
        {
          cartList.map((cart, i) => {
            return(
              <div key={i}>{cart.cartNum} / {cart.itemName} / {cart.price}</div>
            )
          })
        }
      </div>
      <div>
        장바구니 번호 : <input type='text' name='cartNum' value={data.cartNum} onChange={e => handleData(e)}/>
      </div>
      <div>
        상품명 : <input type='text' name='itemName' value={data.itemName} onChange={e => handleData(e)}/>
      </div>
      <div>
        가격 : <input type='text' name='price' value={data.price} onChange={e => handleData(e)}/>
      </div>
      <div>
        <button type='button' onClick={() => {
          dispatch(addCart(data))
        }}>등록</button>
      </div>
    </div>
  )
}

export default Test3