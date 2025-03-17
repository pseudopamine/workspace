
export const data = [
  {
    id : 1,
    item : '달걀 한 판',
  },
  {
    id : 2,
    item : '라면 한 묶음',
  }, 
  {
    id : 3,
    item : '햇반 한 박스',
  } 
];


//cartList에서 id가 2인 데이터 찾기
// for(const e of cartList){
//   if(e.id === 2){
//     //찾았다
//   }
// }

//data에서 id가 2인 데이터 찾기
//e : 배열에 있는 데이터 하나하나
//return 생략 가능
//find : return에 작성한 조건문과 일치하는 데이터만 리턴해준다. 
const aa = data.find((e) => {return e.id === 2})
const aaa = data.find(e => e.id === 2)

//filter : return문에 작성한 조건과 일치하는 데이터는 걸러낸다. 
const aaaa = data.filter((e) => {return e.id === 2})
const aaaaa = data.filter(e => e.id === 2)
data.map(() => {})
