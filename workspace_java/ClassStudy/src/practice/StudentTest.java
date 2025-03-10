package practice;

public class StudentTest {
  public static void main(String[] args) {
    Student member = new Student();
    member.setAllInfo("이한열", 23, "서울특별시", 19870518, "01019870518");

    member.printAllInfo();

  }
}
