package practice;

public class 문제_3_10to12 {
  public static void main(String[] args) {
    //문제 10번
    test10(10, 20);
    System.out.println();

    //문제 11번
    int[] arr1 = {999};
    int[] arr2 = {4398576};
    test11(arr1, arr2);
    System.out.println();

    //문제 12번
    int[] arr3 = {312, 42, 567, 235, 232, 978, 9, 46, 7734, 67, 3, 11};
    test12(arr3);


  }

  public static void test10(int num1, int num2){
    int num3;
    num3 = num1;
    num1 = num2;
    num2 = num3;
    System.out.print(num1 + " " + num2);
  }

  public static void test11(int[] arr1, int[] arr2){
    int[] arr3 = new int[arr1.length];
    arr3[0] = arr1[0];
    arr1[0] = arr2[0];
    arr2[0] = arr3[0];
    System.out.println(arr1[0] + " " + arr2[0]);
  }

  public static void test12(int[] arr){
    int max = arr[0];
    for(int i = 0 ; i < arr.length ; i++){
      for(int j = i + 1 ; j < arr.length ; j++){
        if(arr[i] < arr[j]){
          int a = arr[i];
          arr[i] = arr[j];
          arr[j] = a;
        }
      }
    }
    for( int maxArray : arr ){
      System.out.print(maxArray + " ");
    }

  }

}
