import java.util.Scanner;

public class IF_2 {
  public static void main(String[] args) {
    //키보드로 정수를 하나 입력받아,
    //해당 정수가 짝수일 때만 '참'이라는 문자열을 출력하는 코드

    Scanner sc = new Scanner(System.in);
    int num;

    System.out.print("정수 입력 : ");
    num = sc.nextInt();


    if(num % 2 == 0){
      System.out.println("참");

    }


  }
}
