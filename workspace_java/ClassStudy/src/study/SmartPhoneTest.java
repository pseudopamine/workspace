package study;

import javax.print.attribute.standard.PrinterInfo;

//클래스 자료형을 배열로 활용하기
public class SmartPhoneTest {
  public static void main(String[] args) {
    //스마트폰 5개를 저장할 수 있는 배열 phones를 생성하라
    //자료형[] 배열명 = new 자료형[갯수];
    //스마트폰을 저장할 수 있는 공간을 다섯개 생성
    SmartPhone[] phoneArr = new SmartPhone[5];

    //스마트폰 객체 생성
    SmartPhone p1 = new SmartPhone("s1", 10000, 12.5);
    SmartPhone p2 = new SmartPhone("s2", 15000, 14.7);
    SmartPhone p3 = new SmartPhone("s3", 20000, 15.9);
    SmartPhone p4 = new SmartPhone("s4", 35000, 13.5);
    SmartPhone p5 = new SmartPhone("s5", 46000, 10.1);

    //폰 객체를 배열에 저장
    phoneArr[0] = p1;
    phoneArr[1] = p2;
    phoneArr[2] = p3;
    phoneArr[3] = p4;
    phoneArr[4] = p5;

    //phoneArr배열에 저장된 모든 폰의 모델명, 가격, 크기를 출력
    /*for(int i = 0 ; i < phoneArr.length ; i++){
      phoneArr[i].printInfo();
    }*/

    //phoneArr 배열에서 0번째에 저장된 폰의 가격을 출력
    //System.out.println(phoneArr[0].getPrice());

    //phoneArr 배열에서 크기가 13inch 이상인 폰의 모델명을 모두 출력
    /*for(int i = 0 ; i < phoneArr.length ; i++){
      if(phoneArr[i].getInch() >= 13.0){
        System.out.println(phoneArr[i].getModelName());
      }
    }*/

    //for-each
    for( SmartPhone e : phoneArr ){
      if(e.getInch() >= 13.0){
        System.out.println(e.getModelName());
      }
    }







  }
}
