package com.example.mb.lubantest.http;
import com.example.mb.lubantest.data.Movie;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ApiMethods {
    public static void ApiSubscribe(Observable observable, Observer observer){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
    //获取top250的数据
    public static void getTopMovie(Observer<Movie>observer,int start,int count){
        ApiSubscribe(Api.getApiService().getTopMovie(start,count),observer);
    }

}
