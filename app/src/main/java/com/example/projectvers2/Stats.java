package com.example.projectvers2;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Stats#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Stats extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Stats() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Stats.
     */
    // TODO: Rename and change types and number of parameters
    public static Stats newInstance(String param1, String param2) {
        Stats fragment = new Stats();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        createFakePastData();
        TextView spendingText = (TextView)view.findViewById(R.id.TextSpending);

        //getPastSpending(int x); int 1 = day, int 2 = week, int 3 =month
        RadioButton radioButton1 = view.findViewById(R.id.dayButtonStats);
        radioButton1.setOnClickListener((v) ->{
            spendingText.setText("You have spent £" + getPastSpending(1) +" on games during the last day.");

        });
        RadioButton radioButton2 = view.findViewById(R.id.weekButtonStats);
        radioButton2.setOnClickListener((v) ->{
            spendingText.setText("You have spent £" + getPastSpending(2) +" on games during the last week.");
        });
        RadioButton radioButton3 = view.findViewById(R.id.monthButtonStats);
        radioButton3.setOnClickListener((v) ->{
            spendingText.setText("You have spent £" + getPastSpending(3) +" on games during the last month.");
        });
    }

    //reads in the fake spending data from internal storage
    public String readFakeData(String fileName) {

        StringBuilder text = new StringBuilder();

        try {

            FileInputStream fIS = getActivity().openFileInput(fileName);
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

    public int getPastSpending(int frame) {
        String data = readFakeData("pastData");
        int day = 0;
        int week = 0;
        int month = 0;
        int counter = 1;
        String end = "";
        String parts[] = data.split(",");
        for (String token : parts) {
            if (counter == 1) {
                day += Integer.parseInt(token);
            }
            if (counter == 2) {
                week += Integer.parseInt(token);
            }
            if (counter == 3) {
                month += Integer.parseInt(token);
            }
            if (counter == 4) {
                end += token;
            }
            counter++;
        }
        if (frame == 1){
            return day;
        }
        if (frame == 2){
            return week;
        }
        if (frame == 3){
            return month;
        }
        else return 0;
    }


    //creates/writes to a file which then has fake spending data
    public void createFakePastData(){

        String filename = "pastData";
        String string = "14,38,59,End"; //last day, week, month
        FileOutputStream outputStream;

        try {
            outputStream = getActivity().openFileOutput(filename, getActivity().MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
            Log.i("Main", string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}