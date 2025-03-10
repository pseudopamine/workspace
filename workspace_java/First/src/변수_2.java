public class 변수_2 {
  public static void main(String[] args){
    //변수는 필요하면 여러개 사용할 수 있음
    int num1;
    num1 = 10;

    int num2;
    num2 = 20;

    //int num2; 변수명은 중복불가


    //num1 변수와 num2 변수에 저장된 정수의 합을 출력
    System.out.println(num1 + num2);

    int num3 = 40;
    int num4 = 50;

    System.out.println(num3 + num4);

    int korScore1 = 70;
    int korScore2 = 60;
    int korScore3 = 100;

    int engScore1 = 40;
    int engScore2 = 55;
    int engScore3 = 88;

    int matScore1 = 98;
    int matScore2 = 73;
    int matScore3 = 93;

    System.out.println();
    System.out.println("국어점수");
    System.out.println("김민영" + " : " + korScore1);
    System.out.println("이철진" + " : " + korScore2);
    System.out.println("최창수" + " : " + korScore3);

    System.out.println();
    System.out.println("영어점수");
    System.out.println("김민영" + " : " + engScore1);
    System.out.println("이철진" + " : " + engScore2);
    System.out.println("최창수" + " : " + engScore3);

    System.out.println();
    System.out.println("수학점수");
    System.out.println("김민영" + " : " + matScore1);
    System.out.println("이철진" + " : " + matScore2);
    System.out.println("최창수" + " : " + matScore3);


  }
}
