import java.util.Scanner;

public class 문제_3_4 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int a, b;
    String c;

    System.out.print("첫 번째 수 : ");
    a = sc.nextInt();
    System.out.print("두 번째 수 : ");
    b = sc.nextInt();
    System.out.print("연산자 : ");
    c = sc.next();

    switch (c){
      case "+" :
        System.out.print(a + " + " + b + " = " + (a + b));
        break;
      case "*" :
        System.out.print(a + " + " + b + " = " + (a * b));
        break;
      case "/" :
        System.out.print(a + " + " + b + " = " + (a / (double)b));
        break;
      case "-" :
        System.out.print(a + " + " + b + " = " + (a - b));
        break;
      default:
        System.out.println("연산자를 잘못 입력하였습니다.");
    }
  }
}
