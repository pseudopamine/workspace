import java.util.Scanner;

public class 문제_3_3 {
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

    if(c.equals("+")){
      System.out.print(a + " + " + b + " = " + (a + b));
    }
    else if(c.equals("*")){
      System.out.print(a + " * " + b + " = " + (a * b));
    }
    else if(c.equals("/")){
      System.out.print(a + " / " + b + " = " + (a / (double)b));
    }
    else if(c.equals("-")){
      System.out.print(a + " - " + b + " = " + (a - b));
    }
    else{
      System.out.println("연산자를 잘못 입력하였습니다.");
    }

  }
}
