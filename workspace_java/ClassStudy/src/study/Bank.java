package study;

public class Bank {
  private String owner; //계좌주
  private int balance; //금액
  private String accNum; //계좌번호

  public Bank(String owner, int balance, String accNum) {
    this.owner = owner;
    this.balance = balance;
    this.accNum = accNum;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }

  public String getAccNum() {
    return accNum;
  }

  public void setAccNum(String accNum) {
    this.accNum = accNum;
  }

  public void printInfo(){
    System.out.println("계좌주 : " + owner);
    System.out.println("예금액 : " + balance);
    System.out.println("계좌번호 : " + accNum);
  }


}
