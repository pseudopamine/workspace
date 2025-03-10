package practice;

public class Member {
  private String id;
  private String password;
  private String name;
  private int age;

  public Member(){}

  public Member(int age, String name, String password, String id) {
    this.age = age;
    this.name = name;
    this.password = password;
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
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

  @Override
  public String toString() {
    return "Member{" +
            "id='" + id + '\'' +
            ", password='" + password + '\'' +
            ", name='" + name + '\'' +
            ", age=" + age +
            '}';
  }
}
