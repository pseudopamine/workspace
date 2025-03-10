package practice;

public class 문제_16 {
  public static void main(String[] args) {
    //자바에서 랜덤 숫자를 발생시키고 싶을 때는
    //Math.random() 명령어를 사용
    //Math.random() : 0 <= x < 1.0 사이의 랜덤한 실수값 생성

    double r = Math.random();
    System.out.println(r);

    //0부터 10까지의 랜덤한 정수를 생성
    //0 <= x < 11.0
    int i = (int)(Math.random() * 11);
    System.out.println(i);
  }
}
