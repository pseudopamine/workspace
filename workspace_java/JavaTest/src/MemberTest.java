public class MemberTest {
  public static void main(String[] args) {
    Member member = new Member("java", "1234", "kim", 20);

    member.showInfo();

    member.isLogin("java", "1234");

    member.isLogin("java", "1111");
  }
}
