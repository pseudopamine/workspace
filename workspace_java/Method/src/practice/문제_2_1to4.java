package practice;

import java.util.Scanner;

public class 문제_2_1to4 {
  public static void main(String[] args) {
    //문제 1번
    int sum = test1(10, 15);
    System.out.println(sum);

    //문제 2번
    Scanner sc = new Scanner(System.in);
    System.out.print("정수를 두 개 입력하시오 : ");
    int sqr = test2(sc.nextInt(), sc.nextInt());
    System.out.println(sqr);
    System.out.println();

    //문제 3번
    int max = test3(453, 73);
    System.out.print(max);
    System.out.println();

    //문제 4번
    String str = test4("집에", "가자");
    System.out.println(str);

  }

  public static int test1(int num1, int num2){
    return num1 + num2;
  }

  public static int test2(int num1, int num2){
    return num1 * num2;
  }

  public static int test3(int num1, int num2){
    /*int max = num1 > num2 ? num1 : num2;
    return max;*/
    return num1 > num2 ? num1 : num2;
  }

  public static String test4(String str1, String str2){
    return str1 + str2;
  }

}
