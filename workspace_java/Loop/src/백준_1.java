import java.util.Scanner;

public class 백준_1 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int num;
    System.out.print("반복 숫자 없는 수 나열 위한 정수 입력 : ");
    num = sc.nextInt();

    int i = 0;

    while(i < num){
      int tens = (i / 10) - 1;
      int ones = (i % 10);

      i++;

      if(tens - ones == 0){
        continue;
      }
      System.out.print(i + " ");

    }

  }
}
