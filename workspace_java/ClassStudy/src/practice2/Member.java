package practice2;

public class Member {
  private String name;
  private String id;
  private String pw;

  public Member(){}

  public Member(String name, String id, String pw){
    this.name = name;
    this.id = id;
    this.pw = pw;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public void displayInfo(){
    System.out.println("이름 : " + getName());
    System.out.println("아이디 : " + getId());
    System.out.println("비밀번호 : " + getPw());
  }

}
