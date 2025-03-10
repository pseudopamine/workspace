package com.green.First;

public class Board {
  private int boardNum;
  private String title;
  private String writer;
  private int readCnt;
  private String content;

  public Board(int boardNum, String title, String writer, int readCnt, String content) {
    this.boardNum = boardNum;
    this.title = title;
    this.writer = writer;
    this.readCnt = readCnt;
    this.content = content;
  }

  public int getBoardNum() {
    return boardNum;
  }

  public void setBoardNum(int boardNum) {
    this.boardNum = boardNum;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getWriter() {
    return writer;
  }

  public void setWriter(String writer) {
    this.writer = writer;
  }

  public int getReadCnt() {
    return readCnt;
  }

  public void setReadCnt(int readCnt) {
    this.readCnt = readCnt;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }
}
