/*
* 키보드로 국어, 영어, 수학 점수를 입력받은 후
* 입력받은 국어, 영어, 수학 점수 및 총점, 평균을 출력
* 단, 국어, 영어, 수학 점수는 정수만 저장된다고 가정
*
* */


//문제를 해결하기 위해 필요한 변수 생각!!

import java.util.Scanner;

public class 성적프로그램_1 {
  public static void main(String[] args) {

    //키보드 입력을 위한 변수 생성


    /*System.out.print("국어 점수를 입력하세요 : ");
    int korScore = sc.nextInt();

    System.out.print("영어 점수를 입력하세요 : ");
    int engScore = sc.nextInt();

    System.out.print("수학 점수를 입력하세요 : ");
    int matScore = sc.nextInt();

    System.out.println("국어, 영어, 수학 점수의 총점은 " + (korScore + engScore + matScore) + "점 입니다. ");

    System.out.println("국어, 영어, 수학의 평균 점수는 " + ((korScore + engScore + matScore)/3) + "점 입니다. ");*/

    int korScore;
    int engScore;
    int matScore;
    int totalScore;
    double avg;

    Scanner sc = new Scanner(System.in);

    System.out.print("국어 점수 : ");
    korScore = sc.nextInt();

    System.out.print("영어 점수 : ");
    engScore = sc.nextInt();

    System.out.print("수학 점수 : ");
    matScore = sc.nextInt();


    totalScore = korScore + engScore + matScore;

    avg = totalScore / 3.0;


    System.out.println("국어 점수 = " + korScore);
    System.out.println("영어 점수 = " + engScore);
    System.out.println("수학 점수 = " + matScore);
    System.out.println("총점 = " + totalScore);
    System.out.println("평균 점수 = " + avg);

  }
}
