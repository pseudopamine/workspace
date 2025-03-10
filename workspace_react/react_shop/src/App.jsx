import { Route, Routes } from 'react-router-dom'
import './App.css'
import './common.css'
import ItemInsert from './components/ItemInsert'
import ItemList from './components/ItemList'
import ItemDetail from './components/ItemDetail'
import OrderInsert from './components/OrderInsert'
import Header from './components/Header'
import OrderList from './components/OrderList'

function App() {
  //falsy : false로 판단하는 내용
  //null, undefined, 0, ''

  //truthy : falsy한 데이터 빼고 전부. 'a', 'aaa', '235' ...

  // const data1 = 'java';
  // const data2 = 'python';
  // console.log(data1 && data2)  //python 출력
  // console.log(data1 || data2)  //java 출력



  // const a = true;
  // const b = false;
  // console.log(a && b)  //false


  return (
    <>
    <div className='container'>
      <Header />
      <Routes>
        {/* 상품 정보 */}
        <Route path='' element={ <ItemList /> }/>

        {/* 상품 등록 */}
        <Route path='/insert' element={ <ItemInsert /> }/>

        {/* 상품 상세 조회 */}
        <Route path='/detail/:itemNum' element={ <ItemDetail /> }/>

        {/* 주문 등록 하기 */}
        <Route path='/order' element={ <OrderInsert /> }/>

        {/* 주문 목록 페이지 */}
        <Route path='/order-list' element={ <OrderList /> } />
      </Routes>
    </div>
    </>
  )
}

export default App
