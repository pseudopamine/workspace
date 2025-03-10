import java.util.Scanner;

public class 정수입력받기_2 {
  public static void main(String[] args) {
    //두 정수를 키보드로 입력받아,
    //입력받은 두 수의 합을 출력

    //키보드 입력을 위한 변수 생성
    Scanner sc = new Scanner(System.in);


    System.out.print("정수를 입력하세요 : ");
    int a = sc.nextInt();

    System.out.print("정수를 하나 더 입력하세요 : ");
    int b = sc.nextInt();

    System.out.println("입력받은 두 수 의 합은 = " + (a + b) + " 입니다. ");



  }
}
