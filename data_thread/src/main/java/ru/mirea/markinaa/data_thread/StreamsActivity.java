package ru.mirea.markinaa.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import ru.mirea.markinaa.data_thread.databinding.ActivityMainBinding;
import ru.mirea.markinaa.data_thread.databinding.ActivityStreamsBinding;

public class StreamsActivity extends AppCompatActivity {

    private ActivityStreamsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStreamsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final Runnable runn1 = new Runnable(){
            public void run(){
                binding.textStreams.setText("runOnUiThread() позволяет обращаться напрямую к " +
                        "элементам интерфейса экрана. На вход принимате и запускает указанное " +
                        "действие в потоке пользовательского интерфейса.");
            }
        };

        final Runnable runn2 = new Runnable() {
            @Override
            public void run() {
                binding.textStreams.setText("Метод post() принимает и запускает указанное " +
                        "действие, однако этот метод вызывается непосредственно у элемента UI.");
            }
        };

        final Runnable runn3 = new Runnable() {
            @Override
            public void run() {
                binding.textStreams.setText("Часто из-за особенностей работы android " +
                        "системы и sdk, нам необходимо подождать, когда определённая " +
                        "часть системы будет сконфигурирована или произойдёт какое-то " +
                        "необходимое нам событие. Зачастую это является костылём, но иногда " +
                        "без них никак, особенно в условиях дедлайнов. Метод postDelayed() принимает " +
                        "на вход указанное децствие, и выполняет его через указанное время." +
                        " При этом, если после этого метода указано какое-то действие, оно " +
                        "будет выполнено перед методом postDelayed(), после " +
                        "выполнения метода postDelayed() оно будет также выполнено повторно.");
            }
        };

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(7);
                    binding.textStreams.postDelayed(runn3, 6000);
                    binding.textStreams.post(runn2);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        t.start();

    }
}