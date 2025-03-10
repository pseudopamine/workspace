import { Route, Routes } from 'react-router-dom'
import './App.css'
import BoardList from './component/BoardList'
import BoardInsert from './component/BoardInsert'
import BoardDetail from './component/BoardDetail'
import BoardUpdate from './component/BoardUpdate'
import InputTest from './component/InputTest'
import QueryString from './component/QueryString'
import UseEffectTest from './component/UseEffectTest'

function App() {
  return (
    <div className='container'>
      {/* <InputTest /> */}
      <h1>게시판 프로젝트</h1>

      <Routes>
        {/* 전체 목록 조회 */}
        <Route path='' element={ <BoardList /> } />
        {/* 새 글 등록 */}
        <Route path='/insert' element={ <BoardInsert /> } />
        {/* 특정 글 상세 조회 */}
        <Route path='/detail/:boardNum' element={ <BoardDetail /> }/>
        {/* 특정 글 수정하기 */}
        <Route path='/update/:boardNum' element={ <BoardUpdate /> }/>
        {/* 쿼리 스트링 연습 페이지 */}
        <Route path='/test' element={ <QueryString /> }/>
      </Routes>



    
    
    
    </div>
  )
}

export default App
