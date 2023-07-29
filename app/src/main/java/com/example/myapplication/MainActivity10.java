package com.example.myapplication;
        import android.app.NotificationChannel;
        import android.app.NotificationManager;
        import android.app.PendingIntent;
        import android.app.TimePickerDialog;
        import android.app.DatePickerDialog;
        import android.content.Intent;
        import android.os.Build;
        import android.widget.TimePicker;
        import android.widget.DatePicker;
        import androidx.appcompat.app.AppCompatActivity;
        import java.util.Calendar;
        import android.content.DialogInterface;
        import androidx.appcompat.app.AlertDialog;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.core.app.NotificationCompat;
        import androidx.fragment.app.DialogFragment;
        import androidx.fragment.app.FragmentManager;

        import android.os.Bundle;
        import android.view.MenuInflater;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.PopupMenu;
        import android.widget.Toast;

public class MainActivity10 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
        Button b1;
        EditText e1,e3;
        e1 = findViewById(R.id.timeEditText);
        e3 = findViewById(R.id.dateEditText);
        b1 = findViewById(R.id.button);

        e1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity10.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                e1.setText(hourOfDay + ":" + minute);
                            }
                        }, hour, minute, true);
                timePickerDialog.show();

            }
        });

        e3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity10.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear,int dayOfmonth) {
                                e3.setText(dayOfmonth + "-" + (monthOfYear+1)+"-"+year);
                            }
                        }, year, month, day);
                datePickerDialog.show();

            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity10.this);

                builder.setMessage("Do you want to notify?");


                builder.setTitle("Alert !");


                builder.setCancelable(false);

                builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {

                    sendNotification();
                });

                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {

                    dialog.cancel();
                });
                AlertDialog alertDialog = builder.create();
                builder.show();
            }
        });
    }

    public void sendNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this, "default_channel")
                        .setSmallIcon(R.drawable.ic_launcher_foreground) //set icon for notification
                        .setContentTitle("Notifier") //set title of notification
                        .setContentText("Alert Notification for your pet care visit")//this is notification message
                        .setAutoCancel(true) // makes auto cancel of notification
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT); //set priority of notification

        Intent notificationIntent = new Intent(this, MainActivity10.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //notification message will get at NotificationView
        notificationIntent.putExtra("message", "Alert Notification for your pet care visit");

        Intent intent = new Intent(this, MainActivity10.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE);

        builder.setContentIntent(pendingIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(getApplicationContext().NOTIFICATION_SERVICE);

        // Create the notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default_channel", "Default Channel", NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }

        manager.notify(0, builder.build());
    }

    public void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My Notification Channel";
            String description = "My Notification Channel Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("MyNotificationChannelId", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }



}