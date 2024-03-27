package com.example.walkfromhome;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class calibrate_stride extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup radioGroup;
    private static int counting_step;
    private static double distance_calibrate;
    private Button next,back;
    private TextInputEditText distance_text,step_count_text;
    private boolean walking_information_calibrate = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calibrate_stride);

        distance_text = findViewById(R.id.insert_distance);
        step_count_text = findViewById(R.id.insert_step_count);
        next = findViewById(R.id.calibrate_step_next);
        back = findViewById(R.id.calibrate_step_back);
        radioGroup = findViewById(R.id.walk_data);
        radioGroup.setOnCheckedChangeListener(this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back_page = new Intent(getApplicationContext(),insert_data_page.class);
                startActivity(back_page);
                finish();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String get_distance = distance_text.getText().toString();
                String get_step = step_count_text.getText().toString();
                if((get_distance.isEmpty())|| (get_step.isEmpty())|| (walking_information_calibrate == false)){
                    alert("กรุณากรอกข้อมูลให้ครบถ้วน");
                }
                try {
                    if((!get_distance.isEmpty())&&(!get_step.isEmpty())&&(walking_information_calibrate == true)){
                        distance_calibrate = Double.parseDouble(get_distance);
                        counting_step = Integer.parseInt(get_step);
                        Double stride_length = (distance_calibrate / counting_step);
                        GlobalVariable.stride_length_calibrate = stride_length;
                        Intent main_6mwt = new Intent(getApplicationContext(),main_6mwt_page.class);
                        startActivity(main_6mwt);
                        finish();
                        GlobalVariable.calibrate_check = true;
                        walking_information_calibrate = false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    alert("กรุณากรอกข้อมูลให้ถูกต้อง");
                }
            }
        });
    }

    public void alert(String message){
        AlertDialog dlg = new AlertDialog.Builder(this)
                .setTitle("Alert").setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dlg.show();
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch(i){
            case R.id.slow:
                GlobalVariable.walking_information = "slow_walk";
                walking_information_calibrate = true;
                Toast.makeText(getApplicationContext(),"เดินช้า",Toast.LENGTH_SHORT).show();
                break;
            case R.id.normal_walk:
                GlobalVariable.walking_information = "normal_walk";
                walking_information_calibrate = true;
                Toast.makeText(getApplicationContext(),"เดินปกติ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.fast:
                GlobalVariable.walking_information = "fast_walk";
                walking_information_calibrate = true;
                Toast.makeText(getApplicationContext(),"เดินเร็ว",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}