package ru.mirea.markinaa.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.concurrent.TimeUnit;

import ru.mirea.markinaa.data_thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.streams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StreamsActivity.class);
                startActivity(intent);
            }
        });

        final Runnable runn1 = new Runnable(){
            public void run(){
                binding.tvInfo.setText("runn1");
            }
        };

        final Runnable runn2 = new Runnable() {
            @Override
            public void run() {
                binding.tvInfo.setText("runn2");
            }
        };

        final Runnable runn3 = new Runnable() {
            @Override
            public void run() {
                binding.tvInfo.setText("runn3");
            }
        };

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    binding.tvInfo.postDelayed(runn3, 2000);
                    binding.tvInfo.post(runn2);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}