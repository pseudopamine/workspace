package practice;

public class 문제_6 {
  public static void main(String[] args) {
    //6. 정수를 10개 저장할 수 있는 배열을 생성하고 모든 요소에 3을 넣어라.
    //   그리고 3이 잘 들어갔는지 출력하여 확인해보아라.

    int[] arr = new int[10];

    for(int i = 0 ; i < arr.length ; i++){
      arr[i] = 3;
      System.out.print(arr[i] + " ");
    }
  }
}
