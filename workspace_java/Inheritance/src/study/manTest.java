package study;

public class manTest {
  public static void main(String[] args) {
    //businessMan 클래스에 대한 객체 생성
    BusinessMan man = new BusinessMan("삼성");
    man.company = "삼성";
    System.out.println(man.company);
    man.tellCompany();


    man.tellname();

  }
}
