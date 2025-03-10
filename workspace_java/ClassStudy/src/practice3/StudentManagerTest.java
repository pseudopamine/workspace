package practice3;

import javax.swing.*;
import java.util.Scanner;

public class StudentManagerTest {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    //while문 실행 여부값을 갖고있는 변수
    boolean isRunning = true;

    //학생 관리 기능이 있는 StudentManager 클래스에 대한 객체 생성
    StudentManager manger = new StudentManager();


    System.out.println("학생관리 프로그램을 실행합니다.");

    while(isRunning){
      System.out.print("1)학생등록  2)학생정보변경(연락처) 3)학생정보출력 4)모든학생정보출력  5)프로그램 종료 : ");
      int option = sc.nextInt();

      switch (option){
        case 1:
          manger.regStudent();
          break;
        case 2:
          manger.setTelInfo();
          break;
        case 3:
          manger.printStuInfo();
          break;
        case 4:
          manger.printStuInfoAll();
          break;
        case 5:
          System.out.println("프로그램을 종료합니다.");
          isRunning = false;
          break;
        default:
          System.out.println("1 ~ 5번 중 하나의 정수를 입력하시오.");
      }

      //break
      //1. switch case break
      //2. 가장 가까운 반복문을 벗어난다


    }
  }
}
