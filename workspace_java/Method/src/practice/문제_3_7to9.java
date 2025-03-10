package practice;

import java.util.Arrays;

public class 문제_3_7to9 {
  public static void main(String[] args) {
    //문제 7번
    String[] str = {"집에 ", "가고", " 싶은 ", "마음이", " 굴뚝 ", "같은데요?"};
    String t = test7(str);
    System.out.println(t);

    //문제 8번
    int[] arr1 = {56, 235, 2908, 5543, 2374};
    int[] arr2 = {1, 4, 9, 23};
    test8(arr1, arr2);
    System.out.println();

    //문제 9번
    int [] arr3 = {1, 3, 5, 2, 9, 10, 11, 14, 16, 18, 17, 77, 74, 26, 73, 56};
    int[] newArr = test9(arr3);
    System.out.println(Arrays.toString(newArr));


  }

  public static String test7(String[] str){
    String result = "";
    for(int i = 0 ; i < str.length ; i++){
      result = result + str[i];
    }
    return result;

  }

  public static int[] test8(int[] arr1, int[] arr2){
    int[] arr3 = new int[arr1.length + arr2.length];
      for(int i = 0 ; i < arr1.length ; i++){
        arr3[i] = arr1[i];
      }
      for(int i = 0 ; i < arr2.length ; i++){
        arr3[i + arr1.length] = arr2[i];
      }
    for(int i = 0 ; i < arr3.length ; i++){
      System.out.print(arr3[i] + " ");
    }
      return arr3;
  }

  public static int[] test9(int[] arr){
    int arrLength = 0;
    for(int i = 0 ; i < arr.length ; i++){
      if(arr[i] % 2 == 0){
        arrLength++;
      }
    }
    int cnt = 0;
    int[] newArr = new int[arrLength];
    for(int i = 0 ; i < arr.length ; i++){
      if(arr[i] % 2 == 0){
        newArr[i - cnt] = arr[i];
      }
      else{
        cnt++;
      }
    }
    /*for(int i = 0 ; i < newArr.length - cnt; i++){
      System.out.print(newArr[i] + " ");
    }*/
    return newArr;
  }
}
