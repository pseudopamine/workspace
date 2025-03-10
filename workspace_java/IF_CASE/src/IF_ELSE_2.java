import java.util.Scanner;

public class IF_ELSE_2 {
  public static void main(String[] args) {
    //키보드로 하나의 정수를 입력받아,
    //그 수가 3의 배수이면 '3의 배수입니다'를 출력하고
    //조건에 맞지 않으면 '3의 배수가 아닙니다'를 출력하세요
    Scanner sc = new Scanner(System.in);
    int num;

    System.out.print("숫자를 입력하세요 : ");
    num = sc.nextInt();

    if(num % 3 == 0){
      System.out.println("'3의 배수 입니다'");
    }
    else{
      System.out.println("'3의 배수가 아닙니다'");
    }
  }
}
