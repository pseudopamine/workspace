
const arr1 = [1, 2, 3];
const arr2 = [4, 5];

//[1, 4, 5, 2, 3]
const arr4 = [1, ...arr2, 2, 3]

//spread연산자 : ... 박스를 벗겨!!
const arr3 = [1, 2, 3, ...arr2]


//[1, 2, 3, 4, 5]
arr1[3] = arr2; //XXXXXX아님 [1, 2, 3, [4, 5]]
console.log(arr1);
arr1.push(arr2); //[1, 2, 3, [4, 5]]

const person = {
  name : 'kim',
  age : 20
};

const score = {
  korScore : 80,
  mathScore : 100
};

const student = {
  ...person,
  ...score
};