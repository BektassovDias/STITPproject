package com.example.acer_ssd.whattoeat;



import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.sql.SQLException;

public class databaseEdit extends AppCompatActivity {
    ListView userList2;
    editDBhelper sqlHelper;
    SQLiteDatabase db2;
    Cursor userCursor2;
    SimpleCursorAdapter userAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_edit);
        userList2 = (ListView)findViewById(R.id.listview2);

        userList2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });


        sqlHelper = new editDBhelper(getApplicationContext());
        sqlHelper.create_db();


    }
    @Override
    public void onResume() {
        super.onResume();

        try{
            // открываем подключение
            sqlHelper.open();
            //получаем данные из бд в виде курсора
            userCursor2 =  sqlHelper.database.rawQuery("select * from "+ editDBhelper.TABLE, null);
            // определяем, какие столбцы из курсора будут выводиться в ListView
            String[] headers2 = new String[] {editDBhelper.COLUMN_FULL_NAME, editDBhelper.COLUMN_PRICE};
            // создаем адаптер, передаем в него курсор
            userAdapter2 = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item,
                    userCursor2, headers2, new int[]{android.R.id.text1, android.R.id.text2}, 0);
            userList2.setAdapter(userAdapter2);
        }
        catch (SQLException ex){}

    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        // Закрываем подключение и курсор
        sqlHelper.database.close();
        userCursor2.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {

            Intent intent = new Intent(getApplicationContext(), UserActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
