public class 자료형_3 {
  public static void main(String[] args) {
    //형(data type) 변환
    //정수 -> 실수, 실수 -> 정수

    int num1 = 5;

    //자동 형변환(promotion)
    double num2 = num1;
    System.out.println("num2 = " + num2);

    double num3 = 5.9;
    //강제 형변환(casting)
    int num4 = (int)num3;
    System.out.println("num4 = " + num4);






  }
}
