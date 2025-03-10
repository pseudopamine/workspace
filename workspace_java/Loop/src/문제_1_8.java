import java.util.Scanner;

public class 문제_1_8 {
  public static void main(String[] args) {

    //키보드로 정수를 입력받아 1부터 입력받은 수까지 짝수의 갯수를 구하시오.

    Scanner sc = new Scanner(System.in);

    int num;
    int each = 0;

    System.out.print("정수를 입력하세요 : ");
    num = sc.nextInt();

    while(num > 1){
      if(num % 2 == 0){
        each++;
      }
      num--;
    }
    System.out.println(each + "개 입니다.");
  }
}
