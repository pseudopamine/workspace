package practice;

public class 문제_15 {
  public static void main(String[] args) {
    //15. 1,2,3을 초기값으로 갖는 배열 arr1과 4,5,6을 초기값으로 갖는 arr2를 만든다.
    //    그 후 새로운 배열 newArr을 만들어 arr1과 arr2의 모든 값을 복사해보자.
    //    복사 후 newArr은 1,2,3,4,5,6을 가져야 한다

    int[] arr1 = {1, 2, 3};
    int[] arr2 = {4, 5, 6};

    //위 두 배열의 모든 요소의 값을 저장할 배열 생성
    int[] newArr = new int[arr1.length + arr2.length];

    //arr1 배열의 모든 요소를 newArr 배열에 저장
    for(int i = 0 ; i < arr1.length ; i++){
      newArr[i] = arr1[i];
    }

    //arr2 배열의 모든 요소를 newArr 배열에 저장
    for(int i = 0 ; i < arr2.length ; i++){
      newArr[i + arr1.length] = arr2[i];
    }


    for(int i = 0 ; i < newArr.length ; i++){
      System.out.print(newArr[i] + " ");
    }

  }
}
