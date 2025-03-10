package study;

//배열의 각 요소의 값을 읽을 때에는 for-each문을 사용할 수 있다.
public class ForEach {
  public static void main(String[] args) {
    int[] arr1 = {1, 5, 10};


    // for( 반복 될 데이터를 하나씩 저장할 변수 : 반복 돌릴 데이터)
    for(  int e : arr1  ){
      System.out.print(e + " ");
    }
    System.out.println();

    String[] arr2 = {"집에", "매우", "가고", "싶은", "심정이다"};
    for( String e : arr2 ){
      System.out.print(e + " ");
    }
  }
}
