package com.example.rpspartner;

import android.annotation.SuppressLint;
import android.app.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;


public class RPSDatabase extends Application {

        private double wins = 0.0;
        private double loses = 0.0;
        private double draws = 0.0;
        private LocalDateTime scoredOn;
        private PlayerRecord player_Record = new PlayerRecord();


        private static final String DB_NAME = "player_score_record";
        private static final int DB_VERSION = 1;
        private SQLiteOpenHelper helper;


        @Override
        public void onCreate() {

            helper = new SQLiteOpenHelper(this, DB_NAME, null, DB_VERSION) {
                @Override
                public void onCreate(SQLiteDatabase db) {
                    db.execSQL("CREATE TABLE IF NOT EXISTS tblplayer_record (" +
                            "player_wins REAL, " +
                            "player_loses REAL, " +
                            "player_draws REAL, " +
                            "player_scoredOn DATETIME )");
                }

                @Override
                public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

                }
            };


            super.onCreate();

        }

        public void addRecord (double player_wins, double player_loses, double player_draws , LocalDateTime player_scoredOn){
            SQLiteDatabase db =  helper.getWritableDatabase();
            db.execSQL("INSERT INTO tblplayer_record (player_wins , player_loses , player_draws , player_scoredOn ) "+
                    "VALUES (" + player_wins + "," + player_loses +  player_draws + "," + player_scoredOn + ")");

        }

        public ArrayList<PlayerRecord> getRecord(){


            ArrayList<PlayerRecord> playerRecordsRetrieved = new ArrayList<>();
            SQLiteDatabase db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM tblplayer_record",null);

            while(cursor.isAfterLast()==false){
                PlayerRecord playerRecord = new PlayerRecord();

                playerRecord.Win(cursor.getDouble(0));
                playerRecord.Lose(cursor.getDouble(1));
                playerRecord.Draw(cursor.getDouble(2));
                @SuppressLint("Range") String dateTimeString = cursor.getString(cursor.getColumnIndex("player_scoredOn"));
                playerRecord.ScoredOn(LocalDateTime.parse(dateTimeString, DateTimeFormatter.ISO_LOCAL_DATE_TIME));

                playerRecordsRetrieved.add(playerRecord);

            }

            cursor.close();

            return playerRecordsRetrieved;
        }

}
