package study;

public class Friend {
  private String name;
  private String tell;

  public Friend(String name, String tell) {
    this.name = name;
    this.tell = tell;
  }

  public void showInfo(){
    System.out.println("이름 :  " + name);
    System.out.println("연락처 :  " + tell);
  }
}
