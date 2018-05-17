package com.example.acer_ssd.whattoeat;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Badgeable;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;

public class MainActivity extends ActionBarActivity {
    private Drawer.Result drawerResult = null;
    Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDialog = new Dialog(this);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager()));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);

        drawerResult = new Drawer()
                .withActivity(this)
                .withToolbar(toolbar)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.drawer_header)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName(R.string.drawer_item_home).withIcon(FontAwesome.Icon.faw_book),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_free_play).withIcon(FontAwesome.Icon.faw_map_marker).withIdentifier(2),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_custom).withIcon(FontAwesome.Icon.faw_database).withIdentifier(3),
                        new SectionDrawerItem().withName(R.string.drawer_item_settings),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_help).withIcon(FontAwesome.Icon.faw_home).withIdentifier(4),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_open_source).withIcon(FontAwesome.Icon.faw_share).withIdentifier(5),
                        new DividerDrawerItem(),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_contact).withIcon(FontAwesome.Icon.faw_info_circle).withIdentifier(6)
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    // Обработка клика
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id, IDrawerItem drawerItem) {

                            if (drawerItem.getIdentifier() == 2) {
                                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                MainActivity.this.startActivity(intent);
                            }
                            if (drawerItem.getIdentifier() == 3) {
                                Intent intent = new Intent(MainActivity.this, databaseEdit.class);
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                MainActivity.this.startActivity(intent);
                            }
                        if (drawerItem.getIdentifier() == 4) {

                                goToUrl ( "http://www.chinahouse.hr/en/");

                        }
                        if (drawerItem.getIdentifier() == 5) {
                            try {
                                Intent i = new Intent(Intent.ACTION_SEND);
                                i.setType("text/plain");
                                i.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                                String sAux = "\nLet me recommend you this application\n\n";
                                sAux = sAux + "https://play.google.com/store/apps/details?id=the.package.id \n\n";
                                i.putExtra(Intent.EXTRA_TEXT, sAux);
                                startActivity(Intent.createChooser(i, "choose one"));
                            } catch(Exception e) {
                                //e.toString();
                            }
                        }
                        if (drawerItem.getIdentifier() == 6) {

                            Intent intent = new Intent(MainActivity.this, About.class);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            MainActivity.this.startActivity(intent);

                        }
                            //MainActivity.this.startActivity(intent);

                    }
                })

                        /*if (drawerItem.getIdentifier() == 1) {
                            Intent i = new Intent(MainActivity.this, MapActivity.class);
                        }*/
                .build();


    }

    public void ShowPopup(View v) {
        TextView txtclose, person, price_set, menu_description;
        ImageView full_img;
        //ImageView full_img = (ImageView) findViewById(R.id.full_img);
        myDialog.setContentView(R.layout.custompopup);
        switch (v.getId()){
            case R.id.relativeLayout:
                full_img = (ImageView) myDialog.findViewById(R.id.full_img);
                full_img.setImageResource(R.drawable.f1);
                person = (TextView) myDialog.findViewById(R.id.person);
                person.setText("Set for 1 person");
                menu_description = (TextView) myDialog.findViewById(R.id.menu_description);
                menu_description.setText("Salad with chicken\n" +
                        "Cabbage salad \n" +
                        "Hot and sour soup\n" +
                        "Spring rolls\n" +
                        "Chicken a la Gonbao (with peanuts) \n" +
                        "Veal with vegetables\n" +
                        "Dessert");
                price_set = (TextView) myDialog.findViewById(R.id.price_set);
                price_set.setText("Price: 30RMB");
                break;
            case R.id.relativeLayout2:
                full_img = (ImageView) myDialog.findViewById(R.id.full_img);
                full_img.setImageResource(R.drawable.f2);
                person = (TextView) myDialog.findViewById(R.id.person);
                person.setText("Set for 2 persons");
                menu_description = (TextView) myDialog.findViewById(R.id.menu_description);
                menu_description.setText("Beansprout salad\n" +
                        "Salad with veal slices\n" +
                        "Hot and sour soup\n" +
                        "Spring rolls\n" +
                        "Pork with vegetables\n" +
                        "Crispy duck\n" +
                        "Dessert");
                price_set = (TextView) myDialog.findViewById(R.id.price_set);
                price_set.setText("Price: 60RMB");
                break;
            case R.id.relativeLayout3:
                full_img = (ImageView) myDialog.findViewById(R.id.full_img);
                full_img.setImageResource(R.drawable.f3);
                person = (TextView) myDialog.findViewById(R.id.person);
                person.setText("Set for 3 persons");
                menu_description = (TextView) myDialog.findViewById(R.id.menu_description);
                menu_description.setText("Cabbage salad \n" +
                        "Salad with chicken\n" +
                        "Beansprout salad\n" +
                        "Hot and sour soup\n" +
                        "Spring rolls\n" +
                        "Chicken with vegetables\n" +
                        "Pork in sweet and sour sauce\n" +
                        "Veal with bamboo-shoots and Chinese mushrooms\n" +
                        "Dessert");
                price_set = (TextView) myDialog.findViewById(R.id.price_set);
                price_set.setText("Price: 120RMB");
                break;
            case R.id.relativeLayout4:
                full_img = (ImageView) myDialog.findViewById(R.id.full_img);
                full_img.setImageResource(R.drawable.f6);
                person = (TextView) myDialog.findViewById(R.id.person);
                person.setText("Set for 4 persons");
                menu_description = (TextView) myDialog.findViewById(R.id.menu_description);
                menu_description.setText("Beansprout salad\n" +
                        "Shrimp salad\n" +
                        "Salad with veal slices\n" +
                        "Hot and sour soup\n" +
                        "Spring rolls\n" +
                        "Crispy duck\n" +
                        "Chicken a la Gonbao (with peanuts) \n" +
                        "Veal with vegetables\n" +
                        "Dessert");
                price_set = (TextView) myDialog.findViewById(R.id.price_set);
                price_set.setText("Price: 170RMB");
                break;
            case R.id.relativeLayout5:
                full_img = (ImageView) myDialog.findViewById(R.id.full_img);
                full_img.setImageResource(R.drawable.f5);
                person = (TextView) myDialog.findViewById(R.id.person);
                person.setText("Set for 5 persons");
                menu_description = (TextView) myDialog.findViewById(R.id.menu_description);
                menu_description.setText("Beansprout salad\n" +
                        "Salad with chicken\n" +
                        "Cabbage salad \n" +
                        "Salad with veal slices\n" +
                        "Hot and sour soup\n" +
                        "Spring rolls\n" +
                        "Chicken with vegetables\n" +
                        "Crispy duck\n" +
                        "Pork in sweet and sour sauce\n" +
                        "Veal with onions\n" +
                        "Dessert");
                price_set = (TextView) myDialog.findViewById(R.id.price_set);
                price_set.setText("Price: 260RMB");
                break;
            case R.id.relativeLayout6:
                full_img = (ImageView) myDialog.findViewById(R.id.full_img);
                full_img.setImageResource(R.drawable.f7);
                person = (TextView) myDialog.findViewById(R.id.person);
                person.setText("Set for 6 persons");
                menu_description = (TextView) myDialog.findViewById(R.id.menu_description);
                menu_description.setText("Salad with veal slices\n" +
                        "Beansprout salad\n" +
                        "Cabbage salad \n" +
                        "Shrimp salad\n" +
                        "Hot and sour soup\n" +
                        "Spring rolls\n" +
                        "Chicken a la Gonbao (with peanuts) \n" +
                        "Crispy duck\n" +
                        "Veal with bamboo-shoots and Chinese mushrooms\n" +
                        "Sweet and sour fish (hake)\n" +
                        "Dessert");
                price_set = (TextView) myDialog.findViewById(R.id.price_set);
                price_set.setText("Price: 350RMB");
                break;
        }
        //myDialog.setContentView(R.layout.custompopup);

        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("Close");
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }

    public void ShowPopupPay(View v) {
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
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }

        return super.onOptionsItemSelected(item);
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }


}
