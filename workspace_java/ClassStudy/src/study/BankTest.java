package study;

import java.sql.SQLOutput;

public class BankTest {
  public static void main(String[] args) {
    //Bank 클래스에 대한 객체 5개를 저장할 수 있는 배열 bankArr를 생성
    //Bank 클래스에 대한 객체 5개를 만든 코드다? 아님! XXXXX
    //Bank 클래스에 대한 객체 5개를 저장할 수 있는 공간을 5개 생성
    Bank[] bankArr = new Bank[5];

    //Bank 클래스에 대한 객체 5개를 생성
    //그 다음 bankArr에 객체를 저장
    bankArr[0] = new Bank("고애신", 20000, "1111-2264");
    bankArr[1] = new Bank("장만월", 30000, "3331-1222");
    bankArr[2] = new Bank("김희성", 40000, "5551-8222");
    bankArr[3] = new Bank("구동매", 100000, "6251-7222");
    bankArr[4] = new Bank("유진최", 60000, "9771-4422");

    //bankArr에 저장된 모든 계좌의 예금액 합을 출력
    int sum = 0;
    for(int i = 0 ; i < bankArr.length ; i++){
      sum = sum + bankArr[i].getBalance();
    }
    System.out.print("모든 계좌의 총액 : " + sum);
    System.out.println();

    int sum1 = 0;
    for( Bank bank :  bankArr ){
      sum1 = sum1 + bank.getBalance();
    }
    System.out.print("모든 계좌의 총액 : " + sum1);
    System.out.println();

    /// //////////////////////////////////

    //2. bankArr에 저장된 모든 계좌정보 중에서
    //계좌주가 "구동매"인 계좌정보를 찾고, 그 정보 중
    //예금액을 20% 인상시키는 코드를 작성!

    for(int i = 0 ; i < bankArr.length ; i++){
      if(bankArr[i].getOwner().equals("구동매")){
       bankArr[i].setBalance((int)(bankArr[i].getBalance() * 1.2));
        System.out.println(bankArr[i].getBalance());
      }
    }



    for( Bank bank  :  bankArr ){
      if(bank.getOwner().equals("구동매")){
        int result = (int)(bank.getBalance() * 1.2);
        bank.setBalance(result);
      }
    }

  }








}
