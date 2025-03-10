package practice;

public class 문제_20 {
  public static void main(String[] args) {
    // 1 ~ 99까지 369게임에서 박수를 쳐야하는 경우의 수를 순서대로 출력

    int[] arr = new int[99];

    for (int i = 0; i < arr.length; i++) {
      arr[i] = i + 1;
    }

    for (int i = 0; i < arr.length; i++){
      int ones = arr[i] % 10;
      int tens = arr[i] / 10;
      if (ones == 3 || ones == 6 || ones == 9) {
        if (tens == 3 || tens == 6 || tens == 9)
          System.out.print(arr[i] + " 박수 짝짝");
        else
          System.out.print(arr[i] + " 박수 짝");
      }
      else {
        if(tens == 3 || tens == 6 || tens == 9)
          System.out.print(arr[i] + " 박수 짝");
        else
          continue;
      }
      System.out.println();
    }
  }
}


