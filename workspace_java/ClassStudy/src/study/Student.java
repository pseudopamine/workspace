package study;

//학생을 표현할 설계도
public class Student {
  String name; //학생 이름 데이터
  int age; //학생 나이
  int score; //점수

  //이름을 변경하는 메서드
  public void setName(String name1){
    name = name1;
  }
  //나이를 변경하는 메서드
  public void setAge(int age1){
    age = age1;
  }
  //점수를 변경하는 메서드
  public void setScore(int score1){
    score = score1;
  }

  //이름, 나이, 점수를 모두 변경하는 메서드
  public void setAll(String name1, int age1, int score1){
    name = name1;
    age = age1;
    score = score1;
  }


  //학생의 정보를 출력하는 기능
  public void printInfo(){
    System.out.println("이름 : " + name);
    System.out.println("나이 : " + age);
    System.out.println("점수 : " + score);

  }

}
