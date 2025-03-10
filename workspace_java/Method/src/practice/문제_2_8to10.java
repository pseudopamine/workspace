package practice;

public class 문제_2_8to10 {
  public static void main(String[] args) {
    //문제 8번
    double avg = test8(256, 564, 21);
    System.out.print("세 숫자의 평균 : " + avg);
    System.out.println();

    //문제 9번
    int oddSum = test9(10);
    System.out.println(oddSum);

    //문제 10번

    if(test10("집에가고싶구려")){
      System.out.print("짝수");
    }
    else{
      System.out.print("홀수");
    }

  }

  public static double test8(int num1, int num2, int num3){
    return ((num1 + num2 + num3) / 3.0);
  }

  public static int test9(int num){
    int sum = 0;
    for(int i = 1 ; i < num + 1 ; i++){
      if(i % 2 == 1){
        sum = sum + i;
      }
    }
    return sum;
  }

  public static boolean test10(String str){
    return str.length() % 2 == 0 ? true : false;
    //return str.length() % 2 == 0;
  }



}
