package study;

public class PhoneTest {
  public static void main(String[] args) {
    MobilePhone p1 = new MobilePhone();
    SmartPhone p2 = new SmartPhone();

    //부모 클래스는 자식 클래스의 객체를 받아들임
    MobilePhone p3 = new SmartPhone();
    //자식 클래스는 부모 클래스의 객체를 수용하지 않음
    //SmartPhone p4 = new MobilePhone();  오류발생


  }
}
