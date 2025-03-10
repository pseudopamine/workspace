import java.util.Scanner;

public class 문제_2_3 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int num;
    int clapCnt = 0; //박수의 갯수

    System.out.print("1~99 사이의 정수를 입력하시오>>");
    num = sc.nextInt();

    //입력받은 정수를 1의 자리와 10의 자리 수로 분리
    //십의 자리

    int tens = num / 10;
    //일의 자리
    int ones = num % 10;

    //만약 십의 자리 수가 3, 6, 9면 박수 수를 1 증가
    if(tens == 3 || tens == 6 || tens == 9){
      clapCnt = clapCnt + 1;
//      clapCnt += 1;
//      ++clapCnt;
//      clapCnt++;
    }
//
//    if(tens % 3 == 0 && tens == 0){
//
//    }

    //만약 일의 자리 수가 3, 6, 9면 박수 수를 1 증가
    if(ones == 3 || ones == 6 || ones == 9){
      clapCnt = clapCnt + 1;
    }
    //clapCnt = 0, 1, 2
    //0이면 박수 없음
    //1이면 박수짝
    //2이면 박수짝짝

    switch (clapCnt){
      case 0:
        System.out.println("박수없음");
        break;
      case 1:
        System.out.println("박수짝");
        break;
      case 2:
        System.out.println("박수짝짝");
    }



  }
}
