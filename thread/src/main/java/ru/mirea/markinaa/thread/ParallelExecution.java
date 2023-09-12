package ru.mirea.markinaa.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ru.mirea.markinaa.thread.databinding.ActivityParallelExecutionBinding;


public class ParallelExecution extends AppCompatActivity {

    private ActivityParallelExecutionBinding binding;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityParallelExecutionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable(){
                    public void run(){
                        int numberThread = counter++;
                        Log.d("ThreadProject", String.format("Запущен поток № %d студентом группы № %s номер по списку № %d", numberThread, "БСБО-ХХ-ХХ", -1));
                        long endTime = System.currentTimeMillis() + 20 * 1000;
                        while(System.currentTimeMillis() < endTime){
                            synchronized (this){
                                try{
                                    wait(endTime - System.currentTimeMillis());
                                    Log.d(MainActivity.class.getSimpleName(), "EndTimeЖ "
                                            + endTime);
                                }catch (Exception e){
                                    throw new RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject", "Выполнен поток № " + numberThread);
                        }
                    }
                }).start();
            }
        });
    }
}