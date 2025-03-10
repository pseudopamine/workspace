
/*
* 반복문 (While, for)
* 특정 코드를 원하는 횟수만큼 반복적으로 실행하는 코드
*
* while : 반복 조건이 일치하는 동안~
*
* 반복 시작 조건;
* while(반복 조건){
*   반복 실행할 코드
*   반복 실행할 코드...
*   반복을 파기할 조건
* }
*
* */

public class While_1 {
  public static void main(String[] args) {
    int num = 0; //반복 시작 조건

    //num이 3보다 작을 동안 반복하겠다.
    //안에 있는 조건이 '참'일 동안 반복
    while(num < 3){ //반복 조건
      System.out.println("java");
      num++; //반복을 파기할 조건
    }


    //변수, 자료형, 연산자, 조건문(if), 반복문은 실무에서도 매일매일 사용
  }
}
