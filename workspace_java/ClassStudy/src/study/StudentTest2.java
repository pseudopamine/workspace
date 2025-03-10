package study;

public class StudentTest2 {
  public static void main(String[] args) {
    Student stu1 = new Student();
    stu1.printInfo();

    //학생 객체 stu1의 데이터 변경
    stu1.name = "Lee";
    stu1.age = 30;
    stu1.score = 90;
    stu1.printInfo();

    System.out.println();

    Student stu2 = new Student();

    //stu2 객체의 데이터 변경
    stu2.setName("Hong");
    stu2.setAge(35);
    stu2.setScore(80);
    stu2.printInfo();

    System.out.println();

    //stu3 객체의 데이터 변경
    Student stu3 = new Student();

    stu3.setAll("국연수", 17, 98);
    stu3.printInfo();

  }
}
