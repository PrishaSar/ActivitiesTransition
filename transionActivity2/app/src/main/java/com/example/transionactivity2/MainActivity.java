package com.example.transionactivity2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void go(View v){
        Intent i = new Intent(this, secondActivity.class);
        Bundle b = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();

        startActivity(i,b);
    }
}