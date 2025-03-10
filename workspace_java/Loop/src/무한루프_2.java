import java.util.Scanner;

public class 무한루프_2 {
  public static void main(String[] args) {
    //무한루프는 언제 사용하는가?
    //국어 점수(0 ~ 100)를 입력 받으세요.

    //0 ~ 100점 사이의 점수를 입력할 때 동안 계속 입력시킨다.
    Scanner sc = new Scanner(System.in);
    int score;


    while(true){
      System.out.print("국어 점수 입력(0 ~ 100) : ");
      score = sc.nextInt();

      //입력받은 점수가 0 ~ 100사이라면 반복문을 종료한다.
      if(score >= 0 && score <= 100){
        break;
      }
    }

  }
}
