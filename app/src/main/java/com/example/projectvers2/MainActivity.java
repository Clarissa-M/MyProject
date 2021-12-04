package com.example.projectvers2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainActivity extends AppCompatActivity  {
    private ViewModel viewModel;



    @RequiresApi(api = Build.VERSION_CODES.O)
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

        // viewModel
        viewModel = new ViewModelProvider(this).get(ViewModel.class);
        Log.i("MainActivity", "viewModel is initialised");

        //if there is preset data then get it, else default
        if(viewModel.getHasData()) {
            getData();
        }


        //viewModel.setSpentAmount(14.0);





    }
    public String getTextFileData(String fileName) {

        StringBuilder text = new StringBuilder();

        try {

            FileInputStream fIS = getApplicationContext().openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fIS, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String line;

            while ((line = br.readLine()) != null) {
                text.append(line + '\n');
            }
            br.close();
        } catch (IOException e) {
            Log.e("Error!", "Error occured while reading text file from Internal Storage!");

        }
        return text.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getData(){
        String fullString = getTextFileData("myFile");
        String part1 = "";
        String part2 = "";
        String part3 = "";
        String part4 = "";
        int counter = 1;


        String parts[] =fullString.split(",");
        for(String token :parts){
            if (counter == 1){
                part1 += token;
            }
            if (counter == 2) {
                part2 += token;
            }
            if (counter == 3) {
                part3 += token;
            }
            if (counter == 4) {
                part4 += token;
            }
            counter++;
        }
        viewModel.setTimeFrame(part1);
        viewModel.setLimitAmount(Integer.parseInt(part2));
        LocalDate endDate = LocalDate.parse(part3, DateTimeFormatter.ISO_LOCAL_DATE);
        viewModel.endDateSetter(endDate);
        Log.i("Main", part3);


    }








}