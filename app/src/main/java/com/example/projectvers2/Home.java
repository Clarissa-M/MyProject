package com.example.projectvers2;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home extends Fragment {

    private static final String TAG = "HomeFragment";
    public String provTimeFrame = "Default Timeframe";
    public int provLimit = 0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public Home() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home.
     */
    // TODO: Rename and change types and number of parameters
    public static Home newInstance(String param1, String param2) {
        Home fragment = new Home();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /** if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }**/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final NavController navController = Navigation.findNavController(view);
        ViewModel model = new ViewModelProvider(requireActivity()).get(ViewModel.class);


        TextView yourLimitText = (TextView) view.findViewById(R.id.yourLimitText);
        String limitText = "Your limit is £" + model.getLimitAmount() + " this " + model.getTimeFrame() + " till "+ model.getEndDate() +  ".";
        yourLimitText.setText(limitText);

        TextView timeFrameText = (TextView) view.findViewById(R.id.SpentText1);
        timeFrameText.setText("You have spent £" + model.getSpentAmount() +" on games this " + model.getTimeFrame());

        ProgressBar progBar = view.findViewById(R.id.progressBar);
        progBar.setMax(model.getLimitAmount());
        progBar.setProgress((int)model.getSpentAmount());
        progBar.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#41C7DB")));

        TextView more = (TextView)view.findViewById(R.id.LearnMoreText);
        more.setOnClickListener((v) ->{
            navController.navigate(R.id.action_navigation_home_to_learnMore);
            Log.i("Home", getTextFileData("myFile"));

        });



    }

    public String getTextFileData(String fileName) {

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






}