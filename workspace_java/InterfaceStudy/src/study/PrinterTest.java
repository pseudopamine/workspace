package study;

public class PrinterTest {
  public static void main(String[] args) {
    SamsungPrinter p = new SamsungPrinter();
    p.print();
    p.colorPrint();

    //인터페이스를
    Print p2 = new SamsungPrinter();
  }
}
