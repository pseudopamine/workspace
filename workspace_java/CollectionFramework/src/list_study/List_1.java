package list_study;

import java.util.ArrayList;
import java.util.List;


//List에 저장할 자료형은 기본자료형 사용 못 함
//List에서 사용을 위해 기본자료형을 클래스화 시킨 자료형 존재(Wrapper 클래스)
//int -> Integer, double -> Double, boolean -> Boolean



public class List_1 {
  public static void main(String[] args) {
    //문자열을 다수 저장할 수 있는 List 생성
    List<String> list = new ArrayList<>();

    //정수를 다수 저장할 수 있는 List 생성
    List<Integer> list2 = new ArrayList<>();

    //실수를 다수 저장할 수 있는 List 생성
    List<Double> list3 = new ArrayList<>();


  }
}
