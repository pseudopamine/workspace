package practice;

public class ManTest2 {
  public static void main(String[] args) {
    Man m1 = new Man();
    m1.initMember("홍길동", 30, "울산시");

    Man m2 = new Man();
    m2.initMember("유관순", 20, "울산시");

    //setter -> 데이터 변경 시 사용
    //getter -> 데이터를 읽어올 때 사용

    //m1과 m2 중 나이가 많은 사람의 이름을 출력
    if(m1.getAge() > m2.getAge()){
      System.out.println(m1.getName());
    }
    else if (m1.getAge() < m2.getAge()) {
      System.out.println(m2.getName());
    }

    //m1 객체의 나이가 30이면
    //m1 객체의 이름을 "이순신"으로 변경하세요

    if(m1.getAge() == 30){
      m1.setName("이순신");
    }
    System.out.println(m1.getName());

  }
}
