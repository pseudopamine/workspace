import { Route, Routes } from 'react-router-dom'
import './App.css'
import MemberList from './components/MemberList'
import MemberDetail from './components/MemberDetail'
import MemberInsert from './components/MemberInsert'
import MemberUpdate from './components/MemberUpdate'

function App() {
  return (
    <>
      <div>
        <h1>회원 관리 프로그램</h1>


        <Routes>
          {/* 회원 목록 페이지 */}
          {/* '' : localhost:5173 */}
          {/* '/' : localhost:5173/ */}
          <Route path='' element={ <MemberList /> }/>
          {/* 회원 상세 정보 페이지 */}
          <Route path='/detail/:memId' element={ <MemberDetail /> }/>
          {/* 회원 등록 페이지 */}
          <Route path='/insert' element={ <MemberInsert /> }/>
          {/* 회원 수정 페이지 */}
          <Route path='/update/:memId' element={ <MemberUpdate /> }/>
        </Routes>
      </div>
    </>
  )
}

export default App
