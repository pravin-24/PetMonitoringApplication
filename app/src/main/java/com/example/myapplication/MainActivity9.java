package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.myapplication.R;

import java.text.DateFormat;
import java.util.Calendar;

public class MainActivity9 extends AppCompatActivity {
    private static final String CHANNEL_ID = "status_notification_channel";
    private static final int NOTIFICATION_ID = 1;
    private NotificationManagerCompat notificationManager;
    private TextView textViewTime;
    private TimePicker timePicker;
    private int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        textViewTime = findViewById(R.id.textViewTime);
        timePicker = findViewById(R.id.timePicker);

        notificationManager = NotificationManagerCompat.from(this);

        createNotificationChannel();
    }

    public void onSetTimeButtonClick(View view) {
        hour = timePicker.getCurrentHour();
        minute = timePicker.getCurrentMinute();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        updateTimeText(calendar);

        scheduleNotification(calendar);
    }

    private void updateTimeText(Calendar calendar) {
        String timeText = "Status notification set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(calendar.getTime());
        textViewTime.setText(timeText);
    }

    private void scheduleNotification(Calendar calendar) {
        Intent intent = new Intent(this, MainActivity9.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);


        long notificationTime = calendar.getTimeInMillis();
        long currentTime = Calendar.getInstance().getTimeInMillis();

        if (notificationTime <= currentTime) {
            notificationTime += 24 * 60 * 60 * 1000;
        }

        long delay = notificationTime - currentTime;

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        sendNotification();
                    }
                },
                delay
        );
    }


    private void createNotificationChannel() {
        // Create the NotificationChannel only for API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My App Notification Channel";
            String description = "This is my app notification channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void sendNotification() {
        // Create an intent that opens the MainActivity
        Intent intent = new Intent(this, MainActivity9.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);


        // Create the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.applogo)
                .setContentTitle("Notification")
                .setContentText("Feed Your PET !!!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        // Show the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
