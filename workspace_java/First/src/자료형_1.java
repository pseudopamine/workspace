/*
* 자료형 (data type)
* 자바의 자료형은 크게 기본자료형, 참조자료형으로 구분
* 기본자료형(Primitive type) 8개 존재. 8개 모두 소문자
* 참과 거짓 : boolean -> true, false
* 정수 : byte, short, int, long
* 실수 : float, double
* 문자 : char (character)
* 문자열 (문자 나열) vs 문자
* 문자 : 한 글자 + 홀따옴표로 감싸짐
* 문자열 : 여러 글자, !!쌍따옴표에 싸여진 글자
* ex) 'a':문자, "java":문자열, "A":문자열, "":문자열
* 자바의 기본자료형에는 문자열을 저장할 '기본'자료형을 제공하지 않는다
* 문자열 참조 자료형 : String
*
*
* 참조자료형은 무한대.
*
* */

public class 자료형_1 {
  public static void main(String[] args){
    //참과 거짓
    boolean a = true;
    boolean b = false;
    // boolean c = "true"; 주의!! 이거 아님
    System.out.println(a);

    int d = 1;
    byte e = 1;
    short f = 1;
    long g = 1L; //

    double h = 1.5;
    float i = 1.5F;

    //문자열
    String s = "hello";
    System.out.println(s);






  }
}
