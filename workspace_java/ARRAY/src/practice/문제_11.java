package practice;

import java.util.Scanner;

public class 문제_11 {
  public static void main(String[] args) {
    // 11. (난이도 상) 간단한 성적처리 시스템을 만들어보자. 국어, 영어, 수학점수를 입력받아
    //     각 과목에 대한 점수 및 총점, 평균을 출력하는 프로그램을 만들어보자.
    //     반드시 배열을 사용하도록 한다.

    Scanner sc = new Scanner(System.in);

    int[] arr = new int[3];

    /*int korScore;
    int engScore;
    int matScore;

    System.out.print("국어 점수 입력 : ");
    korScore = sc.nextInt();
    arr[0] = korScore;

    System.out.print("영어 점수 입력 : ");
    engScore = sc.nextInt();
    arr[1] = engScore;

    System.out.print("수학 점수 입력 : ");
    matScore = sc.nextInt();
    arr[2] = matScore;*/


    int total = 0;

    int score;

    for(int i = 0 ; i < arr.length ; i++){
      System.out.print("점수 입력 : ");
      score = sc.nextInt();
      arr[i] = score;
    }

    for(int i = 0 ; i < arr.length ; i++){
      total = total + arr[i];
    }
    System.out.println("국어 점수 : " + arr[0]);
    System.out.println("영어 점수 : " + arr[1]);
    System.out.println("수학 점수 : " + arr[2]);

    System.out.print("총점 : " + total + "점");

    System.out.println();

    double avg = (double)total / arr.length;

    System.out.print("평균 점수 : " + avg + "점");
  }
}






















