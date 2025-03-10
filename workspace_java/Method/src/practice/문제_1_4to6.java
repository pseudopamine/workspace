package practice;

public class 문제_1_4to6 {
  public static void main(String[] args) {
    //문제 4번
    test4(10, 20);
    //문제 5번
    test5(5, 6, 7);
    //문제 6번
    test6(10, 20);

  }
  public static void test4(int num1, int num2){
    System.out.println(num1 + num2);
  }
  public static void test5(int num3, int num4, int num5){
    System.out.println(num3 * num4 * num5);
  }
  public static void test6(int num6, int num7){
    System.out.print(num6 / num7);
    System.out.println(" " + num6 % num7);
  }
}
