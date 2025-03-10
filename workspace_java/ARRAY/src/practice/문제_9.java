package practice;

import java.util.Scanner;

public class 문제_9 {
  public static void main(String[] args) {
    //  9. 수를 5개 저장할 수 있는 배열을 만들고, scanner를 통해 입력받은 값을 하나씩 배열의 요소에 저장해보자.
    //     만약 키보드로 1 2 3 4 5를 입력했다면 배열의 요소에 각각 1 2 3 4 5 가 저장 되어야 한다.
    //     출력을 통해 결과를 확인해 보아라.

    Scanner sc = new Scanner(System.in);

    int[] arr = new int[5];
    int a;

    for(int i = 0 ; i < arr.length ; i++){
      System.out.print("배열값 지정 : ");
      a = sc.nextInt();
      arr[i] = a;
      System.out.println("arr[" + i + "]" + "에 지정된 배열값 : " + arr[i]);
    }


  }
}
