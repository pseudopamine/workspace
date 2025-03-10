package study;

public class MobilePhone {
  public void sendMsg(){
    System.out.println("문자 메세지를 보냅니다.");
  }
}

class SmartPhone extends MobilePhone{
  public void playApp(){
    System.out.println("앱을 실행합니다.");
  }
}