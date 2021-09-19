package com.example.notes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class SettingActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("setting", MODE_PRIVATE);
        int color = sharedPreferences.getInt("test1", 0);
        Log.d("MyLog", "color = " + color + " red = " + R.color.red);
        switch (color){

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
        setContentView(R.layout.activity_setting);

        mToolbar = findViewById(R.id.toolbarNotesSetting);
        mToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }
}
