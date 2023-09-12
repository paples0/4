package ru.mirea.markinaa.cryptoloader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;


import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.lang.invoke.MutableCallSite;
import java.security.InvalidParameterException;

import ru.mirea.markinaa.cryptoloader.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    public final String TAG = this.getClass().getSimpleName();
    private final int LoaderID = 1234;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CipherActivity.class);
                startActivity(intent);
            }
        });
    }

    public void	onClickButton(View	view)	{
        Bundle	bundle	=	new	Bundle();
        bundle.putString(MyLoader.ARG_WORD,	"mirea");
        LoaderManager.getInstance(this).initLoader(LoaderID,	bundle,	this);
    }

    @Override
    public	void onLoaderReset(@NonNull	Loader<String>	loader)	{
        Log.d(TAG,	"onLoaderReset");
    }
    @NonNull
    @Override
    public	Loader<String>	onCreateLoader(int	i,	@Nullable	Bundle	bundle)	{
        if	(i	==	LoaderID)	{
            Toast.makeText(this,	"onCreateLoader:"	+	i,	Toast.LENGTH_SHORT).show();
            return	new	MyLoader(this,	bundle);
        }
        throw	new	InvalidParameterException("Invalid	loader	id");
    }
    @Override
    public	void onLoadFinished(@NonNull Loader<String>	loader,	String	s)	{
        if	(loader.getId()	==	LoaderID)	{
            Log.d(TAG,"onLoadFinished:	" + s);
            Toast.makeText(this,	"onLoadFinished:	"	+	s,	Toast.LENGTH_SHORT).show();
        }
    }
}