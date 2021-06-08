package com.wakeUp.wakeup;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.wakeUp.wakeup.SQLite.AlarmList;
import com.wakeUp.wakeup.SQLite.SQLiteHelper;

import java.util.ArrayList;

public class ModifyActivity extends AppCompatActivity {
    Button button_add;
    Button button_back;
    Button button_delete;

    ListView alarmlist;
    ArrayList<AlarmList> list;
    alarmadapter adapter;

    SQLiteHelper helper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        ///// 리스트 뷰 작업

        // 빈 데이터 리스트 생성.
        //   items = new ArrayList<String>() ;
        // ArrayAdapter 생성. 아이템 View를 선택(single choice)가능하도록 만듦.
        //  final ArrayAdapter adapter = new ArrayAdapter(this,
        //          android.R.layout.simple_list_item_single_choice, items) ;

        // delete button에 대한 이벤트 처리.
        //   Button button_delete = (Button)findViewById(R.id.button_delete) ;
        //   button_delete.setOnClickListener(new Button.OnClickListener() {
        //       public void onClick(View v) {
        //           db Delete= new db();
        //       }
        //   }) ;


        ///// 리스트 뷰 작업 end
        helper = new SQLiteHelper(this);
        db = helper.getWritableDatabase();

        alarmlist = (ListView)findViewById(R.id.alarm_list);
        button_add = (Button)findViewById(R.id.button_add);
        button_back = (Button)findViewById(R.id.button_back);
        button_delete = (Button)findViewById(R.id.button_delete);

        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭시 화면 이동하는 동작 입력
                Intent intent = new Intent(ModifyActivity.this, SetActivity.class);
                startActivity(intent);
            }
        });
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //버튼 클릭시 화면 이동하는 동작 입력
                Intent intent = new Intent(ModifyActivity.this, MainActivity.class);
                startActivity(intent);//화면 1로 이동
            }
        });

        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ModifyActivity.this,"선택한 알람이 삭제됩니다.",Toast.LENGTH_SHORT).show();
                SparseBooleanArray checkedItems = alarmlist.getCheckedItemPositions();
                int count = adapter.getCount() ; //리스트 갯수

                for (int i=count-1;  i>=0; i--) {
                    if (checkedItems.get(i)) {
                        //db.execSQL("delete from Alarm where name ='"+ list[i].getName()+"';"); //items.remove(i);, items -> list
                    }
                }
                //모든 선택 상태 초기화
                alarmlist.clearChoices();
                adapter.notifyDataSetChanged();
                /*if (count > 0) {
                    // 현재 선택된 아이템의 position 획득.
                    checked = alarmlist.getCheckedItemPosition();
                    if (checked > -1 && checked < count) {
                        // 아이템 삭제
                        //list.remove(checked);
                        db.execSQL("delete from Alarm where name ='"+ alarmlist.getName()+"';");
                        // listview 선택 초기화.
                        alarmlist.clearChoices();
                        // listview 갱신.
                        adapter.notifyDataSetChanged();
                    }
                }
                alarmlist.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        AlarmList alarm= (AlarmList)parent.getItemAtPosition(position);
                        db.execSQL("delete from Alarm where name ='"+ alarm.getName()+"';");
                        adapter.notifyDataSetChanged();//중단됨
                    }
                });*/
            }
        });

        alarmlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ModifyActivity.this, SetActivity.class);
                startActivity(intent);
            }
        });

        displayList();
    }
    void displayList(){
        //Dbhelper의 읽기모드 객체를 가져와 SQLiteDatabase에 담아 사용준비
        SQLiteHelper helper = new SQLiteHelper(this);
        SQLiteDatabase database = helper.getReadableDatabase();

        //Cursor라는 그릇에 목록을 담아주기
        Cursor cursor = database.rawQuery("SELECT * FROM ALARM",null);

        //리스트뷰에 목록 채워주는 도구인 adapter준비
        alarmadapter adapter = new alarmadapter();

        //목록의 개수만큼 순회하여 adapter에 있는 list배열에 add
        while(cursor.moveToNext()){
            //num 행은 가장 첫번째에 있으니 0번이 되고, name은 1번
            adapter.addItemToList(cursor.getString(0),cursor.getInt(1),cursor.getInt(2));
        }

        //리스트뷰의 어댑터 대상을 여태 설계한 adapter로 설정
        alarmlist.setAdapter(adapter);

    }
}
