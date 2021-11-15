package com.example.projectvers2;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Setlimit#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Setlimit extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ViewModel model;
    private int checkT = 0;
    private int checkL =0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private DataHandler dh;

    private String provTimeFrame;
    private int provLimit;



    public Setlimit() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Setlimit.
     */
    // TODO: Rename and change types and number of parameters
    public static Setlimit newInstance(String param1, String param2) {
        Setlimit fragment = new Setlimit();
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
        return inflater.inflate(R.layout.fragment_setlimit, container, false);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        TextView warning = (TextView)view.findViewById(R.id.warningSet2Text);
        TextView limit0 = (TextView)view.findViewById(R.id.limit0Text);
        warning.setVisibility(View.INVISIBLE);
        limit0.setVisibility(View.INVISIBLE);

        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        model = new ViewModelProvider(requireActivity()).get(ViewModel.class);

        RadioButton radioButton1 = view.findViewById(R.id.radioButtonDay);
        radioButton1.setOnClickListener((v) ->{
            provTimeFrame = "day";
            checkT++;

        });
        RadioButton radioButton2 = view.findViewById(R.id.radioButtonWeek);
        radioButton2.setOnClickListener((v) ->{
            provTimeFrame = "week";
            checkT++;
        });
        RadioButton radioButton3 = view.findViewById(R.id.radioButtonMonth);
        radioButton3.setOnClickListener((v) ->{
            provTimeFrame = "month";
            checkT++;
        });

        SeekBar seekBar = view.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                checkL++;
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub
                provLimit = Integer.valueOf(progress);
                TextView textProvLim = (TextView) view.findViewById(R.id.chosenAmount);
                textProvLim.setText("£ "+provLimit);

            }
        });


        Button button = view.findViewById(R.id.OKbutton);
        button.setOnClickListener((v) ->{
            if(checkL !=0 && checkT != 0) {
                warning.setVisibility(View.INVISIBLE);
                saveWindow(view);
                /**model.setTimeFrame(provTimeFrame);
                model.setLimitAmount(provLimit);
                model.setSetDate(LocalDate.now());
                model.setEndDate();

                if(provLimit == 0){
                    limit0.setVisibility(View.VISIBLE);
                }
                saveData();**/

            }
            else{
            warning.setVisibility(View.VISIBLE);
            }

        });


    }

    public void saveWindow(View view){
        TextView limit0 = (TextView)view.findViewById(R.id.limit0Text);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(requireContext());
        alertDialogBuilder.setMessage("You are now resetting your timeframe and limit. Do you want to continue?");


        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        model.setTimeFrame(provTimeFrame);
                        model.setLimitAmount(provLimit);
                        model.setSetDate(LocalDate.now());
                        model.setEndDate();

                        if(provLimit == 0){
                            limit0.setVisibility(View.VISIBLE);
                        }
                        saveData();
                    }
                });

        alertDialogBuilder.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    //saving Data to the internal storage
    public void saveData(){
        model = new ViewModelProvider(requireActivity()).get(ViewModel.class);
        String filename = "myFile";
        String string = ""+ model.getTimeFrame() + "," + model.getLimitAmount() + "," + model.getEndDate() +", End" ;
        FileOutputStream outputStream;

        try {
            outputStream = getActivity().openFileOutput(filename, getActivity().MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
            Log.i("Setlimit", string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}