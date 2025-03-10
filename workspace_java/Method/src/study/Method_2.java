package study;

public class Method_2 {
  //이름을 출력하는 메서드 정의
  //나이를 출력하는 메서드 정의
  public static void printName(){
    System.out.println("제 이름은 홍길동입니다.");
  }
  public static void printAge(){
    System.out.println("제 나이는 1488세입니다.");
  }

  public static void main(String[] args) {
    System.out.println("시작!");

    //메서드 호출, 여러번 호출 가능
    printName();
    printAge();
    printName();

    System.out.println("끝!");
  }
}
