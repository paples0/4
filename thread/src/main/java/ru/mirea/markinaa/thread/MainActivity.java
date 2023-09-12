package ru.mirea.markinaa.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

import ru.mirea.markinaa.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private int averageCouples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Thread mainThread = Thread.currentThread();
        binding.textView.setText("Имя текущего потока: " + mainThread.getName());

        mainThread.setName("Мой номер группы: XX, номер по списку: XX, мой любимый фильм: XX");
        binding.textView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(), "Stack: " + Arrays.toString(mainThread
                .getStackTrace()));
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long endTime = System.currentTimeMillis() + 20 * 1000;

                while(System.currentTimeMillis() < endTime){
                    synchronized (this){
                        try{
                            wait(endTime - System.currentTimeMillis());
                        } catch (Exception e){
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });
        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ParallelExecution.class);
                startActivity(intent);
            }
        });
        binding.button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_sec = new Intent(MainActivity.this,
                        CountingCouples.class);
                new	Thread(new	Runnable()	{
                    public	void run()	{
                        synchronized	(this)	{
                            try	{
                                averageCouples = Integer.parseInt(binding.editTextCouples
                                        .getText().toString()) / Integer.parseInt(binding
                                        .editTextStudyDay.getText().toString());
                            }	catch	(Exception	e)	{
                                throw	new	RuntimeException(e);
                            }
                        }
                        intent_sec.putExtra("averageCouples", averageCouples);
                        startActivity(intent_sec);
                    }
                }).start();


            }
        });
        Log.d(MainActivity.class.getSimpleName(), "Group " + mainThread.getThreadGroup());
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();
    }


}