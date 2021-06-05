package com.example.wakeup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Alarm_Receiver extends BroadcastReceiver{

    Context context;
    static String TAG="Alarm";

    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;
        // intent로부터 전달받은 string
        String get_yout_string = intent.getExtras().getString("state");

        // RingtonePlayingService 서비스 intent 생성
        Intent service_intent = new Intent(context, RingtonePlayingService.class);

        // RingtonePlayinService로 extra string값 보내기
        service_intent.putExtra("state", get_yout_string);
        // start the ringtone service

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O){
            this.context.startForegroundService(service_intent);
        }else{
            this.context.startService(service_intent);
        }

        //요일
        boolean[] week = intent.getBooleanArrayExtra("weekday");

        Calendar cal = Calendar.getInstance();

        if (!week[cal.get(Calendar.DAY_OF_WEEK)]) return; // 체크한 요일이 아니면

        service_intent.putExtra("setDay",cal.get(Calendar.DAY_OF_WEEK));

    }
}
