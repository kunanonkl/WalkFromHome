package com.example.walkfromhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class distance_show extends AppCompatActivity {

    private static double STEP_RATIO_MALE = 0.40541373;
    private static double STEP_RATIO_FEMALE = 0.39418646;
    Button next_button;
    TextView distance_text,distance_text3,check_txt;
    String gender;
    int step;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance_show);

        next_button = findViewById(R.id.next_button);
        distance_text3 = findViewById(R.id.distance3);
        check_txt = findViewById(R.id.check);
        if(GlobalVariable.calibrate_check == true){
            check_txt.setText("Calibrate_Stride");
        }else{
            check_txt.setText("Normal");
        }
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent comment_page = new Intent(distance_show.this, comment_to_user_page_guest.class);
                startActivity(comment_page);
                finish();
                GlobalVariable.calibrate_check = false;
            }
        });


        gender = GlobalVariable.gender;
        step = GlobalVariable.step;
        switch (GlobalVariable.stride_length){
            case "short_stride":
                STEP_RATIO_MALE = 0.3266194883;
                STEP_RATIO_FEMALE = 0.3266194883;
                break;
            case "normal_stride":
                STEP_RATIO_MALE = 0.3850267;
                STEP_RATIO_FEMALE = 0.374;
                break;
            case "long_stride":
                STEP_RATIO_MALE = 0.438089;
                STEP_RATIO_FEMALE = 0.417;
                break;
            default:
                STEP_RATIO_MALE = 0.40541373;
                STEP_RATIO_FEMALE = 0.39418646;
        }
        if((gender == "male") && (GlobalVariable.calibrate_check == true)){
            distance_text3.setText(String.format("%.3f",calibrate_distance(GlobalVariable.stride_length_calibrate,step)));
            GlobalVariable.walking_distance = calibrate_distance(GlobalVariable.stride_length_calibrate,step);
        }
        else if((gender == "female") && (GlobalVariable.calibrate_check == true)){
            distance_text3.setText(String.format("%.3f",calibrate_distance(GlobalVariable.stride_length_calibrate,step)));
            GlobalVariable.walking_distance = calibrate_distance(GlobalVariable.stride_length_calibrate,step);
        }
        else if(gender == "male"){
            distance_text3.setText(String.format("%.3f",male_distance(step)));
            GlobalVariable.walking_distance = male_distance(step);
        }else if(gender == "female"){
            distance_text3.setText(String.format("%.3f",female_distance(step)));
            GlobalVariable.walking_distance = female_distance(step);
        }else{
            distance_text.setText("ERROR");
        }
    }
    public double male_distance(int step){
        Double step_length = 60.52;
        Double step_length_max = 60.52+11.57;
        Double step_length_min = 60.52-11.57;
        Double step_length_average = ((step_length_max + step_length_min)/2);
        Double distance = (step * step_length)/100;
        Double distance_max = (step * step_length_max)/100;
        Double distance_min = (step * step_length_min)/100;
        Double distance_average = (step * step_length_average)/100;

        Double distance_using_ratio_male = ((GlobalVariable.height)*(STEP_RATIO_MALE)*(step))/100;

        return distance_using_ratio_male;
    }

    public double female_distance(int step){
        Double step_length = 56.52;
        Double step_length_max = 56.52+5.45;
        Double step_length_min = 56.52-5.45;
        Double step_length_average = ((step_length_max + step_length_min)/2);
        Double distance = (step * step_length)/100;
        Double distance_max = (step * step_length_max)/100;
        Double distance_min = (step * step_length_min)/100;
        Double distance_average = (step * step_length_average)/100;

        Double distance_using_ratio_female = ((GlobalVariable.height)*(STEP_RATIO_FEMALE)*(step))/100;

        return distance_using_ratio_female;
    }
    public double calibrate_distance(double calibrate_step_length,int step){
        Double distance_calibrate = (GlobalVariable.stride_length_calibrate)*(step);
        return distance_calibrate;
    }
}