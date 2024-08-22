package com.example.alarmclock;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AlarmSave extends AppCompatActivity {
    Button btnHenGio, btnBamGio;
    ImageButton img;
    ArrayList<IfSaveTime> list;
    AlarmDAO alarmDAO;
    Button xoa;
    //long time;
    ArrayList<AlarmManager> alarmManagerArrayList;
    //AlarmManager alarmManager;
    //Calendar calendar;
    //PendingIntent pendingIntent;
    //private static final String CHANNEL_ID = "channel_id";
    //int pos;
    MyAddapterSaveTime addapter;
    private ListView lisview;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.activity_alarm_save);
        lisview = findViewById(R.id.Lisview2);
        alarmDAO = new AlarmDAO(this);
        //alarmDAO.xoa();
       xoa=findViewById(R.id.buttonX);
       xoa.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AlertDialog.Builder builder = new AlertDialog.Builder(AlarmSave.this);
               builder.setTitle("Bạn muốn xóa tất cả báo thức không");
               builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                      alarmDAO.xoa();


                       lisview.setAdapter(null);
                   }
               });
               builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       dialogInterface.cancel();
                   }
               });
               AlertDialog alert = builder.create();
               alert.show();
           }
       });

//       list.add(new IfSaveTime("05:15","Báo thức"));
//       list.add(new IfSaveTime("11:15","Báo thức"));
        //Intent i=new Intent(); i = getIntent();
        //if(i!=null) {
//           Bundle b = new Bundle();
//           b = i.getExtras();
//           int g = b.getInt("gio");
//           int p = b.getInt("put");
       // alarmDAO.sx();
        list = new ArrayList<>();
         list = alarmDAO.getAll() ;
        //list = handlePinned(list);
        addapter = new MyAddapterSaveTime(alarmDAO, list, this, new IClickItemListener() {
            @Override
            public void onClickItem(IfSaveTime saveTime) {
                Intent i = new Intent(AlarmSave.this, MainScreen.class);
                int id = saveTime.getId();
                i.putExtra("id", id);
                i.putExtra("ten", saveTime.getText());
                startActivity(i);

            }
        });
        //  String time = String.valueOf(g) + ":" + String.valueOf(p);
//           String t = b.getString("ten");
//           list.add(new IfSaveTime(g,p, t));
        lisview.setAdapter(addapter);
        img = findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(AlarmSave.this, MainScreen.class);
 //               createNotificationChannel();
//                alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//                calendar = Calendar.getInstance();
//                calendar.set(Calendar.HOUR_OF_DAY, ifSaveTime.getGio());
//                calendar.set(Calendar.MINUTE, ifSaveTime.getPhut());
//                Intent i2 = new Intent(this, AlarmReceiver.class);
//
//                pendingIntent = PendingIntent.getBroadcast(this, 10000, i2, 0);
//
//                time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
//                if (System.currentTimeMillis() > time) {
//                    // setting time as AM and PM
//                    if (Calendar.AM_PM == 0)
//                        time = time + (1000 * 60 * 60 * 12);
//                    else
//                        time = time + (1000 * 60 * 60 * 24);
//                }
//
//                // Alarm rings continuously until toggle button is turned off
//                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 0, pendingIntent);
//                //
//                //  alarmManagerArrayList.add(alarmManager);
//
//                Intent i = new Intent(AlarmSave.this,AlarmNotification.class);
//
//                TaskStackBuilder stackBuilder = TaskStackBuilder.create(AlarmSave.this);
//                stackBuilder.addNextIntentWithParentStack(i);
//
//                PendingIntent pendingIntent
//                        = (PendingIntent)stackBuilder.getPendingIntent(getNotificationid(),PendingIntent.FLAG_UPDATE_CURRENT);
//
//                NotificationCompat.Builder n = new NotificationCompat.Builder(AlarmSave.this,"CHANNEL_ID")
//                        .setSmallIcon(R.drawable.ic_baseline_access_alarms_24)
//                        .setContentTitle("Thông báo")
//                        .setContentText("thông tin ở đây nè!!!")
//                        .setAutoCancel(true)
//                        // .setContentIntent(pendingIntent)
//                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);
//
//                NotificationManagerCompat nC = NotificationManagerCompat.from(AlarmSave.this);
//                nC.notify(getNotificationid(),n.build());
                startActivity(a);
            }});
        alarmManagerArrayList=new ArrayList<>();
        //broadcast
        //    AlarmManager alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);
//        for (IfSaveTime ifSaveTime : list) {
//
//            if (ifSaveTime.isaBoolean() == true) {
//
//                createNotificationChannel();
//                alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//                calendar = Calendar.getInstance();
//                calendar.set(Calendar.HOUR_OF_DAY, ifSaveTime.getGio());
//                calendar.set(Calendar.MINUTE, ifSaveTime.getPhut());
//                Intent i2 = new Intent(this, AlarmReceiver.class);
//
//                pendingIntent = PendingIntent.getBroadcast(this, 10000, i2, 0);
//
//                time = (calendar.getTimeInMillis() - (calendar.getTimeInMillis() % 60000));
//                if (System.currentTimeMillis() > time) {
//                    // setting time as AM and PM
//                    if (Calendar.AM_PM == 0)
//                        time = time + (1000 * 60 * 60 * 12);
//                    else
//                        time = time + (1000 * 60 * 60 * 24);
//                }
//
//                // Alarm rings continuously until toggle button is turned off
//                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 0, pendingIntent);
//                //
//                 alarmManagerArrayList.add(alarmManager);
//
//
//
//            }
//        }
            btnBamGio = findViewById(R.id.button3);
            btnBamGio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(AlarmSave.this, CountdownClock.class);
                    startActivity(i);
                }
            });
            btnHenGio = findViewById(R.id.button1);
            btnHenGio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(AlarmSave.this, Timer.class);
                    startActivity(i);
                }
            });
    }
    /*private int getNotificationid() {
        return (int) new Date().getTime();
    }
    private void createNotificationChannel(){
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            CharSequence name = getString(R.string.channel_name);
            String d = getString(R.string.d);
            int a = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,name,a);
            channel.setDescription(d);
            NotificationManager nt = getSystemService(NotificationManager.class);
            nt.createNotificationChannel(channel);
        }
    }*/


// broadcast


//
//    private ArrayList<IfSaveTime> handlePinned(ArrayList<IfSaveTime> notesList) {
//        ArrayList<IfSaveTime> handledNoteList = new ArrayList<>();
//
//        for (IfSaveTime note: notesList) {
//            if (note.isPinned()){
//                handledNoteList.add(note);
//            }
//        }
//
//        for (Note note : notesList){
//            if(!note.isPinned()){
//                handledNoteList.add(note);
//            }
//        }

}