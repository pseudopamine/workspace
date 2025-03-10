package practice;

public class 문제_3_4to6 {
  public static void main(String[] args) {
    //문제 4번
    if(test4(4)){
      System.out.print("짝수");
    }
    else{
      System.out.print("홀수");
    }
    System.out.println();

    //문제 5번
    int[] arr1 = {62, 547, 232, 5757, 33, 87};
    test5(arr1);
    System.out.println();

    //문제 6번
    int[] arr2 = {57, 893, 5, 754, 32, 74, 1, 90, 20};
    int max = test6(arr2);
    System.out.print("최대값은 " + max);


  }

  public static boolean test4(int num){
    /*boolean a = num % 2 == 0 ? true : false;
    return a;*/
    return num % 2 == 0;

  }

  public static void test5(int[] arr){
    for(int i = 0 ; i < arr.length ; i++){
      System.out.print(arr[i] + " ");
    }

  }
  public static int test6(int[] arr){
    int max = arr[0];
    for(int i = 0 ; i < arr.length ; i++){
      if(arr[i] > max){
        max = arr[i];
      }
    }
    return max;

  }

}
