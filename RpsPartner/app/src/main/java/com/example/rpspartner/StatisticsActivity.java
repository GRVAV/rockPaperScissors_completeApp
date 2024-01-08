package com.example.rpspartner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class StatisticsActivity extends AppCompatActivity {

    RPSDatabase DBHelper;
    String output;
    TextView outputLbl;
    TableLayout outputTbl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

        }

        DBHelper = new RPSDatabase();
        outputLbl = findViewById(R.id.txtOutput_lbl);
        outputTbl= findViewById(R.id.tbllay_Output);

        displayOutput();

    }

    public void displayOutput(){

        ArrayList<PlayerRecord> records =  DBHelper.getRecord();

        for (PlayerRecord record : records) {
            TableRow row = new TableRow(this);
            // Add views to the row...
            outputTbl.addView(row);


            TextView winColumn  = new TextView(this);
            winColumn.setText(String.valueOf(record.getWin()));
            winColumn.setTextAppearance(android.R.style.TextAppearance_Medium);

            TextView loseColumn = new TextView(this);
            loseColumn.setText(String.valueOf(record.getLose()));
            loseColumn.setTextAppearance(android.R.style.TextAppearance_Medium);

            TextView drawColumn = new TextView(this);
            drawColumn.setText(String.valueOf(record.getDraw()));
            drawColumn.setTextAppearance(android.R.style.TextAppearance_Medium);

            TextView scoredOnColumn = new TextView(this);
            scoredOnColumn.setText(String.valueOf(record.getScoredOn()));
            scoredOnColumn.setTextAppearance(android.R.style.TextAppearance_Medium);

            row.addView(winColumn);
            row.addView(loseColumn);
            row.addView(drawColumn);
            row.addView(scoredOnColumn);

        }



    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }

        return true;
    }
}