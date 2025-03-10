import java.util.Arrays;
import java.util.Scanner;

public class Test5 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int[] baseball = new int[3];
    int[] num = new int[3];

    int strike = 0;
    int ball = 0;
    int cnt = 0;

    /*for(int i = 0 ; i < baseBall.length ; i++){
      baseBall[i] = (int)(Math.random() * 9 + 1);
      if(baseBall[0] == baseBall[1] || baseBall[0] == baseBall[2] || baseBall[1] == baseBall[2]){
        continue;
      }
      System.out.print(baseBall[i] + " ");
    }*/

    for(int i = 0 ; i < baseball.length ; i++){
      baseball[i] = (int)(Math.random() * 9 + 1);
      for(int a = 0 ; a < i ; a++){
        if(baseball[a] == baseball[i]){
          i = i - 1;
          break;
        }
      }
    }
    System.out.println(Arrays.toString(baseball));
    System.out.println();
    System.out.println("숫자를 정했습니다. 게임을 시작합니다.");


    while(true){
      System.out.print(cnt + " >> ");
      for(int i = 0 ; i < num.length ; i++){
        num[i] = sc.nextInt();
        for(int a = 0 ; a < i ; a++){
          if(num[a] == num[i]){
            i = i - 1;
            break;
          }
        }
      }
      for(int i = 0 ; i < baseball.length ; i++){
        for(int a = 0 ; a < num.length ; a++){
          if(baseball[i] == num[a] && i == a){
            strike = strike + 1;
          }
          else if(baseball[i] == num[a] && i != a){
            ball = ball + 1;
          }
        }
      }
      cnt++;
      System.out.println(strike + "스트라이크, " + ball + "볼");
      if(strike == 3){
        System.out.println(cnt + "회 만에 정답을 맞췄습니다.");
        break;
      }

    }

  }
}
