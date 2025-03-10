import java.util.Scanner;

public class 문제_1_11 {
  public static void main(String[] args) {

    //키보드로 두 정수를 입력받아 입력받은 두 정수 사이의 모든 정수의 합을 출력하여라.

    Scanner sc = new Scanner(System.in);

    int num1, num2;
    System.out.print("정수를 두 개 입력하시오 : ");
    num1 = sc.nextInt();
    num2 = sc.nextInt();

    int min = num1 < num2 ? num1 : num2;
    int max = num1 > num2 ? num1 : num2;

    int i = min + 1;
    int sum = 0;

    while(i < max){
      sum += i;
      i++;
    }
    System.out.println(sum);
  }
}
