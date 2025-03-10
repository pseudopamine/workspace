import java.util.Scanner;

public class 문자열입력받기_2 {
  public static void main(String[] args) {
    //키보드 입력을 받기 위한 변수(복사해서 사용!)
    Scanner sc = new Scanner(System.in);

    //이름과 주소를 입력
    System.out.print("이름을 입력하세요 : ");
    String name = sc.next();

    System.out.println("주소를 입력해주세요 : ");
    String addr = sc.next();

    System.out.println("이름 = " + name);
    System.out.println("주소 = " + addr);
  }
}
