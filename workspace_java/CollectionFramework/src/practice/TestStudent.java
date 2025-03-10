package practice;

import java.util.ArrayList;
import java.util.List;

public class TestStudent {
  public static void main(String[] args) {
    /*Student student1 = new Student("장만월", 98, 73);
    Student student2 = new Student("고애신", 96, 40);
    Student student3 = new Student("최유진", 99, 100);*/

    List<Student> list = new ArrayList<>();

    list.add(new Student("장만월", 98, 73));
    list.add(new Student("고애신", 96, 90));
    list.add(new Student("최유진", 55, 99));


    //문제 10-1번 : 리스트에 저장된 모든 정보를 출력해보세요.
    for(int i = 0 ; i < list.size() ; i++){
      System.out.println(list.get(i));
    }
    System.out.println();

    //문제 10-2번 : 총점이 150점 이상인 학생의 모든 정보를 출력
    for(int i = 0 ; i < list.size() ; i++){
      if(list.get(i).getTotalScore() >= 150){
        System.out.println(list.get(i));
      }
    }
    System.out.println();

    //문제 10-3번 : 모든 학생에 대한 평균 점수를 출력하세요
    double sum = 0.0;
    for(int i = 0 ; i < list.size() ; i++){
      sum = (double)sum + list.get(i).getKorScore();
    }
    System.out.print("국어 평균 점수는 " + (double)sum / list.size() + "점 입니다.");
    System.out.println();
    double sum1 = 0.0;
    for(int i = 0 ; i < list.size() ; i++){
      sum1 = (double)sum1 + list.get(i).getEngScore();
    }
    System.out.print("영어 평균 점수는 " + (double)sum1 / list.size() + "점 입니다.");
    System.out.println();
    System.out.println();

    //문제 10-4번 : 총점이 1등인 학생의 모든 정보를 출력해보세요
    int maxIndex = 0;
    for(int i = 0 ; i < list.size() ; i++){
      if(list.get(i).getTotalScore() > list.get(maxIndex).getTotalScore()){
        maxIndex = i;
      }
    }
    System.out.println(list.get(maxIndex));


    /*for(int i = 0 ; i < list.size() ; i++){
      int max = list.get(0).getTotalScore();
      if(max < list.get(i).getTotalScore()){
        max = list.get(i).getTotalScore();
        System.out.println(list.get(i));
        break;
      }
    }*/
  }
}
