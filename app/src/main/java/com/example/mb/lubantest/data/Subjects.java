package com.example.mb.lubantest.data;

public class Subjects {
    private String title, year, id;

    public Subjects(String title, String year, String id) {
        this.title = title;
        this.year = year;
        this.id = id;
    }
  public String getTitle(){return title;}

    public String getId() {
        return id;
    }

    public String getYear() {
        return year;
    }
}
