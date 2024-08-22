package com.example.alarmclock;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
public class AlarmHelper extends SQLiteOpenHelper {
    public static final String DB_NAME ="DB.ALARM";
    public static final int DB_VERSION=1;
    public AlarmHelper(@Nullable Context context) {
        super(context,DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE "
                + "alarms"
                + "("
                + "ID integer primary key autoincrement, "
                + "gio integer, "
                + "phut integer, "

                + "ten text,"
                +"trangthai int"
                + ")";
        sqLiteDatabase.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS alarms");
        onCreate(sqLiteDatabase);
    }
}

