package com.example.acer_ssd.whattoeat;

import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by Acer_SSD on 29.04.2018.
 */

public class PageFragment3 extends Fragment {
    ListView full_menu3;
    editDBhelper sqlHelper;
    SQLiteDatabase db2;
    Cursor userCursor3;
    SimpleCursorAdapter userAdapter3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqlHelper = new editDBhelper(getActivity().getApplicationContext());
        sqlHelper.create_db();
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //ListView full_menu2 = (ListView) getView().findViewById(R.id.full_menu);
        full_menu3 = (ListView) getView().findViewById(R.id.main_listview);
    }
    @Override
    public void onResume() {
        super.onResume();

        try{
            // открываем подключение
            sqlHelper.open();
            //получаем данные из бд в виде курсора
            userCursor3 =  sqlHelper.database.rawQuery("select * from "+ editDBhelper.TABLE, null);
            // определяем, какие столбцы из курсора будут выводиться в ListView
            String[] headers3 = new String[] {editDBhelper.COLUMN_FULL_NAME, editDBhelper.COLUMN_IMAGE,editDBhelper.COLUMN_PRICE};
            // создаем адаптер, передаем в него курсор
            userAdapter3 = new SimpleCursorAdapter(getActivity(), R.layout.custom_list,
                    userCursor3, headers3, new int[]{R.id.text1, R.id.text2, R.id.text3}, 0);
            full_menu3.setAdapter(userAdapter3);
        }
        catch (SQLException ex){}

    }
    // Inflate the fragment layout we defined above for this fragment
    // Set the associated text for the title
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page3, container, false);
        return view;
    }
}
