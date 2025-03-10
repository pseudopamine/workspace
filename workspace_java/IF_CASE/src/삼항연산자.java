public class 삼항연산자 {
  public static void main(String[] args) {
    int a = 3;
    int b;

    if(a > 5){
      b = 1;
    }
    else{
      b = 2;
    }

    // 위와 동일한 해석의 삼항 연산자
    // 삼항연산자
    // 참 또는 거짓을 판별하는 조건 ? 참일 때 실행내용 : 거짓일 때 실행내용;
    b = a > 5 ? 1 : 2;


    System.out.println(b);


    //num이 5이면 "참" 출력, 그렇지 않으면 "거짓" 출력
    int num = 5;
    System.out.println( num == 5 ? "참" : "거짓" );

    //키보드로 정수 두 개를 입력받아,
    //입력받은 수 중에서 큰 수는 max라는 변수에 저장하고,
    //작은 수는 min이라는 변수에 저장.
    int num1 = 3;
    int num2 = 7;
    int min = num1 > num2 ? num2 : num1;
    int max = num1 > num2 ? num1 : num2;



  }
}
