package practice;

import java.util.Scanner;

public class 문제_1_7to9 {
  public static void main(String[] args) {
    //문제 7번
    test7("집에", "갈래");
    //문제 8번
    Scanner sc = new Scanner(System.in);
    System.out.print("정수를 입력하시오 : ");
    test8(sc.nextInt());
    //문제 9번
    System.out.print("정수를 두 개 입력하시오 :");
    test9(sc.nextInt(), sc.nextInt());

  }
  public static void test7(String a, String b){
    System.out.println(a + b);
  }
  public static void test8(int num1){
    System.out.println(num1);
  }
  public static void test9(int num2, int num3){
    System.out.println(num2 + num3);
  }
}
