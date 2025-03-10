public class Switch_2 {
  public static void main(String[] args) {
    String a = "java";

    switch(a){
      case "남" :
        System.out.println("남성입니다.");
        break;
      case "여" :
        System.out.println("여성입니다.");
        break;
      default: //위에 나열된 결과 나머지 전부다
        System.out.println("잘못된 문자열입니다.");
    }
  }
}
