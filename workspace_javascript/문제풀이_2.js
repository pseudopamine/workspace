
//문제 1번
const emp = {
  name : '안중근', 
  dept : '개발부', 
  job : '부장', 
  salary : 100000
}

//문제 2번
const test1 = (emp) => {
  console.log(emp.dept === '개발부' ? emp.salary : '부서가 잘못되었습니다.');
}
test1(emp);

//문제 3번

const test2 = function (){
  
}



///////////////////////////////////////////////////////////////////////////////

const data = [
  {
      name : 'kim',
      age : 20,
      scores : [50, 60, 70]
  }, 
  {
      name : 'lee',
      age : 30
  }, 
  {
      name : 'hong',
      age : 40
  }
];

console.log(data[0].scores[1]); //kim 출력



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


//문제 4번
const foodName = (arr) => {
  for(let i = 0 ; i < arr.length ; i++){
    console.log(arr[i].name);
  }
}

foodName(foodList);

//문제 5번
let sum = 0;
const test5 = function(arr){
  for(let i = 0 ; i < arr.length ; i++){
    if(arr[i].type === '한식'){
      sum += arr[i].price;
    }
  }return sum;
}

const result2 = test5(foodList);
console.log(result2);

//문제 6번
let newArr = [];
function test6(arr){
  for(let i = 0 ; i < arr.length ; i++){
    if(arr[i].material.length >= 3){
      newArr = newArr.concat(arr[i].material);
    }
  }return newArr;
}

const result3 = test6(foodList);
console.log(result3);

//문제 7번
const test7 = (foodlist, foodname) => {
  for(let i = 0 ; i < foodlist.length ; i++){
    if(foodlist[i].name){

    }
  }
}