package study;
//아래 요구사항에 맞는
//메소드를 정의 하시오.

//메소드를 완성했으면
//MyStudent클래스에서 메소드를 구현해보세요.
public interface StudentUtil {
  //매개변수로 들어온 학생의 총점을 리턴
  //메서드명 : getTotalScore
  public int getTotalScore(Student student);

  //매개변수로 들어온 두 학생 중
  //총점이 높은 학생을 리턴
  //메서드명 : getHighScoreStudent
  public Student getHighScoreStudent(Student student1, Student student2);

	//학생 여러명을 매개변수로 받아, 또 다른 매개변수로 
	//받은 학생의 이름과 일치하는 학생의 점수 등급을 문자열로 리턴
	//학생의 점수 등급은 총점의 평균으로 계산.
	//만약, 매개변수로 받은 이름과 일치하는 학생이 없다면
	//"학생 정보 없음"이라는 문자열을 리턴
	// 90 <= 총점 <= 100 -> A
	// 80 <= 총점 <= 89 -> B
	// 70 <= 총점 <= 79 -> C
	// 70 > 총점 -> D
	//메소드명: getGradeByStudentName
  public String getGradeByStudentName(Student[] students, String name);
	
	
	//매개변수로 학생 여러명을 받아,
	//해당 학생들의 총점을 배열로 리턴
	//예를 들어 매개변수로 학생 3명이 들어오고,
	//각각의 학생의 총점이 270, 280, 250점 이면
	//270, 280, 250의 값을 갖는 배열을 리턴해야 함
	//메소드명: getTotalScoresToArray
  public int[] getTotalScoresToArray(Student[] students);
	
	
}
