package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// 2. 정수를 저장할 수 있는 리스트를 만들고 5개의 정수를 scanner를 통해 입력받아 리스트에 저장한다.
//    리스트에 저장된 모든 데이터의 합을 출력하여라.

public class 문제_1_2 {
  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();

    Scanner sc = new Scanner(System.in);

    for(int i = 0 ; i < 5 ; i++){
      System.out.print(i + 1 + "번째 정수를 입력하세요 : ");
      list.add(sc.nextInt());
    }

    int sum = 0;
    for(int i = 0 ; i < list.size() ; i++){
      sum = sum + list.get(i);
    }
    System.out.println("총 합 = " + sum);
  }
}
