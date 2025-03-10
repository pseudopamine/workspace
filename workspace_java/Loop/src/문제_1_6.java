public class 문제_1_6 {
  public static void main(String[] args) {

    //1~100까지 숫자 중 3의 배수인 수의 갯수를 구하시오.

    int num = 1;
    int each = 0;

    while(num < 101){
      if(num % 3 == 0){
        each++;
      }
      num++;
    }
    System.out.println("3의 배수는 " + each + "개 입니다.");
  }
}
