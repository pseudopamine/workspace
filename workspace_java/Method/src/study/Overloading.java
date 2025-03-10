package study;


//메서드 오버로딩
//메서드명은 중복 불가하다.
//하지만 메서드의 매개변수 정보(매개변수의 갯수, 매개변수의 자료형)가 다르면
//중복된 이름으로 메서드명 작성 허용!!
//똑같은 기능인데 매개변수만 다르다고 다른 메서드명으로 작성한다면 학습해야할 자료가 많아지기 때문에
public class Overloading {
  public static void main(String[] args) {
    System.out.println(true);

  }
  //문자열을 출력하는 메서드
  public static void println(String str){
    System.out.println(str);
  }

  //정수를 출력하는 메서드
  public static void println(int str){
    System.out.println(str);
  }

  //정수를 출력하는 메서드
  public static void println(int str, int str1) {
    System.out.println(str);
  }
}
