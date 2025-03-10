import java.util.Scanner;

public class 문제_1_10 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
//    int a, b, c;
//
//    System.out.print("첫번째 수 : ");
//    a = sc.nextInt();
//
//    System.out.print("두번째 수 : ");
//    b = sc.nextInt();
//
//    System.out.print("세 번째 수 : ");
//    c = sc.nextInt();
//
//    if(a > b && a > c && b > c){
//      System.out.println(a + " > " + b + " > " + c);
//    }
//    else if(a > b && a > c && c > b){
//      System.out.println(a + " > " + c + " > " + b);
//    }
//    else if(b > a && b > c && a > c){
//      System.out.println(b + " > " + a + " > " + c);
//    }
//    else if(b > a && b > c && c > a){
//      System.out.println(b + " > " + c + " > " + a);
//    }
//    else if(c > a && c > b && a > b){
//      System.out.println(c + " > " + a + " > " + b);
//    }
//    else if(c > a && c > b && b > a){
//      System.out.println(c + " > " + b + " > " + a);
//    }

    int num1, num2, num3; // 키보드를 입력받은 정수 저장을 위한 변수
    int min, mid, max;

    //키보드로 숫자 입력
    System.out.print("첫번째 수 : ");
    num1  = sc.nextInt();
    System.out.print("두번째 수 : ");
    num2  = sc.nextInt();
    System.out.print("세번째 수 : ");
    num3  = sc.nextInt();

    // 숫자 크기 비교
    //num1이 가장 클 경우
    if(num1 > num2 && num1 > num3){
      max = num1;

      //num2가 num3 보다 클 경우
      if(num2 > num3){
        mid = num2;
        min = num3;
      }
      //그렇지 않은 경우
      else{
        mid = num3;
        min = num2;
      }

    }
    //그렇지 않고 num2가 가장 클 경우
    else if(num2 > num1 && num2 > num3){
      max = num2;

      if(num1 > num3){
       mid = num1;
       min = num3;
      }
      else{
        mid = num3;
        min = num1;
      }
    }
    //num3가 가장 클 경우
    else{
      max = num3;

      if(num1 > num2){
        mid = num1;
        min = num2;
      }
      else{
        mid = num2;
        min = num1;
      }
    }

    //출력
    System.out.println(max + " > " + mid + " > " + min);



  }
}
