package com.example.mb.lubantest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.mb.lubantest.R;
import com.example.mb.lubantest.data.Movie;
import com.example.mb.lubantest.data.Subjects;
import com.example.mb.lubantest.http.ApiMethods;
import com.example.mb.lubantest.http.ApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private static final int PHOTO_CAMERA = 1000;
    private static final int DELETE_CODE = 1002;
    private static final int SELECT_IMAGE_REQUEST = 0x0011;
    private ArrayList<String> mImageList = new ArrayList<>();
    private ArrayList<String> mRuquestList = new ArrayList<>();
    private static  final  String TAG =MainActivity.class.getName();

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        first();
        second();
    }
    private void second(){
        Observer<Movie>observer =new Observer<Movie>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Movie movie) {
Log.d(TAG,"onNext:"+movie.getTitle());
List<Subjects>list =movie.getSubjects();
for (Subjects subjects :list){
    Log.d(TAG,"onNext:"+subjects.getId()+","+subjects.getYear()+","+subjects.getTitle());
}
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
        ApiMethods.getTopMovie(observer,0,10);
    }

    //基本rxjava+retrofit
    private void first() {
        String baseUrl = "https://api.douban.com/v2/movie/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getTopMovie(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Movie>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(TAG, "onSubscrive:");
                    }

                    @Override
                    public void onNext(Movie movie) {
                        Log.d(TAG, "onNext: " + movie.getTitle());
                        List<Subjects> list = movie.getSubjects();
                        for (Subjects sub : list) {
                            Log.d(TAG, "onNext: " + sub.getId() + "," + sub.getYear() + "," + sub.getTitle());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: Over!");
                    }
                });
    }
}
