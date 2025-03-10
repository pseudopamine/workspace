package list_study;

import java.util.ArrayList;
import java.util.List;

public class List_3 {
  public static void main(String[] args) {
    //문자열을 다수 저장할 수 있는 list 객체 생성
    List<String> list = new ArrayList<>();

    list.add("java");
    list.add("C++");
    list.add("python");

    for(int i = 0 ; i < list.size() ; i++){
      System.out.println(list.get(i));
    }
    for( String e :  list){
      System.out.println(e);
    }

    list.remove(0);
    System.out.println(list.size());

  }
}
