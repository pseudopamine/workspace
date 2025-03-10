package practice;

import java.util.ArrayList;
import java.util.List;

public class 문제_1_3 {
  public static void main(String[] args) {
    List<String> list = new ArrayList<>();

    list.add("김철수");
    list.add("이영희");
    list.add("박희동");
    list.add("홍길동");
    list.add("최수지");

    for(int i = 0 ; i < list.size() ; i++){
      if(list.get(i).equals("홍길동")){
        System.out.println("해당 이름이 존재합니다");
      }
    }




  }






}
