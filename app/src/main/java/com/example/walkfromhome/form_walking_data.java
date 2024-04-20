package com.example.walkfromhome;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class form_walking_data extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    Dialog myDialog;
    private Button next_button,back_button;
    private RadioGroup radioGroup;
    private int i;
    private RadioGroup radioGrop2;
    private RadioGroup radioGroup3;
    private boolean walking_weight_check = false,walking_information = false,stride_information = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_form_walking_data);
        next_button = findViewById(R.id.insert_data_next_button);
        back_button = findViewById(R.id.insert_data_back_button);
        radioGrop2 = findViewById(R.id.speedGroup);
        radioGrop2.setOnCheckedChangeListener(this);
        radioGroup3 = findViewById(R.id.strideGroup);
        radioGroup3.setOnCheckedChangeListener(this);
        Intent walking_method = new Intent(form_walking_data.this,main_6mwt_page.class);

        JSONObject dataRead = readJSONFromFile(getApplicationContext(), "data.json");
        if(dataRead != null){
            try {
                String speed = dataRead.getString("speed");
                String stride = dataRead.getString("stride");

                switch (stride) {
                    case  "short_stride": radioGroup3.check(R.id.short_stride); break;
                    case  "normal_stride": radioGroup3.check(R.id.normal_stride); break;
                    case  "long_stride": radioGroup3.check(R.id.long_stride); break;
                }

                switch (speed) {
                    case  "slow_walk": radioGrop2.check(R.id.slow); break;
                    case  "normal_walk": radioGrop2.check(R.id.normal_walk); break;
                    case  "fast_walk": radioGrop2.check(R.id.fast); break;
                }

            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if((walking_information == true) && (stride_information == true)){
                        startActivity(walking_method);
                        finish();
                        walking_information = false;
                        stride_information = false;
                    }else if((walking_information == false)||(stride_information == false)){
                        alert("กรุณาเลือกข้อมูลให้ครบถ้วน");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    alert("มีบางอย่างผิดปกติกรุณาลองใหม่");

                }
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent insert_data_page = new Intent(form_walking_data.this,insert_data_page.class);
                startActivity(insert_data_page);
                finish();
            }
        });

    }
    public void alert(String message){
        AlertDialog dlg = new AlertDialog.Builder(form_walking_data.this)
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
            case R.id.short_stride:
                GlobalVariable.stride_length = "short_stride";
                stride_information = true;
                Toast.makeText(getApplicationContext(),"ระยะก้าวสั้น",Toast.LENGTH_SHORT).show();
                break;
            case R.id.normal_stride:
                GlobalVariable.stride_length = "normal_stride";
                stride_information = true;
                Toast.makeText(getApplicationContext(),"ระยะก้าวปกติ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.long_stride:
                GlobalVariable.stride_length = "long_stride";
                stride_information = true;
                Toast.makeText(getApplicationContext(),"ระยะก้าวยาว",Toast.LENGTH_SHORT).show();
            break;
            case R.id.slow:
                GlobalVariable.walking_information = "slow_walk";
                walking_information = true;
                Toast.makeText(getApplicationContext(),"เดินช้า",Toast.LENGTH_SHORT).show();
                break;
            case R.id.normal_walk:
                GlobalVariable.walking_information = "normal_walk";
                walking_information = true;
                Toast.makeText(getApplicationContext(),"เดินปกติ",Toast.LENGTH_SHORT).show();
                break;
            case R.id.fast:
                GlobalVariable.walking_information = "fast_walk";
                walking_information = true;
                Toast.makeText(getApplicationContext(),"เดินเร็ว",Toast.LENGTH_SHORT).show();
            break;
        }

    }
    public JSONObject readJSONFromFile(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            FileInputStream fis = context.openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append('\n');
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return new JSONObject(stringBuilder.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}