package practice;

public class 문제_10 {
  public static void main(String[] args) {
    //10. 1, 2, 3, 4, 5, 6 ,7 ,8 로 초기화된 배열을 만들고, 배열의 요소 중 짝수의 개수를 구하는 프로그램을 만들어라.

    int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
    int evenCnt = 0;

    //for-each문 사용
    for( int e : arr){
      if(e % 2 == 0){
        evenCnt++;
      }
    }
    System.out.println("배열의 요소 중 짝수는 " + evenCnt + "개 입니다.");


    System.out.println();
    //기본 for문 사용
    for(int i = 0 ; i < arr.length ; i++){
      if(arr[i] % 2 == 0)
        evenCnt++;
    }
    System.out.println("배열의 요소 중 짝수는 " + evenCnt + "개 입니다.");
  }





}
