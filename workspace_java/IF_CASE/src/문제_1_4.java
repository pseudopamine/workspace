import java.util.Scanner;

public class 문제_1_4 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int num;

    System.out.print("정수 입력 : ");
    num = sc.nextInt();

    if(num % 3 == 0){
      System.out.println("'3의 배수입니다'");
    }
  }
}
