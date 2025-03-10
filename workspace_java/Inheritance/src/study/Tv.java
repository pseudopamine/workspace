package study;

//클래스가 명시적으로 상속을 받고 있지 않으면
//자동으로 Object 클래스를 상속받는다
//결론, 모든 클래스는 Object 클래스를 상속받고 있음
//모든 클래스의 객체는 Object로 다 받을 수 있다
public class Tv {
  int num;
  String name;

  public void turnOn(){
    System.out.println("TV 전원 켬");
  }

  //Object 클래스의 toString() 메서드 오버라이딩
  //@Override : 메서드가 오버라이딩 문법에 맞는지 확인
  @Override
  public String toString() {
    return "Tv{" +
            "num=" + num +
            ", name='" + name + '\'' +
            '}';
  }
}
