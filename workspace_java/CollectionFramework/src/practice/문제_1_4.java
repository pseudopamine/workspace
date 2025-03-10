package practice;

import java.util.ArrayList;
import java.util.List;

//4. 리스트에 1~100까지의 랜덤한 정수를 10개 넣어보자.
//   그 후 저장된 데이터 중 짝수의 개수와 모든 짝수를 출력하는 프로그램을 만드세요

public class 문제_1_4 {
  public static void main(String[] args) {
    List<Integer> list = new ArrayList<>();

    for(int i = 0 ; i < 10 ; i++){
      list.add((int)(Math.random() * 100) + 1);
    }

    for( int i :  list ){
      System.out.print(i + " ");
    }

    System.out.println();

    int cnt = 0;
    System.out.print("짝수는 : ");
    for(int i = 0 ; i < list.size() ; i++){
      if(list.get(i) % 2 == 0){
        cnt++;
        System.out.print(list.get(i) + " ");
      }
    }
    System.out.println();
    System.out.println("짝수의 갯수는 모두 " + cnt + "개 입니다.");
  }
}

