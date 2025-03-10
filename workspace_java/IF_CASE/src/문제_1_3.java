import java.util.Scanner;

public class 문제_1_3 {
  public static void main(String[] args) {
    int a, b;
    Scanner sc = new Scanner(System.in);

    System.out.print("정수 입력 : ");
    a = sc.nextInt();

    System.out.print("정수 또 입력 : ");
    b = sc.nextInt();

    if(a > b){
      System.out.println("a가 큽니다");
    }
    else if(a < b){
      System.out.println("b가 큽니다");
    }
    else if(a == b){
      System.out.println("같습니다.");
    }
  }
}
