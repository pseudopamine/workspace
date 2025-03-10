import './App.css'
import Footer from './components/Footer'
import SideMenu from './components/SideMenu'
import StateTest from './components/StateTest';

function Header(){
  return (
    <div>
      <div>header</div>
    </div>
  )
}

function App() {
  let title = '오늘의 제목';
  let arr = [1, 2, 3]
  let person = {
    name : 'kim',
    age : 20
  };
  //num의 값이 3 이상이면 div 보이게 해라
  let num = 5;

  return (
    <>
    <StateTest />
    {
      num >= 3 ? <div>3 이상입니다</div> : ''
    }
    <Header></Header>
    <Header />
      <div className='title'>{title}</div>
      <div>{10 + 20}</div>
      <div>{arr[0]}</div>
      <div>{person.name}</div>
      <div>hello</div>
      <SideMenu />
      <Footer />
    </>
  )
}

export default App
