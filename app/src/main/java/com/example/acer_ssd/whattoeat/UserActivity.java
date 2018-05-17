package com.example.acer_ssd.whattoeat;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import java.sql.SQLException;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        long userId=0;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            userId = extras.getLong("id");
        }

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, PlaceholderFragment.newInstance(userId))
                    .commit();
        }
    }

    public static class PlaceholderFragment extends Fragment {

        EditText nameBox;
        EditText price;
        EditText content;
        Button delButton;
        Button saveButton;
        editDBhelper sqlHelper;
        Cursor userCursor;

        public static PlaceholderFragment newInstance(long id) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args=new Bundle();
            args.putLong("id", id);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setRetainInstance(true);


        }
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_user, container, false);
            nameBox = (EditText) rootView.findViewById(R.id.name);
            price = (EditText) rootView.findViewById(R.id.price);
            content = (EditText) rootView.findViewById(R.id.content);

            delButton = (Button) rootView.findViewById(R.id.delete);
            saveButton = (Button) rootView.findViewById(R.id.save);

            final long id = getArguments() != null ? getArguments().getLong("id") : 0;
            sqlHelper = new editDBhelper(getActivity());
            try {

                sqlHelper.open();

                // кнопка удаления
                delButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        sqlHelper.database.delete(editDBhelper.TABLE, "_id = ?",
                                new String[]{String.valueOf(id)});
                        goHome();
                    }
                });

                // кнопка сохранения
                saveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ContentValues cv = new ContentValues();
                        cv.put(editDBhelper.COLUMN_FULL_NAME, nameBox.getText().toString());
                        cv.put(editDBhelper.COLUMN_PRICE, price.getText().toString());
                        cv.put(editDBhelper.COLUMN_NAME, price.getText().toString());
                        cv.put(editDBhelper.COLUMN_TYPE, price.getText().toString());
                        cv.put(editDBhelper.COLUMN_IMAGE, content.getText().toString());

                        if (id > 0) {
                            sqlHelper.database.update(editDBhelper.TABLE, cv,
                                    editDBhelper.COLUMN_ID + "=" + String.valueOf(id), null);
                        } else {
                            sqlHelper.database.insert(editDBhelper.TABLE, null, cv);
                        }
                        goHome();
                    }
                });

                // если 0, то добавление
                if (id > 0) {
                    // получаем элемент по id из бд
                    userCursor = sqlHelper.database.rawQuery("select * from " + editDBhelper.TABLE + " where " +
                            editDBhelper.COLUMN_ID + "=?", new String[]{String.valueOf(id)});
                    userCursor.moveToFirst();
                    nameBox.setText(userCursor.getString(3));
                    //yearBox.setText(String.valueOf(userCursor.getInt(2)));
                    price.setText (userCursor.getString(4));
                    content.setText (userCursor.getString(5));
                    userCursor.close();
                } else {
                    // скрываем кнопку удаления
                    delButton.setVisibility(View.GONE);
                }
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            return rootView;
        }
        public void goHome(){

            sqlHelper.database.close();
            Intent intent = new Intent(getActivity(), databaseEdit.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
    }
}
