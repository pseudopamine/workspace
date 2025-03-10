package practice;

public class 문제_1_13to15 {
  public static void main(String[] args) {
    //문제 13번
    int a = 51;
    int b = 23;
    test13(a, b);
    System.out.println();
    //문제 14번
    test14(3, 594);
    System.out.println();
    //문제 15번
    test15("탄핵", 5);


  }
  public static void test13(int num1, int num2){
   int max = num1 > num2 ? num1 : num2;
   int min = num1 < num2 ? num1 : num2;
   for(int i = min + 1 ; i < max ; i++){
     System.out.print(i + " ");
   }
    /*int max = num1;
    int min = num1;
    if(num1 > num2){
      max = num1;
      min = num2;
    }
    else if(num1 < num2){
      max = num2;
      min = num1;
    }
    int[] arr1 = new int[max - min];
    for(int i = 0 ; i < arr1.length - 1 ; i++){
      arr1[i] = min + i + 1;
      System.out.print(arr1[i] + " ");
    }*/
  }
  public static void test14(int num1, int num2){
    int max = num1 > num2 ? num1 : num2;
    int min = num1 < num2 ? num1 : num2;
    int cnt = 0;
    for(int i = min + 1 ; i < max ; i++){
      if(i % 5 == 0){
        cnt++;
      }
    }
    System.out.print("5의 배수의 갯수는 " + cnt + "개 입니다.");
    /*int max = num3;
    int min = num4;
    if(num3 > num4){
      max = num3;
      min = num4;
    }
    else if(num3 < num4){
      max = num4;
      min = num3;
    }
    int cnt = 0;
    int[] arr2 = new int[max - min];
    for(int i = 0 ; i < arr2.length - 1 ; i++){
      arr2[i] = min + i + 1;
      if(arr2[i] % 5 == 0){
        cnt++;
      }
    }
    System.out.print("5의 배수의 갯수는 " + cnt + "개 입니다.");*/
  }
  public static void test15(String str, int num){
    for(int i = 0 ; i < num ; i++){
      System.out.print(str + " ");
    }
  }
}
