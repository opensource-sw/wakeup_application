package com.example.wakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button_set;
    Button button_modify;
    Button button_quiz;
    Button button_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Intent my_intent = new Intent(this, Alarm_Receiver.class);

        button_set = (Button)findViewById(R.id.button_set);
        button_modify = (Button)findViewById(R.id.button_modify);
        button_quiz = (Button)findViewById(R.id.button_mathquiz);
        button_game = (Button)findViewById(R.id.button_rspgame);

        button_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SetActivity.class);
                startActivity(intent);
            }
        });
        button_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ModifyActivity.class);
                startActivity(intent);
            }
        });
        button_quiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        button_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Alarm 종료",Toast.LENGTH_SHORT).show();
                // 알람매니저 취소
                //alarm_manager.cancel(pendingIntent);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(getApplicationContext(), RspgameActivity.class);
                        startActivity(intent);
                        finish();
                    }
                },3000);
                my_intent.putExtra("state","alarm off");
                // 알람취소
                sendBroadcast(my_intent);
            }
        });
    }
}