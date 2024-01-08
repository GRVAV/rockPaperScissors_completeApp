package com.example.rpspartner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Setting up the DatabaseClass
    RPSDatabase DBHelper;

    //data variables
    private int playerScore =0;
    private int compScore =0;
    private int draws=0;
    private LocalDateTime scoredOn;
    private static int rock =1;
    private static int paper = 2;
    private static int scissor = 3;
    private static String rockStr = String.valueOf(R.string.rock);
    private static String paperStr = String.valueOf(R.string.paper);
    private static String scissorStr = String.valueOf(R.string.scissor);
    private String playerChoice;
    private String compChoice;
    ImageView playerImg;
    ImageView compImg;




    //buttons
    private Button menu;
    private Button reset;
    private ImageButton rockBtn;
    private ImageButton paperBtn;
    private ImageButton scissorBtn;


    //text views
    private TextView playerscore_lbl;
    private TextView compscore_lbl;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        menu = (Button)findViewById(R.id.btnMenu);
        reset = (Button)findViewById(R.id.btnReset);
        rockBtn = (ImageButton) findViewById(R.id.btnRock);
        paperBtn = (ImageButton) findViewById(R.id.btnPaper);
        scissorBtn = (ImageButton) findViewById(R.id.btnScissor);


        playerscore_lbl = (TextView) findViewById(R.id.txt_playerscore);
        compscore_lbl = (TextView) findViewById(R.id.txt_compscore);

        playerscore_lbl.setText("-");
        compscore_lbl.setText("-");

        playerImg = (ImageView) findViewById(R.id.imgPlayerMove);
        compImg = (ImageView) findViewById(R.id.imgCompMove);

        DBHelper = new RPSDatabase();


        View.OnClickListener setListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnMenu:
                        Intent intent = new Intent(MainActivity.this, menuActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.btnReset:
                        btnReset_click();
                        break;
                    case R.id.btnRock:
                        playerChoice= playerTurn(rock);
                        System.out.println(playerChoice);
                        compChoice=computerTurn();
                        System.out.println(compChoice);
                        CheckWinner();
                        break;
                    case R.id.btnPaper:
                        playerChoice=playerTurn(paper);
                        System.out.println(playerChoice);
                        compChoice=computerTurn();
                        System.out.println(compChoice);
                        CheckWinner();
                        break;
                    case R.id.btnScissor:
                        playerChoice=playerTurn(scissor);
                        System.out.println(playerChoice);
                        compChoice=computerTurn();
                        System.out.println(compChoice);
                        CheckWinner();
                        break;
                    default:
                        break;
                }
            }
        };

        menu.setOnClickListener(setListener);
        reset.setOnClickListener(setListener);
        rockBtn.setOnClickListener(setListener);
        paperBtn.setOnClickListener(setListener);
        scissorBtn.setOnClickListener(setListener);


    }

    public void CheckWinner(){
        if(((playerChoice==rockStr)&&(compChoice==paperStr))||((playerChoice==paperStr)&&(compChoice==scissorStr))||
                ((playerChoice==scissorStr)&&(compChoice==rockStr)))
        {

            Toast.makeText(this,"Computer Won",Toast.LENGTH_SHORT).show();
            compScore++;


            scoredOn = LocalDateTime.now();

            compscore_lbl.setText(compScore+"");
        }else if(((compChoice==rockStr)&&(playerChoice==paperStr))||((compChoice==paperStr)&&(playerChoice==scissorStr))||
                ((compChoice==scissorStr)&&(playerChoice==rockStr)))
        {

            Toast.makeText(this,"Player Won",Toast.LENGTH_SHORT).show();
            playerScore++;

            playerscore_lbl.setText(playerScore+"");
        }else{
            Toast.makeText(this,"Game Draw",Toast.LENGTH_SHORT).show();
            draws++;
        }

        //((RPSDatabase)getApplication()).addRecord(playerScore,compScore,draws,scoredOn);
        //DBHelper.addRecord(playerScore,compScore,draws,scoredOn);

    }

    public String playerTurn(int turn){
        switch(turn){
            case 1:
                playerImg.setImageResource(R.drawable.rockopt);
                return rockStr;
            case 2:
                playerImg.setImageResource(R.drawable.paperopt);
                return paperStr;
            case 3:
                playerImg.setImageResource(R.drawable.scissoropt);
                return scissorStr;
            default:
                return "error in player choice";
        }
    }

    public String computerTurn(){
        Random r = new Random();
        int turn = 0;

        while(turn==0){
            turn= r.nextInt(4);
        }

        switch(turn){
            case 1:
                compImg.setImageResource(R.drawable.rockopt);
                return rockStr;
            case 2:
                compImg.setImageResource(R.drawable.paperopt);
                return paperStr;
            case 3:
                compImg.setImageResource(R.drawable.scissoropt);
                return scissorStr;
            default:
                return "error in comp choice";
        }
    }

    public void btnReset_click(){
        playerScore = 0;
        compScore=0;

        playerscore_lbl.setText(playerScore+"");
        compscore_lbl.setText(compScore+"");

        playerImg.setImageResource( R.drawable.rock_paper_scissors);
        compImg.setImageResource( R.drawable.rock_paper_scissors);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        outState.putInt("player_Score",playerScore);
        outState.putInt("comp_Score",compScore);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {

        playerScore = savedInstanceState.getInt("player_Score");
        compScore = savedInstanceState.getInt("comp_Score");

        playerscore_lbl.setText(playerScore+"");
        compscore_lbl.setText(compScore+"");

        super.onRestoreInstanceState(savedInstanceState);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.maingamemenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){

            case R.id.menu_statisticsItem:
                //reset the values to default
                Intent statistics = new Intent(MainActivity.this,StatisticsActivity.class);
                startActivity(statistics);
                break;
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }

        return true;
    }


}