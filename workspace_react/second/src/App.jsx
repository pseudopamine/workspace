import "./App.css";

//리턴문 안의 html은 반드시 하나의 태그에 감싸져야 한다.
//컴포넌트는 반드시 대문자로 시작!! (소문자로 쓰면 js의 함수로 인식해버림)
function Header() {
  return (
    <div>
      <h3>header</h3>
    </div>
  );
}

const Footer = () => {
  return (
    <div>
      <h3>footer</h3>
    </div>
  );
};

function App() {
  return (
    <>
      <Header />
      <div>
        <h3>content</h3>
      </div>
      <Footer />
    </>
  );
}

export default App;
