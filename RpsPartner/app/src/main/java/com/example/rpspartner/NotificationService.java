package com.example.rpspartner;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationService extends Service {
    private int counter;
    static final String CHANNEL_ID = "1";


    public NotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {
        //Toast toast = Toast.makeText(this, "Service is called after app is deactivated ", Toast.LENGTH_SHORT);

        final Timer timer = new Timer(true);

        final ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();


        //Step 1: we need to create Notification Channel
        createNotificationChannel();


        final Intent intent = new Intent(getApplicationContext(), MainActivity.class)
                .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        final PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_IMMUTABLE);


        if (networkInfo != null && networkInfo.isConnected()) {
            //download data from internet

            //counter = 0;
            timer.schedule(new TimerTask() {
                @Override
                public void run() {  //background thread
                    //counter++;
                    //toast.show();

                    try {
                        URL url = new URL("https://quotes.rest/qod?language=en");
                        InputStream inputStream = url.openStream();

                        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A"); //to return the entire string

                        String displayText;
                        if (scanner.hasNext()) {
                            displayText = scanner.next();
                        } else {
                            displayText = "";
                        }

                        String parsedDisplayText = new JSONObject(displayText).getJSONObject("contents")
                                .getJSONArray("quotes")
                                .getJSONObject(0)
                                .getString("quote");

                        //Step 2: set Icon, text, content, context for our Notification
                        final NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                                .setSmallIcon(R.drawable.ic_baseline_circle_notifications_24)
                                .setContentTitle("RPSPartner is missing you!!")
                                .setContentText(parsedDisplayText)
                                .setContentIntent(pendingIntent)  //click on notification => you go back to Main Activity
                                .setAutoCancel(true); //tap on notification => it disappears from notification panel

                        //Step 3: Displaying the Notification
                        int notificationId = 0;
                        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getApplicationContext());
                        notificationManagerCompat.notify(notificationId, notificationBuilder.build());

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } finally {
                        stopSelf();     //to stop the service
                    }


                    //timer.cancel(); //to stop the timer


                }
            }, 1000);

        }
        super.onCreate();

    }

        void createNotificationChannel(){

            String channelName = "RPS Channel1";
            String channelDescription = "First Channel for RPSPartner notification";

            //only for API 26+
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                NotificationChannel channel = new NotificationChannel(CHANNEL_ID,channelName, NotificationManager.IMPORTANCE_DEFAULT);
                channel.setDescription(channelDescription);

                NotificationManager manager = getSystemService(NotificationManager.class);
                manager.createNotificationChannel(channel);
            }
        }


}