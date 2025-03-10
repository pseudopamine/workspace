package practice;

public class Student {
  private String name;
  private int korScore;
  private int engScore;
  private int totalScore;

  public Student(String name, int korScore, int engScore) {
    this.name = name;
    this.korScore = korScore;
    this.engScore = engScore;
    this.totalScore = korScore + engScore;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getKorScore() {
    return korScore;
  }

  public void setKorScore(int korScore) {
    this.korScore = korScore;
  }

  public int getEngScore() {
    return engScore;
  }

  public void setEngScore(int engScore) {
    this.engScore = engScore;
  }

  public int getTotalScore() {
    return totalScore;
  }

  public void setTotalScore(int totalScore) {
    totalScore = korScore + engScore;
    this.totalScore = totalScore;
  }

  @Override
  public String toString() {
    return "Student{" +
            "name='" + name + '\'' +
            ", korScore=" + korScore +
            ", engScore=" + engScore +
            ", totalScore=" + totalScore +
            '}';
  }
}
