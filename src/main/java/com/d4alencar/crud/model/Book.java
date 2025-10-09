package com.d4alencar.crud.model;

public class Book {
  private int id;
  private String title;
  private int year;
  private String author;

  public Book(int id, String title, int year, String author){
    this.id = id;
    this.title = title;
    this.year = year;
    this.author = author;
  }

  public int getId() {return id;}
  public String getTitle() {return title;}
  public int getYear() {return year;}
  public String getAuthor() {return author;}
}
