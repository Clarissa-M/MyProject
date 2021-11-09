package com.example.projectvers2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity  {
    //all added 1.11.
    DataHandler DH;
    String provTimeFrame;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize Bottom Navigation View.
        BottomNavigationView navView = findViewById(R.id.bottomNav_view);

        //Pass the ID's of Different destinations
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_setlimit, R.id.navigation_stats, R.id.navigation_advice)
                .build();

        //Initialize NavController.
        NavController navController = Navigation.findNavController(this, R.id.navHostFragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        //check the storage for data, if data exists, if not create with empty constructor. (will need to check for null values)
        this.DH = new DataHandler();
        this.provTimeFrame = "";

        //added 1.11.
        /**
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
         **/

    }

    /**
    //added 1.11.

    @Override
    public void sendData(String message) {
        Home f = (Home) getSupportFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewPager + ":" + viewPager.getCurrentItem());
       //Home f = (Home) getSupportFragmentManager().findFragmentByTag();
        f.displayReceivedData(message);
    }
    **/


    //reaction method to radio button in SetLimit clicked
    public void onSetLimRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radioButtonDay:
                if (checked)
                    Log.i("myInfo", "Radiobutton day");
                    provTimeFrame = "day";
                //set timeframe to day
                break;
            case R.id.radioButtonWeek:
                if (checked)
                    provTimeFrame = "week";
                    break;
            case R.id.radioButtonMonth:
                if (checked)
                    provTimeFrame = "month";
                    break;
        }
    }

    public void onSetLimOkayClicked(View v){
        Log.i("myInfo", ("The timeframe now is" + provTimeFrame));
        DH.setTimeFrame(provTimeFrame);
        timeFrameText(v);
    }
    public void timeFrameText(View v){
        TextView t = findViewById(R.id.SpentText1);
        Log.i("myInfo", ("The ID is" + t));
        //((TextView)findViewById(R.id.SpentText1)).setText(DH.getTimeFrame());
        //RadioButton b = findViewById(R.id.radioButtonDay);
        //String s = t.getText().toString();
        //Log.i("myInfo", ("This is the id" + s));

    }
}