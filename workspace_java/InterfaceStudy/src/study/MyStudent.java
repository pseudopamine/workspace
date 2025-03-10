package study;

public class MyStudent implements StudentUtil {

  //매개변수로 들어온 학생의 총점을 리턴
  @Override
  public int getTotalScore(Student student) {
    int sum = student.getKorScore() + student.getMathScore() + student.getEngScore();
    return sum;
  }

  @Override
  public Student getHighScoreStudent(Student student1, Student student2) {
    return getTotalScore(student1) > getTotalScore(student2) ? student1 : student2;
  }

  //학생 여러명을 매개변수로 받아, 또 다른 매개변수로
  //받은 학생의 이름과 일치하는 학생의 점수 등급을 문자열로 리턴
  //학생의 점수 등급은 총점의 평균으로 계산.
  //만약, 매개변수로 받은 이름과 일치하는 학생이 없다면
  //"학생 정보 없음"이라는 문자열을 리턴
  // 90 <= 총점 <= 100 -> A
  // 80 <= 총점 <= 89 -> B
  // 70 <= 총점 <= 79 -> C
  // 70 > 총점 -> D
  @Override
  public String getGradeByStudentName(Student[] students, String name) {
    String grade = "학생 정보 없음";

    for(int i = 0 ; i < students.length ; i++){
      if(students[i].getName().equals(name)){
        double avg = getTotalScore(students[i]) / 3.0;
        grade = getGradeByAvg(avg);
        break;
      }
    }
    return grade;
    }


  //평균점수로 등급을 문자열로 리턴하는 메서드
  public String getGradeByAvg(double avg){
    String grade = "";

    if(avg >= 90 && avg <= 100){
      grade = "A";
    }
    else if(avg >= 80){
      grade = "B";
    }
    else if(avg >= 70){
      grade = "C";
    }
    else{
      grade = "D";
    }
    return grade;
  }


  //매개변수로 학생 여러명을 받아,
  //해당 학생들의 총점을 배열로 리턴
  //예를 들어 매개변수로 학생 3명이 들어오고,
  //각각의 학생의 총점이 270, 280, 250점 이면
  //270, 280, 250의 값을 갖는 배열을 리턴해야 함
  @Override
  public int[] getTotalScoresToArray(Student[] students) {
    int[] resultArr = new int[students.length];

    for(int i = 0 ; i < students.length ; i++){
      resultArr[i] = getTotalScore(students[i]);
    }

    return resultArr;
  }
}
