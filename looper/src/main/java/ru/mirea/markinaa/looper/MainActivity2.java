package ru.mirea.markinaa.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import java.util.HashMap;

import ru.mirea.markinaa.looper.databinding.ActivityMain2Binding;
import ru.mirea.markinaa.looper.databinding.ActivityMainBinding;

public class MainActivity2 extends AppCompatActivity {

    private ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Handler mainThreadHandler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg2){
                Log.d(MainActivity2.class.getSimpleName(), "This is result: " +
                        msg2.getData().getString("result"));
            }
        };

        SecondLooper secondLooper = new SecondLooper(mainThreadHandler);
        secondLooper.start();

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Message msg2 = Message.obtain();
                Bundle bundle2 = new Bundle();
                bundle2.putString("KEY2", binding.editTextAge.getText().toString());
                msg2.setData(bundle2);
                secondLooper.handler.sendMessage(msg2);
            }
        });
    }
}