package practice3;

import java.util.Scanner;

public class StudentManager {
  private Scanner sc;
  private Student[] students;
  private int index; //students 배열의 요소를 지정



  public StudentManager(){
    sc = new Scanner(System.in);
    students = new Student[3];
    index = 0;
  }

  //학생 등록 기능
  public void regStudent(){
    System.out.println("학생 등록을 시작합니다. 학생 정보를 입력하세요.");
    System.out.print("이름 : ");
    String name = sc.next();
    System.out.print("나이 : ");
    int age = sc.nextInt();
    System.out.print("연락처 : ");
    String tel = sc.next();
    System.out.print("학점 : ");
    String grade = sc.next();

    //입력받은 정보로 학생을 생성
    Student stu = new Student(name, age, grade, tel);

    //생성한 학생을 students 배열에 저장한다.
    students[index++] = stu;
  }

  //학생 정보 변경 기능(연락처)
  public void setTelInfo(){
    System.out.println("학생의 연락처를 변경합니다.");
    System.out.print("변경 학생 : ");
    String name = sc.next();
    System.out.print("연락처 : ");
    String tel = sc.next();
    for(int i = 0 ; i < index ; i++){
      if(students[i].getName().equals(name)){
        students[i].setTel(tel);
      }
    }
    System.out.println("변경이 완료되었습니다.");
  }


  //학생 정보 출력(한 명)
  public void printStuInfo(){
    System.out.print("정보를 열람할 학생 : ");
    String name = sc.next();
    System.out.println("요청하신 학생의 정보입니다.");
    for(int i = 0 ; i < index ; i++){
      if(students[i].getName().equals(name)){
        students[i].printStudent();
      }
    }
  }

  //모든 학생 정보 출력
  public void printStuInfoAll(){
    System.out.println("모든 학생의 정보입니다. 현재 총 학생 수는 " + index + "명입니다");
    for(int i = 0 ; i < index ; i++){
      students[i].printStudent();
      System.out.println();
    }

  }
}
