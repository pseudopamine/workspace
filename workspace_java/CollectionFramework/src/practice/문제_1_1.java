package practice;

import java.util.ArrayList;
import java.util.List;

public class 문제_1_1 {
  public static void main(String[] args) {
    List<String> list = new ArrayList<>();

    list.add("java");
    list.add("c++");
    list.add("python");

    System.out.println(list.get(0));
    System.out.println(list.get(1));
    System.out.println(list.get(2));

  }
}
