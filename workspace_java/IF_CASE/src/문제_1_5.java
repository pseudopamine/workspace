import java.util.Scanner;

public class 문제_1_5 {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    int a;
    String grade;

    System.out.print("점수를 입력하세요 : ");
    a = sc.nextInt();

    if(90 < a && a <= 100){
     grade = "A";
    }
//    else if(80 < a && a <= 90){
    else if(80 < a){
      grade = "B";
    }
    else{
      grade = "C";
    }

    System.out.println("학점은 " + grade + "입니다");
  }
}
