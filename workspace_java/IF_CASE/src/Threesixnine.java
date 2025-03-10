import com.sun.source.tree.ContinueTree;

import java.util.Scanner;

public class Threesixnine {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int num;

    System.out.print("삼육구 삼육구 삼육구 삼육구!! ");
    num = sc.nextInt();

    int tens = num / 10;
    int ones = num % 10;

    if(ones == 3 || ones == 6 || ones == 9){
      if(tens == 3 || tens == 6 || tens == 9)
        System.out.print(num + " 박수 짝짝");
      else
        System.out.print(num + " 박수 짝");
    }
    else {
      if(tens == 3 || tens == 6 || tens == 9)
        System.out.print(num + " 박수 짝");
    }
  }
}

