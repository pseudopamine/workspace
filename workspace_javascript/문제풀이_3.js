
const foodList = [
  {
    name : '김치찌개',
    price : 12000,
    type : '한식',
    material : ['김치','두부', '돼지고기']
  }, 
  {
    name : '짜장면',
    price : 8000,
    type : '중식',
    material : ['춘장','밀가루', '양파', '오이']
  }, 
  {
    name : '불고기',
    price : 15000,
    type : '한식',
    material : ['소고기','양파', '대파']
  }, 
  {
    name : '탕수육',
    price : 25000,
    type : '중식',
    material : ['돼지고기','밀가루']
  }
];

console.log(foodList.length)
console.log(foodList)

//문제 4번 : 모든 음식의 이름을 출력하는 함수를 화살표함수로 구현하고 호출하세요.
const test4 = (arr) => {
  for(let i = 0 ; i < arr.length ; i++){
    console.log(arr[i].name);
  }
}

test4(foodList);

//문제 5번 : 한식 음식의 가격의 합을 출력하는 함수를 함수표현식으로 구현하고 호출하세요.
let sum = 0;
const test5 = function(arr){
  for( let food of arr){
    if(food.type === '한식'){
      sum += food.price;
    }
  }return sum;


  // for(let i = 0 ; i < arr.length ; i++){
  //   if(arr[i].type === '한식'){
  //     sum += arr[i].price;
  //   }
  // }return sum;
}

const result2 = test5(foodList);
console.log(`한식 가격의 합은 ${result2}원 입니다.`);

//문제 6번 : 음식재료가 3개 이상인 음식정보만 새로운 배열에 담아 리턴하는 함수를 구현하고 호출하세요.
let newArr = [];
function test6(arr){
  for(let i = 0 ; i < arr.length ; i++){
    if(arr[i].material.length >= 3){
      newArr.push(arr[i]); //concat() :배열에 다른 배열을 추가
    }
  }return newArr;
}

const result3 = test6(foodList);
console.log(result3);

//문제 7번 :첫번째 매개변수로 foodList, 두번째 매개변수로 음식명이 전달되면 전달된 음식명의 모든 요리재료를 출력하는 함수를 화살표함수로 구현하고 호출하세요.  만약, 두번째 매개변수로 전달된 음식명이 없다면 '정보없음'을 출력하세요.
const test7 = (foodList, foodName) => {
  //let isFind = false;
  for(let i = 0 ; i < foodList.length ; i++){
    if(foodList[i].name === foodName){
      console.log(foodList[i].material);
      //isFind = true;
      return;
    }
  }
  console.log('정보없음');

  //못 찾았을 때 출력 기능
  //부정연산자 이용, isFind가 false면 실행, isFind가 true면 실행하지 않음
  //위에 if문이 true일 때 isFind가 true가 됨
  //!true는 false이기 때문에 
  //if(!isFind){
  //console.log('정보없음');
  //}
}
test7(foodList, "된장찌개");


//매개변수로 5보다 큰 숫자가 들어올 때만 '안녕' 출력
function aaa(num){
  if(num > 5){
    console.log('안녕')
  }
}
//매개변수로 5보다 큰 숫자가 들어올 때만 '안녕' 출력
//return 키워드 작성 후 데이터를 작성하지 않으면 return을 만났을 때 함수 종료
function bbb(num){
  if(num < 5){
    return;
  }
  console.log('안녕');
}



