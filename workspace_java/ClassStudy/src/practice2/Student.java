package practice2;

import java.util.Scanner;

public class Student {

  Scanner sc = new Scanner(System.in);

  boolean run = true;
  int studentNum = 0;
  String[] name = new String[3];
  int[] age = new int[3];
  String[] phone = new String[3];
  String[] score = new String[3];


  int[] scores = new int[studentNum];


  public void setName(String name){
    for(int i = 0 ; i < this.name.length ; i++){
      if(name.equals(this.name[i])){
        this.name[i + 1] = name;
      }
      else{
        this.name[i] = name;
      }
    }
  }


  public String[] getName(){
    return name;
  }



  public void setAge(int age){
    for(int i = 0 ; i < this.age.length ; i++){
      if(name.equals(getName())){
        this.age[i + 1] = age;
      }
      else{
        this.age[i] = age;
      }
    }
  }
  public void setPhone(String phone){
    for(int i = 0 ; i < this.phone.length ; i++){
      if(name.equals(getName())){
        this.phone[i + 1] = phone;
      }
      else{
        this.phone[i] = phone;
      }
    }
  }

  public void setScore(String score) {
    for (int i = 0; i < this.score.length; i++) {
      if (name.equals(getName())) {
        this.score[i + 1] = score;
      } else {
        this.score[i] = score;
      }
    }
  }

  /*public String[] getName(){
    for(int i = 0 ; i < this.name.length ; i++){
      if(name.equals(this.name[i])){
        this.name[i + 1] = name;
      }
      else{
        this.name[i] = name;
      }
    }
    return name;
  }*/






  public void studentInfo(){
    System.out.println("학생관리 프로그램을 실행합니다.");
    while (run) {
      System.out.println("1)학생등록 2)학생정보변경(연락처) 3)학생정보출력 4)모든학생정보출력 5)프로그램 종료 :");

      int selectNo = sc.nextInt();

      switch(selectNo){

        case 1:
          System.out.println("학생 등록을 시작합니다. 학생 정보를 입력하세요. ");
          System.out.print("이름 : ");
          setName(sc.next());
          System.out.print("나이 : ");
          setAge(sc.nextInt());
          System.out.println("연락처 : ");
          setPhone(sc.next());
          System.out.println("학점 : ");
          setScore(sc.next());
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
