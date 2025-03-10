import java.util.Scanner;

public class Test3 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("1에서 999 사이의 숫자를 입력하세요: ");
    int num = scanner.nextInt();

    int clapCnt = 0;

    while (num > 0) {
      int eachNum = num % 10;
      if (eachNum == 3 || eachNum == 6 || eachNum == 9) {
        clapCnt++;
      }
      num = num / 10;
    }
    System.out.println("박수 " + clapCnt + "번");
  }
}
