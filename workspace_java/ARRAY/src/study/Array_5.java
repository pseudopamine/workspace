package study;

//배열 자료형(참조 자료형)과 기본 자료형의 차이점
public class Array_5 {
  public static void main(String[] args) {
    //기본자료형 예시

    int a = 10;
    int b = 20;
    a = b;
    b = 30;
    System.out.println("a = " + a); //20
    System.out.println("b = " + b); //30
    System.out.println();

    //배열(참조자료형) 예시
    int[] arr1 = new int[1];
    arr1[0] = 10;
    int[] arr2 = new int[1];
    arr2[0] = 20;

    arr1 = arr2;
    arr2[0] = 30;
    System.out.println("arr1[0] = " + arr1[0]); //30
    System.out.println("arr2[0] = " + arr2[0]); //30




  }
}
