import java.util.Scanner;

public class For_1_8 {
  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);

    int cnt = 0;

    System.out.print("정수를 입력하시오 : ");

    for(int i = sc.nextInt(); i > 0 ; i--){
      if(i % 2 == 0){
        cnt++;
      }
    }
    System.out.println("짝수의 갯수는 " + cnt + "개 입니다.");
  }
}
