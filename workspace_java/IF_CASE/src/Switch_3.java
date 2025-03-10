public class Switch_3 {
  public static void main(String[] args) {
    /*
    * 0~100사이의 임의의 정수를 변수에 저장하고
    * 90 <= x <= 100 -> 문자열 'A' 출력
    * 80 <= x <= 89 -> 문자열 'B' 출력
    * 70 <= x <= 79 -> 문자열 'C' 출력
    * x <= 69 -> 문자열 'D' 출력
    *
    * */

    int x = 56;

    switch(x / 10){
      case 10 :
      case 9 :
        System.out.println("A");
        break;
      case 8 :
        System.out.println("B");
        break;
      case 7 :
        System.out.println("C");
        break;
      default :
        System.out.println("D");
    }

  }
}
