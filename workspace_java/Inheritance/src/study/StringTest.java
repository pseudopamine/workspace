package study;

public class StringTest {
  public static void main(String[] args) {
    String s1 = "simple";
    String s2 = "java";

    //concat() : 두 문자열을 나열한 결과값을 리턴
    String s3 = s1.concat(s2);
    System.out.println(s3);

    //length() : 문자열의 길이를 정수로 리턴
    int len = s3.length();

    //String.valueof() : 매개변수로 들어간 데이터를 문자열로 바꿔서 리턴
    String result = String.valueOf(10.5);

    String data = "hi java";

    //substring() : 일부 문자열을 추출할 때 사용
    String r1 = data.substring(3);
    System.out.println(r1);

    //1번째 자리에서 4번째 자리 전까지
    String r2 = data.substring(1, 4);
    System.out.println(r2);

    String data2 = "a,b,c";
    //split() : 매개변수로 전달된 문자열을 기준으로 문자열을 조각내서
    //조각낸 데이터를 리턴, 조각냈기 때문에 배열형태
    String[] r3 = data2.split(",");

    //replace() : ,를 -로 교체
    String r4 = data2.replace(",", "-");
    System.out.println(r4);

  }
}
