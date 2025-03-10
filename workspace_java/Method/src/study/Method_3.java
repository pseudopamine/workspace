package study;


/*
* 매개변수가 있는 메서드
* 매개변수 : 메서드의 정의 부분의 () 영역에 작성한 변수
* 배개변수가 하는 역할 : 메서드 내에서 기능을 구현할 때 특정 데이터가 필요하다면
*                     그 데이터를 매개변수로 작성.
*
* */
public class Method_3 {
  public static void main(String[] args) {
    printNum(5);
    printNum(25);
    printNum(567);

    //메서드 선언 시 매개변수의 자료형과
    //메서드 호출 시 사용한 매개변수의 자료형이 다르면 오류!!
    //printNum("java"); 오류!!!

    //매개변수의 갯수가 달라도 오류!!
    //printNum(3, 4); 오류!!!!

  }

  //원하는 숫자를 출력하는 메서드 정의
  public static void printNum(int num){
    System.out.println(num);
  }
}
