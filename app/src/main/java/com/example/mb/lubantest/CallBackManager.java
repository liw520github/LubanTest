package com.example.mb.lubantest;

public class CallBackManager extends Thread{
    Callback callback;

    public CallBackManager(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void run() {
        super.run();
        for (int i =0;i<5;i++){
            try{
                Thread.sleep(300);
                System.out.print("图片下载中");
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        callback.callback("图片下载完成");
    }


}
