package practice;

public class ManTest {
  public static void main(String[] args) {
    Man member1 = new Man();
    Man member2 = new Man();

    member1.setName("장만월");
    member1.setAge(1400);
    member1.setAdr("명동");
    member1.printInfo();

    //이름 데이터 출력
    System.out.println(member1.name);
    System.out.println(member1.getName());


  }
}
