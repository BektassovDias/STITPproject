package com.example.acer_ssd.whattoeat;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.view.ScaleGestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class recommendation_list extends AppCompatActivity {
    ListView userList;
    DatabaseHelper databaseHelper;
    SQLiteDatabase db;
    Cursor userCursor;
    SimpleCursorAdapter userAdapter;
    Dialog myDialog;
    String food, food2, food3, food4, food5, food6, food7, food8, food9;

    private GestureDetectorCompat gestureObject;


    float historicX = Float.NaN, historicY = Float.NaN;
    static final int DELTA = 50;
    enum Direction {LEFT, RIGHT;}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation_list);
        userList = (ListView)findViewById(R.id.listview);


        userList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                // TODO Auto-generated method stub
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        historicX = event.getX();
                        historicY = event.getY();
                        break;

                    case MotionEvent.ACTION_UP:
                        if (event.getX() - historicX < -DELTA)
                        {
                            onResume();
                            return true;
                        }

                    default: return false;
                }
                return false;
            }
        });
        databaseHelper = new DatabaseHelper(getApplicationContext());
        // создаем базу данных
        databaseHelper.create_db();
        myDialog = new Dialog(this);
    }

    @Override
    public void onResume() {
        super.onResume();


        final Random random = new Random();
        /*TextView textView = (TextView) findViewById(R.id.textView6);
        TextView textView2 = (TextView) findViewById(R.id.textView7);*/

        Boolean chicken = getIntent().getExtras().getBoolean("checkBox");
        Boolean soup = getIntent().getExtras().getBoolean("checkBox2");
        Boolean rice = getIntent().getExtras().getBoolean("checkBox3");
        Boolean noodles = getIntent().getExtras().getBoolean("checkBox4");
        Boolean fish = getIntent().getExtras().getBoolean("checkBox5");
        Boolean squid = getIntent().getExtras().getBoolean("checkBox6");
        Boolean pork = getIntent().getExtras().getBoolean("checkBox7");
        Boolean meat = getIntent().getExtras().getBoolean("checkBox8");
        Boolean duck = getIntent().getExtras().getBoolean("checkBox9");


        String[] chickenArray = new String[]{"'chicken_1'", "'chicken_2'", "'chicken_3'", "'chicken_4'", "'chicken_5'","'chicken_6'","'chicken_7'","'chicken_8'","'chicken_9'","'chicken_10'","'chicken_11'"};
        int n = (int)Math.floor(Math.random() * chickenArray.length);

        String[] soupArray = new String[]{"'soup_1'", "'soup_3'", "'soup_4'", "'soup_5'"};
        int h = (int)Math.floor(Math.random() * soupArray.length);

        String[] fishArray = new String[]{"'fish_1'", "'fish_2'", "'fish_3'"};
        int e = (int)Math.floor(Math.random() * fishArray.length);

        String[] riceArray = new String[]{"'rice_1'", "'rice_2'", "'rice_3'", "'rice_4'", "'rice_5'","'rice_6'"};
        int l = (int)Math.floor(Math.random() * riceArray.length);

        String[] noodlesArray = new String[]{"'noodles_1'", "'noodles_2'", "'noodles_3'", "'noodles_4'", "'noodles_5'"};
        int k = (int)Math.floor(Math.random() * noodlesArray.length);

        String[] squidArray = new String[]{"'squid_1'", "'squid_2'", "'squid_3'", "'squid_4'"};
        int o = (int)Math.floor(Math.random() * squidArray.length);

        String[] porkArray = new String[]{"'pork1'", "'pork2'", "'pork3'", "'pork4'"};
        int p = (int)Math.floor(Math.random() * porkArray.length);

        String[] meatArray = new String[]{"'soup_2'", "'soup_6'", "'soup_7'"};
        int m = (int)Math.floor(Math.random() * meatArray.length);

        String[] duckArray = new String[]{"'duck1'", "'duck2'", "'duck3'", "'duck4'"};
        int d = (int)Math.floor(Math.random() * duckArray.length);


        if (chicken){
            food = chickenArray[n];
            //textView.setText(chickenArray[n]);
        }
        if (soup){
            food2 = soupArray[h];
            //textView2.setText(food2);
        }
        if (rice){
            food3 = riceArray[l];
            //textView.setText(food3);
        }
        if (noodles){
            food4 = noodlesArray[k];
            //textView.setText(food4);
        }
        if (fish){
            food5 = fishArray[e];
            //textView.setText(food4);
        }
        if (squid){
            food6 = squidArray[o];
            //textView.setText(food4);
        }
        if (pork){
            food7 = porkArray[p];
            //textView.setText(food4);
        }
        if (meat){
            food8 = meatArray[m];
            //textView.setText(food4);
        }
        if (duck){
            food9 = duckArray[d];
            //textView.setText(food4);
        }


        // открываем подключение
        db = databaseHelper.open();
        //получаем данные из бд в виде курсора
        userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE + " where "+DatabaseHelper.COLUMN_NAME+ " in ("+food+","+food2+","+food3+","+food4+","+food5+","+food6+","+food7+","+food8+","+food9+")", null);
        //userCursor =  db.rawQuery("select * from "+ DatabaseHelper.TABLE, null);
        // определяем, какие столбцы из курсора будут выводиться в ListView
        final String[] headers = new String[] {DatabaseHelper.COLUMN_FULL_NAME, DatabaseHelper.COLUMN_IMAGE, DatabaseHelper.COLUMN_PRICE};
        // создаем адаптер, передаем в него курсор
        userList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        userAdapter = new SimpleCursorAdapter(this, R.layout.custom_list,
                userCursor, headers, new int[]{R.id.text1, R.id.text2, R.id.text3}, 0);
        userList.setAdapter(userAdapter);

    }





    public void ShowPopupPayRec(View v) {
        TextView txtclose;
        ImageButton imgbutton;
        Button btnOrder;
        myDialog.setContentView(R.layout.custompopup_pay);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("Close");
        btnOrder = (Button) myDialog.findViewById(R.id.btnOrder);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }
    public void OnUpdateList(View view){
        onResume();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключение и курсор
        db.close();
        userCursor.close();
    }
    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
