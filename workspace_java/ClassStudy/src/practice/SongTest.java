package practice;

public class SongTest {
  public static void main(String[] args) {
    Song album1 = new Song();
    String[] composer = {"김창완", "김형석", "이지은"};

    album1.setAllInfo("너의 의미", "아이유", "꽃갈피", 2015, composer);

    album1.printInfo();
  }
}
