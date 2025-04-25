import { useSelector } from 'react-redux'
import './App.css'
import Test1 from './components/Test1';
import Test2 from './components/Test2';
import Test3 from './components/Test3';

function App() {
  //store에 저장된 데이터 가져오기
  const data = useSelector((state) => {return state});
  console.log(data);

  const data1 = useSelector(state => state);
  console.log(data1)

  const counter = useSelector(state => state.counter);
  console.log(counter)

  return (
    <>
      <p>ㅠㅠㅠㅠㅠㅠㅠㅠㅠㅠ</p>
      <div>
        {/* <Test1 />
        <Test2 /> */}
        <Test3 />
      </div>
    </>
  )
}

export default App
