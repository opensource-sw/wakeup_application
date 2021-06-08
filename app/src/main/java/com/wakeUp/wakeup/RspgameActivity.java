package com.wakeUp.wakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.wakeUp.wakeup.R;
import java.util.ArrayList;


public class RspgameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rspgame);


        /*final Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE); // 진동울리기
        vibrator.vibrate(new long[]{3000,1000},0);

        Uri notification =RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        final Ringtone ringtone = RingtoneManager.getRingtone(getApplicationContext(),notification);
        AudioAttributes audioAttributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_ALARM).build();
        ringtone.setAudioAttributes(audioAttributes);
        ringtone.play(); // 알람시작 */

        TextView textView = findViewById(R.id.text01);
        textView.setText("3번 이기면 알람이 종료돼요. 시작버튼을 누르세요.");
        TextView resut_textView = findViewById(R.id.resultText);

        MyButtonLis myButtonLis = new MyButtonLis();
        myButtonLis.chageText = resut_textView;


        //시작버튼
        Button button = findViewById(R.id.button01);
        button.setText("시작");
        myButtonLis.start = button;

        //가위버튼
        Button button1 = findViewById(R.id.button2);
        myButtonLis.btn01 = button1;
        button1.setOnClickListener(myButtonLis);


        //바위버튼
        Button button2 = findViewById(R.id.button3);
        myButtonLis.btn02 = button2;
        button2.setOnClickListener(myButtonLis);
        //보 버튼
        Button button3 = findViewById(R.id.button4);
        myButtonLis.btn03 = button3;
        button3.setOnClickListener(myButtonLis);



        Atimer atimer = new Atimer(60000,200);
        atimer.textView = textView;

        myButtonLis.atimer = atimer;

        button.setOnClickListener(myButtonLis);

    }
}

class MyButtonLis extends Activity implements View.OnClickListener
{
    Atimer atimer;
    TextView chageText;

    int i=0;
    int count = 0;

    Button start;
    Button btn01;
    Button btn02;
    Button btn03;

    @Override
    public void onClick(View view) {

        if (view.equals(start))
        {
            atimer.start();
            chageText.setText("가위 바위 보를 고르세요.");

        }else if(view.equals(btn01))
        {

            chageText.setText("가위");

            atimer.cancel();
            if(atimer.i==1)
            {
                chageText.setText("YOU LOSE\n" + (3-count) + " 번 남았습니다. \n시작버튼을 다시 누르세요.");
            }else if(atimer.i==2)
            {
                count++;
                chageText.setText("YOU WIN\n" + (3-count) + " 번 남았습니다. \n시작버튼을 다시 누르세요.");
                if (count == 3) // 세번이기면 앱 종료
                {
                    /*ActivityCompat.finishAffinity(this);
                    System.exit(0);*/
                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            moveTaskToBack(true);
                            finishAndRemoveTask();
                            android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    }, 1);

                }
            }else
            {
                chageText.setText("DRAW\n" + (3-count) + " 번 남았습니다. \n시작버튼을 다시 누르세요.");
            }

        }else if (view.equals(btn02))
        {
            chageText.setText("바위");

            atimer.cancel();
            if(atimer.i==2)
            {
                chageText.setText("YOU LOSE\n" + (3-count) + " 번 남았습니다. \n시작버튼을 다시 누르세요.");
            }else if(atimer.i==0)
            {
                count++;
                chageText.setText("YOU WIN\n" + (3-count) + " 번 남았습니다. \n시작버튼을 다시 누르세요.");
                if (count == 3) // 세번이기면 앱 종료
                {
                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            moveTaskToBack(true);
                            finishAndRemoveTask();
                            android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    }, 1);
                    /*ActivityCompat.finishAffinity(this);
                    System.exit(0);*/
                }
            }else
            {
                chageText.setText("DRAW\n" + (3-count) + " 번 남았습니다. \n시작버튼을 다시 누르세요.");
            }



        }else if (view.equals(btn03))
        {
            chageText.setText("보");

            atimer.cancel();

            if(atimer.i==0)
            {
                chageText.setText("YOU LOSE\n" + (3-count) + " 번 남았습니다. \n시작버튼을 다시 누르세요.");
            }else if(atimer.i==1)
            {
                count++;
                chageText.setText("YOU WIN\n" + (3-count) + " 번 남았습니다. \n시작버튼을 다시 누르세요.");
                if (count == 3) // 세번이기면 앱 종료
                {
                    new Handler().postDelayed(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            moveTaskToBack(true);
                            finishAndRemoveTask();
                            android.os.Process.killProcess(android.os.Process.myPid());
                        }
                    }, 1);
                    /*ActivityCompat.finishAffinity(this);
                    System.exit(0);*/
                }
            }else
            {
                chageText.setText("DRAW\n" + (3-count) + " 번 남았습니다. \n시작버튼을 다시 누르세요.");
            }
        }
    }

}

class Atimer extends CountDownTimer
{
    TextView textView;
    int i=0;


    ArrayList<String> list =new  ArrayList<String>();


    public Atimer(long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        list.add("가위"); //0
        list.add("바위");  //1
        list.add("보");    //2
    }


    @Override
    public void onTick(long l) {
        i++;
        if(i>=list.size()) i=0;
        //textView.setText(list.get(i));
        textView.setText(list.get(i));
    }

    @Override
    public void onFinish() {

        textView.setText("END");
    }
}
