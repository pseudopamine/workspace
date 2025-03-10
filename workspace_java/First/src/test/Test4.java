package test;

import java.util.Scanner;

public class Test4 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int num;
    int clapCnt = 0;

    System.out.print("1~999 사이의 정수를 입력하시오>>");
    num = sc.nextInt();

    int hundreds = num / 100;
    int tens = num / 10;
    int ones = num % 10;


    /*if (ones == 3 || ones == 6 || ones == 9) {
      if (tens == 3 || tens == 6 || tens == 9)
        System.out.print(num + " : 박수 2번");
      else
        System.out.print(num + " : 박수 1번");
    }
    else {
      if(tens == 3 || tens == 6 || tens == 9)
        System.out.print(num + " : 박수 1번");
    }*/



    //만약 십의 자리 수가 3, 6, 9면 박수 수를 1 증가
    if(tens == 3 || tens == 6 || tens == 9){
      clapCnt = clapCnt + 1;
    }

    //만약 일의 자리 수가 3, 6, 9면 박수 수를 1 증가
    if(ones == 3 || ones == 6 || ones == 9){
      clapCnt = clapCnt + 1;
    }

    //만약 백의 자리 수가 3, 6, 9면 박수 수를 1 증가
    if(hundreds == 3 || hundreds == 6 || hundreds == 9){
      clapCnt = clapCnt + 1;
    }




    //clapCnt = 0, 1, 2
    //0이면 박수 없음
    //1이면 박수짝
    //2이면 박수짝짝

    switch (clapCnt){
      case 0:
        System.out.println("박수 0번");
        break;
      case 1:
        System.out.println("박수 1번");
        break;
      case 2:
        System.out.println("박수 2번");
        break;
      case 3:
        System.out.println("박수 3번");
    }

    }
  }

