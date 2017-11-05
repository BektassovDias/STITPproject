package com.example.acer_ssd.whattoeat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void OnClick(View view){
        Intent intent = new Intent(this, recommendation_list.class);
        startActivity(intent);
    }

    public void OnClick2(View view){
        CheckBox checkBox = (CheckBox)findViewById(R.id.checkBox);
        CheckBox checkBox2 = (CheckBox)findViewById(R.id.checkBox2);
        CheckBox checkBox3 = (CheckBox)findViewById(R.id.checkBox3);
        CheckBox checkBox4 = (CheckBox)findViewById(R.id.checkBox4);
        CheckBox checkBox5 = (CheckBox)findViewById(R.id.checkBox5);
        CheckBox checkBox6 = (CheckBox)findViewById(R.id.checkBox6);
        CheckBox checkBox7 = (CheckBox)findViewById(R.id.checkBox7);
        CheckBox checkBox8 = (CheckBox)findViewById(R.id.checkBox8);
        CheckBox checkBox9 = (CheckBox)findViewById(R.id.checkBox9);


        Intent intent = new Intent(MainActivity.this, recommendation_list.class);
        intent.putExtra("checkBox", checkBox.isChecked());
        intent.putExtra("checkBox2", checkBox2.isChecked());
        intent.putExtra("checkBox3", checkBox3.isChecked());
        intent.putExtra("checkBox4", checkBox4.isChecked());
        intent.putExtra("checkBox5", checkBox5.isChecked());
        intent.putExtra("checkBox6", checkBox6.isChecked());
        intent.putExtra("checkBox7", checkBox7.isChecked());
        intent.putExtra("checkBox8", checkBox8.isChecked());
        intent.putExtra("checkBox9", checkBox9.isChecked());

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.settings){
            Intent intent = new Intent(this, databaseEdit.class);
            startActivity(intent);
        }
        if(id==R.id.about){

        }

        return super.onOptionsItemSelected(item);
    }
}
