package study;

//Car 클래스의 내용을 실행하기 위한 클래스
public class CarTest {
  public static void main(String[] args) {
    //Car 클래스를 활용하여 Car 객체 생성!
    //객체 생성 문법
    //  - 클래스명 객체명 = new 클래스명();
    // * 객체명 == 인스턴스(instance) == 참조변수명
    //new -> 새로운 인스턴스(객체)를 생성해주세요!

    //car1이라는 인스턴스를 생성
    Car car1 = new Car();

    //car1 인스턴스의 데이터 접근 방법
    //객체명.변수명;
    System.out.println(car1.carName);
    System.out.println(car1.price);

    //객체에 존재하는 변수의 값을 변경
    car1.carName = "아반떼";
    car1.price = 1000;
    System.out.println(car1.carName);
    System.out.println(car1.price);

    //메서드 사용법
    //객체명.메서드호출
    car1.powerOn();
    car1.takeBreak();

  }
}
