package com.example.mb.lubantest;

public class Student {
    private String name = null;

    public Student(String name) {
        this.name = name;
    }

    public void setNamee(String name) {
        this.name = name;
    }

    private int calcADD(int a, int b) {
        return a + b;
    }
    private int useCalculator(int a,int b){
        return new Calculator().add(a,b);
    }
    public void callHelp(int a,int b){
        new SuperCalculator().add(a,b,this);
    }

 public void fillBlank(int a, int b) {
        int result = useCalculator(a, b);
        System.out.print(name + "=" + result);
    }
}