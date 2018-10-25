package com.example.mb.lubantest.data;

import java.util.List;

public class Movie {
    private String title;
    private List<Subjects>subjects;

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public String getTitle() {
        return title;
    }
}
