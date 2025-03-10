package study;

public class StudentTest {
  public static void main(String[] args) {
    //String 데이터는 메모리 공간 낭비를 최소화 할 수 있도록 설계
    int[] arr1 = {1, 2, 3};
    int[] arr2 = arr1;
    arr2[0] = 5;
    System.out.println(arr1[0]);
    System.out.println(arr2[0]);

    String a = "java";
    String b = a;
    b = "python";
    System.out.println(a);
    System.out.println(b);

    String str1 = "react";
    String str2 = "react";
    String str3 = new String("java");
    String str4 = new String("java");


    // == 두 객체의 주소값이 같은지 비교하는 연산
    // 숫자 비교 연산에서 사용
    if(str1 == str2){
      System.out.println("str1과 str2는 같습니다.");
    }

    if(str3 == str4){
      System.out.println("str3과 str4는 같습니다.");
    }


  }
}
