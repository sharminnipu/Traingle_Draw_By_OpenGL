package com.sharminnipu.traingle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    MyGlOpenView glOpenView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        glOpenView= new MyGlOpenView(this);
        setContentView(glOpenView);
    }
}