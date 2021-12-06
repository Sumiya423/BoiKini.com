package com.example.android.logindemo.activity;

import android.os.Bundle;


import android.support.v7.app.AppCompatActivity;

import com.example.android.logindemo.R;

public class DeveloperActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        setTitle("App Developer");
    }
}