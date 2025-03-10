package practice;

import java.util.Scanner;

public class 문제_21 {
  public static void main(String[] args) {
    // 21. 다음은 키보드로부터 학생 수와 학생들의 점수를 입력받아, 최고점수 및 평균 점수를 구하는 프로그램입니다. 실행 결과를 보고, 알맞게 작성하세요.

    Scanner sc = new Scanner(System.in);

    boolean run = true;
    int studentNum = 0;
    int[] scores = new int[studentNum];

    while (run) {
      System.out.println("------------------------------------------------");
      System.out.println("1.학생수 | 2.점수입력 | 3.점수리스트 | 4.분석 | 5.종료");
      System.out.println("------------------------------------------------");
      System.out.print("선택 : ");

      int selectNo = sc.nextInt();

      switch(selectNo){

        case 1:
          System.out.print("학생수> ");
          studentNum = sc.nextInt();
          scores = new int[studentNum]; //배열 scores 값을 선언
          break;

        case 2:
          int score;
          for (int i = 0; i < studentNum ; i++) {
            System.out.print("scores[" + i + "]" + "> ");
            score = sc.nextInt();
            scores[i] = score;
          }
          break;

        case 3:
          for (int i = 0; i < studentNum ; i++) {
            System.out.print("scores[" + i + "]" + ": " + scores[i]);
            System.out.println();
          }
          break;


        case 4:
          int max = scores[0];

          for (int i = 0; i < studentNum ; i++) {
            if (scores[i] > max) {
              max = scores[i];
            }
          }
          System.out.println("최고 점수: " + max);

          int sum = 0;
          double avg = (double)sum / studentNum;

          for (int i = 0; i < studentNum ; i++) {
            sum = sum + scores[i];
            avg = (double) sum / studentNum;
          }
          System.out.println("평균 점수: " + avg);
          break;

        case 5:
        default:
          run = false;
      }

    }
    System.out.println("프로그램 종료");

  }
}
