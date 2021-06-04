package com.example.wakeup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button button_set;
    Button button_modify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_set = (Button)findViewById(R.id.button_set);
        button_modify = (Button)findViewById(R.id.button_modify);

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
    }
}