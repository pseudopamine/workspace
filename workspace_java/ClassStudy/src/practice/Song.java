package practice;

import java.util.Arrays;

public class Song {
  String title;
  String artist;
  String album;
  int year;
  String[] composer;

  public void setAllInfo(String title, String artist, String album, int year, String[] composer){
    this.title = title;
    this.artist = artist;
    this.album = album;
    this.year = year;
    this.composer = composer;
  }

  public void printInfo(){
    System.out.println("노래 제목 : " + title);
    System.out.println("가    수 : " + artist);
    System.out.println("앨범 제목 : " + album);
    System.out.println("발표 연도 : " + year);
    System.out.println("작 곡 가 : " + Arrays.toString(composer));
  }
}
