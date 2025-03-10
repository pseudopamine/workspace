package practice2;

public class Member2Test {
  public static void main(String[] args) {
    Member2 member1 = new Member2("홍길동", "hong");
    Member2 member2 = new Member2("강자바", "java");

    member1.login("hong", "12345");

    member2.logout("java");

  }
}
