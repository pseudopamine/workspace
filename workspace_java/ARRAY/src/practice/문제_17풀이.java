package practice;

public class 문제_17풀이 {
  public static void main(String[] args) {
    int[] lotto = new int[6];

    //1~45까지의 랜덤한 정수
    //1.0 <= x < 46.0 실수!
//    (int)(Math.random() * 45 + 1);

    for(int i = 0 ; i < lotto.length ; i++){
      lotto[i] = (int)(Math.random() * 45 +1);
    }

    //배열의 모든 요소 출력

    for(int i = 0 ; i < lotto.length ; i++){
      System.out.print(lotto[i]+ " ");
    }
  }

}
