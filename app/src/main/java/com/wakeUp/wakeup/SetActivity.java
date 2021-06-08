package com.wakeUp.wakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.wakeUp.wakeup.SQLite.SQLiteHelper;

import java.util.Calendar;

public class SetActivity extends AppCompatActivity {

    AlarmManager alarm_manager;
    TimePicker alarm_timepicker;
    EditText inputName;
    Context context;
    PendingIntent pendingIntent;
    RadioButton btn1, btn2;
    CheckBox cbSun, cbMon, cbTue, cbWed, cbThu, cbFri, cbSat;
    SQLiteHelper helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        this.context = this;

        // 알람매니저 설정
        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

        // 타임피커 설정
        alarm_timepicker = findViewById(R.id.time_picker);

        inputName = (EditText) findViewById(R.id.inputName);

        //요일
        cbMon=findViewById(R.id.cb_mon);
        cbTue=findViewById(R.id.cb_tue);
        cbWed=findViewById(R.id.cb_wed);
        cbThu=findViewById(R.id.cb_thu);
        cbFri=findViewById(R.id.cb_fri);
        cbSat=findViewById(R.id.cb_sat);
        cbSun=findViewById(R.id.cb_sun);

        // Calendar 객체 생성
        final Calendar calendar = Calendar.getInstance();

        // 알람리시버 intent 생성
        final Intent my_intent = new Intent(this, Alarm_Receiver.class);

        // 알람 시작 버튼
        Button alarm_on = findViewById(R.id.btn_save);

        helper = new SQLiteHelper(this);
        db = helper.getWritableDatabase();

        alarm_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // calendar에 시간 셋팅
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());
                calendar.set(Calendar.SECOND, 0);

                // 시간 가져옴
                int hour = alarm_timepicker.getHour();
                int minute = alarm_timepicker.getMinute();
                Toast.makeText(SetActivity.this,"Alarm 예정 " + hour + "시 " + minute + "분", Toast.LENGTH_SHORT).show();

                // receiver에 string 값 넘겨주기
                my_intent.putExtra("state","alarm on");

                pendingIntent = PendingIntent.getBroadcast(SetActivity.this, 0, my_intent, PendingIntent.FLAG_UPDATE_CURRENT);

                //알람셋팅
                alarm_manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                        pendingIntent);

                String name = inputName.getText().toString() ;

                db.execSQL("insert into Alarm values('" + name + "','" + hour + "','" + minute + "')");

                Intent intent = new Intent(SetActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // 알람 정지 버튼
        Button alarm_off = findViewById(R.id.btn_back);
        alarm_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SetActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
