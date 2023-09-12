package ru.mirea.markinaa.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.os.Looper;

import java.util.concurrent.TimeUnit;

public class SecondLooper  extends Thread{

    public Handler handler;
    private Handler maHandler;
    public SecondLooper(Handler maThreadHandler){
        maHandler = maThreadHandler;
    }
    public void run(){
        Log.d("SecondLooper", "run");
        Looper.prepare();
        handler = new Handler(Looper.myLooper()){
            public void handleMessage(Message msg2){
                String data2 = msg2.getData().getString("KEY2");
                Log.d("Looper get message: ", data2);

                Log.d("Sleep: ", data2);
                try {
                    TimeUnit.SECONDS.sleep(Integer.parseInt(data2));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("Don't sleep: ", data2);

                int count = data2.length();
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("result", String.format("Information is committed " +
                        "%s is %d", data2, count));
                message.setData(bundle);
                maHandler.sendMessage(message);
            }
        };
        Looper.loop();
    }

}
