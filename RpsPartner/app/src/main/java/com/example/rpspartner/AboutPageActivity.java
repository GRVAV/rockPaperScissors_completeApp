package com.example.rpspartner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_page);


        TextView textView = (TextView) findViewById(R.id.txtAboutText);

        textView.setText("Hi Everyone , \n Welcome to My application , it's not complicated to use and hope you enjoy");



    }
}