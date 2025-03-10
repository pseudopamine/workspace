import java.util.Scanner;

public class 문제_1_2 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int num;

    System.out.print("정수 입력 : ");
    num = sc.nextInt();

    if(num % 2 == 0){
      System.out.println("'짝수입니다'");
    }
    if(num % 2 != 0){
      System.out.println("'홀수입니다'");
    }

  }

}
