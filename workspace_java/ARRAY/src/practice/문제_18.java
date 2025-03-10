package practice;

public class 문제_18 {
  public static void main(String[] args) {
    // 18. 1, 5, 3, 8, 2를 초기값으로 갖는 배열을 생성하고 해당 배열에서 최대값을 출력하여라.

    int[] arr = {4, 463, 3547, -76, 7437, 74238, 834};

    //1. 0번째 요소를 가장 큰 값이라 가정하고 시작.
    //2. max라는 변수를 만들고, 거기에 0번째 요소를 저장
    //3. 배열의 크기만큼 반복한다.
    //4. 반복을 하며 배열 요소의 값과 max 변수의 값을 비교
    //5. 만약 max값보다 배열 요소의 값이 크다면 배열 요소의 값을 max 변수에 저장한다.


    int max = arr[0];

    //for문
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] > max) {
        max = arr[i];
      }
    }
    System.out.println(max);
  }
}


    //for-each문
    /*for( int num  : arr ){
      if(num > max){
        max = num;
      }
    }
    System.out.println(max);
  }
}*/