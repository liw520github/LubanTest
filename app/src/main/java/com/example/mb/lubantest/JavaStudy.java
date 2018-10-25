package com.example.mb.lubantest;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.util.Date;

public class JavaStudy<T> {
    private T[] array;

    public void setArray(T[] array) {
        this.array = array;
    }

    public T[] getArray() {
        return array;
    }

    public static void main(String arg[]) {
        JavaStudy<String> a = new JavaStudy<>();
        String array[] = {"dff", "ewre", "ewrewr"};
        a.setArray(array);
        for (int i =0;i<a.getArray().length;i++){
            System.out.print(a.getArray()[i]+"\n");
        }
    }

}
