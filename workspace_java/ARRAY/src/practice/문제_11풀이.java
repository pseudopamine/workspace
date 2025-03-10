package practice;

import java.util.Arrays;
import java.util.Scanner;

// 11. (난이도 상) 간단한 성적처리 시스템을 만들어보자. 국어, 영어, 수학점수를 입력받아
//     각 과목에 대한 점수 및 총점, 평균을 출력하는 프로그램을 만들어보자.
//     반드시 배열을 사용하도록 한다.
public class 문제_11풀이 {
  public static void main(String[] args) {
    //----------------- 변수 -----------------//
    Scanner sc = new Scanner(System.in);
    //0번 요소: 국어, 1: 영어, 2: 수학, 3: 총점.
    int[] scores = new int[4];
    //과목명을 저장하고 있는 배열
    String[] subjects = {"국어", "영어", "수학"};
    //평균
    double avg = 0.0;

    //----------------- 점수입력 -----------------//
    //과목수만큼 반복 (총점수 포함되어 있기에 -1)
    for(int i = 0 ; i < scores.length - 1 ; i++){
      //0 ~ 100점 사이의 값을 입력받을 때까지 반복
      while(true){
        System.out.print(subjects[i] + " 점수 입력 : ");
        scores[i] = sc.nextInt();

        //입력받은 점수가 0~100 사이면 반복문 벗어남
        if(scores[i] >= 0 && scores[i] <= 100){
          break; //가장 가까운 반복문 벗어난다.
        }
      }
    }

    //----------------- 총점, 평균 계산 -----------------//
    //과목수만큼 반복해서 총점을 계산
    for(int i = 0 ; i < scores.length - 1 ; i++){
      scores[scores.length - 1] = scores[scores.length - 1] + scores[i];
    }

    //평균 = (총점 / 과목수)
    avg = scores[scores.length - 1] / (double)(scores.length - 1);

    //----------------- 결과 출력 -----------------//
    System.out.println();
    System.out.println("***** 입력한 정보로 점수 정보를 출력합니다 *****");

    //각 과목의 점수 출력
    for(int i = 0 ; i < scores.length - 1 ; i++){
      System.out.println(subjects[i] + " 점수 = " + scores[i]);
    }
    //총점 및 평균 출력
    System.out.println("총점 = " + scores[scores.length - 1]);
    System.out.println("평균 = " + avg);








  }
}
