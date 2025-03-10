import java.util.Scanner;

public class 문제_1_9 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int a, b;
    int min; //a와 b 중 작은 수를 저장할 변수
    int max; //a와 b 중 큰 수를 저장할 변수

    System.out.print("첫번째 수 : ");
    a = sc.nextInt();

    System.out.print("두번째 수 : ");
    b = sc.nextInt();

    if(a < b){
//      System.out.println(b + " > " + a);
      min = a;
      max = b;
    }
    else{
//      System.out.println(a + " > " + b);
      min = b;
      max = a;
    }
    System.out.println(min + " > " + max);
  }
}
