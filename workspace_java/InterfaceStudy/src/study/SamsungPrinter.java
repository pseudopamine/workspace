package study;

//implements : 구현
//Print 인터페이스를 구현하여 SamsungPrinter 클래스를 만들거임
public class SamsungPrinter implements Print{

  @Override
  public void print() {
    System.out.println("흑백 출력 실행");
  }

  @Override
  public void colorPrint() {
    System.out.println("컬러 출력 실행");
  }
}
