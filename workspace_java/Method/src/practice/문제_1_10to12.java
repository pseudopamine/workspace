package practice;

import java.util.Scanner;

public class 문제_1_10to12 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    //문제 10번
    //test10() 메서드의 '인자'로 5가 전달되었다.
    test10(5);
    //문제 11번
    test11(17, 2);
    //문제 12번
    test12(11);


  }
  public static void test10(int num1){
    /*if(num1 % 2 == 0){
      System.out.println("짝수입니다.");
    }
    if(num1 % 2 != 0){
      System.out.println("홀수입니다.");
    }*/
    System.out.println(num1 % 2 == 0 ? "짝수입니다." : "홀수입니다."); //삼항연산자
  }
  public static void test11(int num2, int num3){
    /*if(num2 % 2 == 0 && num3 % 2 == 0){
      System.out.println("두 수는 짝수입니다.");
    }
    else if((num2 + num3 % 2 != 0) && num2 != num3){
      System.out.println("한 수만 짝수입니다.");
    }
    else if(num2 % 2 != 0 && num3 % 2 != 0){
      System.out.println("두 수 모두 홀수입니다.");
    }*/
    if(num2 % 2 == 0 && num3 % 2 == 0){
      System.out.println("두 수는 짝수입니다.");
    }
    else if(num2 % 2 != 0 && num3 % 2 != 0){
      System.out.println("두 수는 홀수입니다.");
    }
    else{
      System.out.println("한 수만 짝수입니다.");
    }
  }
  public static void test12(int num4){
    /*int[] arr = new int[num4 + 1];
    for(int i = 0 ; i < num4 + 1 ; i++){
      arr[i] = i;
      System.out.print(arr[i] + " ");
    }*/
    for(int i = 0 ; i < num4 + 1 ; i++){
      System.out.print(i + " ");
    }



  }
}
