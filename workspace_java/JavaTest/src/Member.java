public class Member {
  private String id;
  private String pw;
  private String name;
  private int age;

  public Member(String id, String pw, String name, int age) {
    this.id = id;
    this.pw = pw;
    this.name = name;
    this.age = age;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPw() {
    return pw;
  }

  public void setPw(String pw) {
    this.pw = pw;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  void showInfo(){
    System.out.println("ID : " + getId());
    System.out.println("패스워드 : " + getPw());
    System.out.println("이름 : " + getName());
    System.out.println("나이 : " + getAge());
  }

  boolean isLogin(String id, String pw){
    boolean result = false;
    if(getId().equals(id)){
      if(getPw().equals(pw)){
        result = true;
        System.out.println("'로그인 가능'");
      }
      else {
        System.out.println("'로그인 불가능'");
      }
    }

    return result;
  }

}
