public class 문제_1_7 {
  public static void main(String[] args) {

    //1~100까지 숫자 중 5의 배수인 수를 모두 출력하고, 5의 배수인 수의 갯수도 출력하시오.

    int num = 1;
    int each = 0;

    while(num < 101){
      if(num % 5 == 0){
        each++;
        System.out.print(num + " ");
      }
      num++;
    }
    System.out.println();
    System.out.println("5의 배수는 " + each + "개 입니다.");
  }
}
