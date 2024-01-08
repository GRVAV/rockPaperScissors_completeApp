package com.example.rpspartner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class menuActivity extends AppCompatActivity {

    Button btn_Play;
    Button btn_About;
    Button btn_Settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

         btn_Play = findViewById(R.id.btnPlay);
         btn_About = findViewById(R.id.btnAbout);
         btn_Settings =findViewById(R.id.btnSettings);


        View.OnClickListener btnMenuListenerSetter = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.btnPlay:
                        Intent Play = new Intent(menuActivity.this,MainActivity.class);
                        startActivity(Play);

                        break;
                    case R.id.btnAbout:
                        Intent About = new Intent(menuActivity.this,AboutPageActivity.class);
                        startActivity(About);
                        break;
                    case R.id.btnSettings:
                        Intent Settings = new Intent(menuActivity.this,SettingsActivity.class);
                        startActivity(Settings);
                    default:
                        break;
                }
            }
        };
        btn_Play.setOnClickListener(btnMenuListenerSetter);
        btn_About.setOnClickListener(btnMenuListenerSetter);
        btn_Settings.setOnClickListener(btnMenuListenerSetter);
    }
}