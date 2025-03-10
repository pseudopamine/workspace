//문제 1번

function test1(arr){
  for(let i = 0 ; i < arr.length ; i++){
    if(arr[i] % 2 === 0){
      console.log(arr[i]);
    }
  }
}

const test1_data = [1, 2, 3, 4, 5, 6, 7, 8, 9];

test1(test1_data);


//문제 2번
const test2 = function (arr1, arr2){
  //첫번째 배열의 합과 두번째 배열의 합을 합한 뒤 평균
  const sum1 = getArrSum(arr1);
  const sum2 = getArrSum(arr2);
  return (sum1 + sum2) / (arr1.length + arr2.length);
}

//배열의 합을 구하는 함수 만들기
function getArrSum(arr){
  let sum = 0;
  for(const e of arr){
    sum += e;
  }
  return sum;
}
//호출하기
const data1 = [1, 2, 3, 4];
const data2 = [5, 6];
const result = test2(data1, data2);
console.log(result);


// arr1 = [1, 2, 3, 4, 5, 6];
// let sum = 0;
// const test2 = function (arr1){
//   for(let i = 0 ; i < arr1.length ; i++){
//     sum = sum + arr1[i]
//   }
//   console.log(sum / arr1.length)
// }

// const test2_data = [1, 2, 3, 4, 5, 6];

// test2(test2_data);


//문제 3번

const test3 = (arr) => {
  let maxIndex = 0;
  for(let i = 0 ; i < arr.length ; i++){
    if(arr[i].length > maxIndex){
      maxIndex = i;
    }
  }console.log(arr[maxIndex]);
}

const test3_data = ['거북이', '두루미', '학', '고슴도치', '기린', '삼천갑자동방삭'];

test3(test3_data);


//문제 4번
//3의 배수 [3, 6, 9, 12, 15, 18, 21]
//두번째 매개변수는 arr.length = 7

const arr = [];
function test4(s, e){
  for(let i = 0 ; i < e ; i++){
    arr[i] = s * (i + 1);
  }
  return arr;
}

const result1 = test4(3, 7);
console.log(result1);