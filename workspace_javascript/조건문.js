
//alert(123);

let num1 = 5;

//num1이 3 이상이면 '3 이상입니다'를 출력하는 코드 작성
if(num1 >= 3){
  console.log('3 이상입니다.');
}

//1부터 10까지 출력

for(let i = 1 ; i < 11 ; i++){
  console.log(i);
}

//자바스크립트는 배열 생성 시 크기가 지정 X
//크기가 알아서 늘어남
let arr = []; //빈 배열 생성
let arr2 = [1, 2, 3];
for(let i = 0 ; i < arr2.length ; i++){
  console.log(arr2[i]);
}

console.log(arr2);

//for...of 문법
for(  let e of arr2  ){
  console.log(e);
}

