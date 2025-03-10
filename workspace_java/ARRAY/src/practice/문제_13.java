package practice;

public class 문제_13 {
  public static void main(String[] args) {
    //13. 길이가 100인 배열을 만들고 각 요소의 값을 1 ~ 100으로 변경하자.
    //    그 후 배열에 들어간 수 중 소수만 출력해보자.
    //    (소수란 1과 자신의 수로만 나누어 떨어지는 수이다. ex> 2,3,5,7..)

    int[] arr = new int[100];

    for(int i = 0 ; i < arr.length ; i++){
      arr[i] = i + 1;
      if((arr[i] / 1 == arr[i]) && arr[i] % arr[i] == 0){
        System.out.print(arr[i] + " ");
      }
    }
  }
}
