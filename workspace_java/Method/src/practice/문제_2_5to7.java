package practice;

public class 문제_2_5to7 {
  public static void main(String[] args) {
    //문제 5번
    String score = test5(78);
    System.out.println("당신의 성적은 " + score + "입니다.");

    //문제 6번
    String str = test6(17317071);
    System.out.println(str);

    //문제 7번
    double d = test7(1.84);
    System.out.println(d);

  }

  public static String test5(int num){
    String result = "";
    if(num >= 90){
      result = "A";
    }
    else if(num >= 70){
      result = "B";
    }
    else{
      result = "C";
    }
    return result;

  }

  public static String test6(int num){
    //숫자 -> 문자열
    //String a = String.valueOf(num);
    return String.valueOf(num);
    //return num + "";
    //문자열 -> 정수
    //int b = Integer.parseInt("10");
  }

  public static double test7(double d){
    return d * d;
  }



}
