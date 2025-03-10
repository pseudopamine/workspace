public class 연산자_1 {
  public static void main(String[] args) {
    //산술 연산자(+, -, *, /, %)
    // '%' : mod(모드) 연산자 : 나눗셈의 나머지
    System.out.println(6 % 2);
    System.out.println(8 % 3);
    System.out.println();

    //정수끼리의 연산은 무조건 정수로 결과가 나옴
    System.out.println(8 / 3);
    System.out.println(8.0 / 3);

    int a = 8;
    System.out.println((double)a / 3);
    System.out.println();

    //비교연산자(>, <, >=, <=, ==, !=)
    // '==' : 같다, '!=' : 다르다
    System.out.println(5 > 1);
    System.out.println(3 == 3);

    //복합대입연산자
    int num = 3;

    //num 값을 1 증가 시키는 코드
    num = num + 1;
    num += 1; //복합대입연산자

    num *= 3; //num = num * 3;
    num -= 4; //num = num - 4;
    num /= 2; //num = num / 2;
    num %= 2; //num = num % 2;

    //논리연산자
    //그리고and : &&, 이거나or : ||(버티컬 바)
    //and와 or 연산이 동시에 있으면 and 연산이 우선순위가 높다
    System.out.println(3 > 1 && 2 < 4);
    System.out.println(1 == 1 && 2 > 1 || 3 > 5 && 1 < 4 );
    System.out.println((1 == 1 && 2 > 1) || (3 > 5 && 1 < 4 ));


  }
}
