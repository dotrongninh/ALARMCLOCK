package com.example.alarmclock;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CountdownClock extends AppCompatActivity {
    private ArrayList<infoCountdown> list;
    private ListView listView;
    private TextView textView;
    private ImageButton btnStart, btnPause, btnReset;
    private Button btnBaoThuc, btnHenGio;
    private long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;
    private int Seconds, Minutes, MilliSeconds;
    private boolean trangthai;
    private SharedPreferences sharedPreferences;
    int luot = 1;


    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown_clock);
        textView = findViewById(R.id.textView);
        listView = findViewById(R.id.list_view);
        btnStart = findViewById(R.id.btnAdd);
        btnReset = findViewById(R.id.btnReset1);
        btnPause = findViewById(R.id.btnPause);
        sharedPreferences = getSharedPreferences("com.example.alarmclock", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("trangthai", false);
        editor.commit();
        Handler handler = new Handler();
        list = new ArrayList<>();
        MyAddapterCountdown addapter = new MyAddapterCountdown(list, this);
        listView.setAdapter(addapter);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                MillisecondTime = SystemClock.uptimeMillis() - StartTime;
                //TimeBuff: thời gian bị dừng
                //Militime: dừng nó = 0
                //chạy 1,2,3.
                //a = b +c
                //start: b = 0, c chạy lên
                //pause: b = a, c = 0
                //start: b = a, c chạy lên
                //TimeBuff ở hàm Pause
                UpdateTime = TimeBuff + MillisecondTime;
                Seconds = (int) (UpdateTime / 1000);
                Minutes = Seconds / 60;
                Seconds = Seconds % 60;
                MilliSeconds = (int) (UpdateTime % 100);
                textView.setText(String.format("%02d", Minutes) + ":" + String.format("%02d", Seconds) + ";" + String.format("%02d", MilliSeconds));
                handler.postDelayed(this, 0);
            }
        };
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharedPreferences.getBoolean("trangthai", false) == true) {
                    list.add(new infoCountdown(textView.getText().toString(), luot));
                    luot++;
                    addapter.notifyDataSetChanged();
                } else {
                    //uptime: lấy thời gian hệ thống: 18h23
                    StartTime = SystemClock.uptimeMillis();
                    handler.postDelayed(runnable, 60);
                    btnReset.setEnabled(false);
                    editor.putBoolean("trangthai", true);
                    editor.commit();
                }
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimeBuff += MillisecondTime;
                handler.removeCallbacks(runnable);
                btnReset.setEnabled(true);
                editor.putBoolean("trangthai", false);
                editor.commit();
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MilliSeconds = 0;
                MillisecondTime = 0L;
                StartTime = 0L;
                TimeBuff = 0L;
                UpdateTime = 0L;
                Seconds = 0;
                Minutes = 0;
                textView.setText("00:00;00");
                list.clear();
                addapter.notifyDataSetChanged();
                editor.putBoolean("trangthai", false);
                editor.commit();
                luot = 1;
            }
        });

        btnBaoThuc = findViewById(R.id.button2);
        btnBaoThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CountdownClock.this, AlarmSave.class);
                startActivity(i);
            }
        });
        btnHenGio = findViewById(R.id.button1);
        btnHenGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CountdownClock.this, Timer.class);
                startActivity(intent);
            }
        });
    }
}