package com.example.notes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.notes.presentation.listNotes.ListNotesFragmentKotlin;

public class MainActivity extends AppCompatActivity implements ListNotesFragmentKotlin.CallbackSupportActionBar {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("setting", MODE_PRIVATE);
        int color = sharedPreferences.getInt("test1", 0);
        Log.d("MyLog", "color = " + color + " red = " + R.color.red);
        switch (color) {

            case R.color.red:
                setTheme(R.style.Red);
                break;
            case R.color.pink:
                setTheme(R.style.Pink);
                break;
            case R.color.purple:
                setTheme(R.style.Purple);
                break;
            case R.color.deep_purple:
                setTheme(R.style.deep_purple);
                break;
            case R.color.blue:
                setTheme(R.style.blue);
                break;
            case R.color.cyan:
                setTheme(R.style.cyan);
                break;
            case R.color.teal:
                setTheme(R.style.teal);
                break;
            case R.color.green:
                setTheme(R.style.green);
                break;
            case R.color.light_green:
                setTheme(R.style.light_green);
                break;
            case R.color.lime:
                setTheme(R.style.lime);
                break;
            case R.color.amber:
                setTheme(R.style.amber);
                break;
            case R.color.orange:
                setTheme(R.style.orange);
                break;
            case R.color.deep_orange:
                setTheme(R.style.deep_orange);
                break;
            case R.color.brown:
                setTheme(R.style.brown);
                break;
            case R.color.grey:
                setTheme(R.style.grey);
                break;
            case R.color.blue_grey:
                setTheme(R.style.blue_grey);
                break;

        }
        setContentView(R.layout.activity_main);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void onEvent(@NonNull Toolbar toolbar) {
        setSupportActionBar(toolbar);

    }
}