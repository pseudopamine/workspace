import java.util.Scanner;

public class 문제_2_1 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int a, b, c;

    System.out.print("변의 길이 입력 : ");
    a = sc.nextInt();

    System.out.print("변의 길이 입력 : ");
    b = sc.nextInt();

    System.out.print("변의 길이 입력 : ");
    c = sc.nextInt();

    if(a + b > c && b + c > a && a + c > b){
      System.out.println("가능");
    }
//    else if(b + c > a){
//      System.out.println("가능");
//    }
//    else if(a + c > b){
//      System.out.println("가능");
//    }
    else{
      System.out.println("불가능");
    }
  }
}
