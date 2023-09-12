package ru.mirea.markinaa.serviceapp;

import static android.Manifest.permission.FOREGROUND_SERVICE;
import static android.Manifest.permission.POST_NOTIFICATIONS;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ru.mirea.markinaa.serviceapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private boolean isWork = false;
    private int PermissionCode = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imageButtonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isWork) {
                    binding.imageButtonPlay.setImageDrawable(getResources().getDrawable(R.drawable
                            .ic_gallery_fragment_pause, null));
                    binding.imageView3.setImageDrawable(getResources().getDrawable(R.drawable
                            .kremlin, null));
                    isWork = true;
                    Intent serviceIntent = new Intent(MainActivity.this,
                            PlayerService.class);
                    ContextCompat.startForegroundService(MainActivity.this,	serviceIntent);
                } else {
                    binding.imageButtonPlay.setImageDrawable(getResources().getDrawable(R.drawable
                            .ic_play_arrow, null));
                    isWork = false;
                    stopService(
                            new	Intent(MainActivity.this,	PlayerService.class));
                }

            }
        });

        if (ContextCompat.checkSelfPermission(this, POST_NOTIFICATIONS) == PackageManager
                .PERMISSION_GRANTED){
            Log.d(MainActivity.class.getSimpleName().toString(),	"Разрешения получены");
        }	else	{
            Log.d(MainActivity.class.getSimpleName().toString(),	"Нет разрешений!");
            ActivityCompat.requestPermissions(this,	new	String[]{POST_NOTIFICATIONS,
                    FOREGROUND_SERVICE},	PermissionCode);
        }

    }

}