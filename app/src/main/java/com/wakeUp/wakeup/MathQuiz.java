package com.wakeUp.wakeup;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.Random;

public class MathQuiz extends AppCompatActivity {
    TextView textView,After,AM_PM; //문제내용, 시간경과
    TextClock Clock,PM_AM,date;
    Chronometer Time;// 총소요시간, 현재시간
    Button button1, button2, button3, button4;

    private MathProblem problem =new MathProblem(); //Problem가져오기
    private String correct; //정답
    private int QuestionLength=problem.Question.length; //질문 갯수

    public boolean running;
    Random rd=new Random(); //난수

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mathquiz);

        Clock=(TextClock)findViewById(R.id.Clock);
        PM_AM=(TextClock)findViewById(R.id.PM_AM);
        date=(TextClock)findViewById(R.id.date);
        After=(TextView)findViewById(R.id.After);
        Time=(Chronometer) findViewById(R.id.Time);
        textView=(TextView)findViewById(R.id.textView);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);


        /*Uri notification =RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        final Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(),notification);
        AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ALARM).build();
        ringtone.setAudioAttributes(audioAttributes);
        ringtone.play(); // 알람시작

        final Vibrator vibrator=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(new long[]{3000,1000}, 0); */

        if(!running){
            Time.start();
        }

        final int number=rd.nextInt(QuestionLength);

        InputQuestion(number);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button1.getText()==correct){
                    Time.stop();
                    String str=Time.getText().toString();
                    textView.setText("소요시간 "+str);
                    button2.setEnabled(false);
                    button3.setEnabled(false);
                    button4.setEnabled(false);

                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            moveTaskToBack(true);
                            finish();
                            android.os.Process.killProcess(android.os.Process.myPid());  //앱 종료
                        }
                    }, 2000); //2초 딜레이
                }
                else{
                    textView.setText("오답");
                    button1.setBackgroundColor(Color.BLACK);
                    button1.setEnabled(false); //버튼비활성화

                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            textView.setText(problem.getQuestion(number));
                        }
                    }, 500); //0.5초 딜레이
                }


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button2.getText()==correct){
                    Time.stop();
                    String str=Time.getText().toString();
                    textView.setText("소요시간 "+str);
                    button1.setEnabled(false);
                    button3.setEnabled(false);
                    button4.setEnabled(false);

                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            moveTaskToBack(true);
                            finish();
                            android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    }, 2000);
                }
                else{
                    textView.setText("오답");
                    button2.setBackgroundColor(Color.BLACK);
                    button2.setEnabled(false); //버튼비활성화

                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            textView.setText(problem.getQuestion(number));
                        }
                    }, 500);
                }

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button3.getText()==correct){
                    Time.stop();
                    String str=Time.getText().toString();
                    textView.setText("소요시간 "+str);
                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    button4.setEnabled(false);

                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            moveTaskToBack(true);
                            finish();
                            android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    }, 2000);

                }
                else{
                    textView.setText("오답");
                    button3.setBackgroundColor(Color.BLACK);
                    button3.setEnabled(false); //버튼비활성화

                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            textView.setText(problem.getQuestion(number));
                        }
                    }, 500);
                }

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(button4.getText()==correct){
                    Time.stop();
                    String str=Time.getText().toString();
                    textView.setText("소요시간 "+str);
                    button1.setEnabled(false);
                    button2.setEnabled(false);
                    button3.setEnabled(false);

                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            moveTaskToBack(true);
                            finish();
                            android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    }, 2000);

                }
                else{
                    textView.setText("오답");
                    button4.setBackgroundColor(Color.BLACK);
                    button4.setEnabled(false); //버튼비활성화

                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            textView.setText(problem.getQuestion(number));
                        }
                    }, 500);
                }

            }
        });


    }

    private void InputQuestion(int random) { //질문과 버튼 변경 String으로만
        textView.setText(problem.getQuestion(random));
        button1.setText(problem.getButton1(random));
        button2.setText(problem.getButton2(random));
        button3.setText(problem.getButton3(random));
        button4.setText(problem.getButton4(random));

        correct=problem.getCorrect(random);
    }

}