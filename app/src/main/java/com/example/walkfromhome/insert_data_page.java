package com.example.walkfromhome;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class insert_data_page extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    Dialog myDialog;
    Double weight,height;
    private String gender;
    private Button next_button,back_button,calibrate_mode;
    TextInputEditText insert_firstname,insert_lastname,insert_weight,insert_height;
    private RadioGroup radioGroup;
    private RadioButton selectedRatioBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data_page);
        next_button = findViewById(R.id.insert_data_next_button);
        back_button = findViewById(R.id.insert_data_back_button);
        insert_weight = findViewById(R.id.insert_weight);
        insert_height = findViewById(R.id.insert_height);
        insert_firstname = findViewById(R.id.insert_firstname);
        insert_lastname = findViewById(R.id.insert_lastname);
        radioGroup = findViewById(R.id.genderGroup);
        radioGroup.setOnCheckedChangeListener(this);
        calibrate_mode = findViewById(R.id.to_calibrate_button);

        JSONObject dataRead = readJSONFromFile(getApplicationContext(), "data.json");
        if(dataRead != null){
            try {
                String firstName = dataRead.getString("firstName");
                String lastName = dataRead.getString("lastName");
                String weight = dataRead.getString("weight");
                String height = dataRead.getString("height");
                String gender = dataRead.getString("gender");
                if(!firstName.isEmpty()){
                    insert_firstname.setText(dataRead.getString("firstName"));
                }
                if(!lastName.isEmpty()){
                    insert_lastname.setText(dataRead.getString("lastName"));
                }
                if(!weight.isEmpty()){
                    insert_weight.setText(dataRead.getString("weight"));
                }
                if(!height.isEmpty()){
                    insert_height.setText(dataRead.getString("height"));
                }

                if(gender.equals("male")) {
                    radioGroup.check(R.id.male_ratio);
                }
                else {
                    radioGroup.check(R.id.female_ratio);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }

        calibrate_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedRatioBtn = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                String get_firstname = insert_firstname.getText().toString();
                String get_lastname = insert_lastname.getText().toString();
                String get_weight = insert_weight.getText().toString();
                String get_height = insert_height.getText().toString();
                Intent to_stepCounter_app = new Intent(insert_data_page.this, calibrate_stride.class);
                if((get_weight.isEmpty()) || (get_height.isEmpty()) || (get_firstname.isEmpty()) || (get_lastname.isEmpty())){
                    alert("กรุณากรอกข้อมูลให้ครบถ้วน");
                }
                else{
                try {
                    if ((!get_weight.isEmpty()) && (!get_height.isEmpty())) {
                        GlobalVariable.firstName = get_firstname;
                        GlobalVariable.lastName = get_lastname;
                        weight = Double.parseDouble(get_weight);
                        height = Double.parseDouble(get_height);
                        GlobalVariable.weight = weight;
                        GlobalVariable.height = height;
                        if ((gender == "ชาย")) {
                            GlobalVariable.gender = "male";
                            try {
                                Toast.makeText(insert_data_page.this, "เพศชาย", Toast.LENGTH_SHORT).show();
                            }catch (NumberFormatException e){
                                e.printStackTrace();
                                alert("เกิดข้อผิดพลาดบางอย่างขึ้น");
                            }
                            startActivity(to_stepCounter_app);
                            //GlobalVariable.calibrate_check = true;
                        } else if ((gender == "หญิง")) {
                            GlobalVariable.gender = "female";
                            try {
                                Toast.makeText(insert_data_page.this, "เพศหญิง", Toast.LENGTH_SHORT ).show();
                            }catch (NumberFormatException e){
                                e.printStackTrace();
                                alert("เกิดข้อผิดพลาดบางอย่างขึ้น");
                            }
                            startActivity(to_stepCounter_app);
                            //GlobalVariable.calibrate_check = true;
                        } else if(selectedRatioBtn == null){
                            alert("กรุณาเลือกเพศ");
                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    alert("กรุณากรอกข้อมูลให้ถูกต้อง");
                }
                }
            }
        });

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                selectedRatioBtn = radioGroup.findViewById(radioGroup.getCheckedRadioButtonId());
                String get_firstname = insert_firstname.getText().toString();
                String get_lastname = insert_lastname.getText().toString();
                String get_weight = insert_weight.getText().toString();
                String get_height = insert_height.getText().toString();
                Intent to_form_page = new Intent(insert_data_page.this,form_walking_data.class);
                if((get_weight.isEmpty()) || (get_height.isEmpty()) || (get_firstname.isEmpty()) || (get_lastname.isEmpty())){
                    alert("กรุณากรอกข้อมูลให้ครบถ้วน");
                }
                else{
                try {
                 if ((!get_weight.isEmpty()) && (!get_height.isEmpty())) {
                        GlobalVariable.firstName = get_firstname;
                        GlobalVariable.lastName = get_lastname;
                        weight = Double.parseDouble(get_weight);
                        height = Double.parseDouble(get_height);
                        GlobalVariable.weight = weight;
                        GlobalVariable.height = height;
                        if ((gender == "ชาย")) {
                            GlobalVariable.gender = "male";
                            try {
                                Toast.makeText(insert_data_page.this, "เพศชาย", Toast.LENGTH_SHORT).show();
                            }catch (NumberFormatException e){
                                e.printStackTrace();
                                alert("เกิดข้อผิดพลาดบางอย่างขึ้น");
                            }
                            startActivity(to_form_page);
                        } else if ((gender == "หญิง")) {
                            GlobalVariable.gender = "female";
                            try {
                                Toast.makeText(insert_data_page.this, "เพศหญิง", Toast.LENGTH_SHORT ).show();
                            }catch (NumberFormatException e){
                                e.printStackTrace();
                                alert("เกิดข้อผิดพลาดบางอย่างขึ้น");
                            }
                            startActivity(to_form_page);
                        } else if(selectedRatioBtn == null){
                            alert("กรุณาเลือกเพศ");
                        }
                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    alert("กรุณากรอกข้อมูลให้ถูกต้อง");
                }
                }
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back_page = new Intent(getApplicationContext(),data_before_test_page.class);
                startActivity(back_page);
                finish();
            }
        });

    }

    public void alert(String message){
        AlertDialog dlg = new AlertDialog.Builder(insert_data_page.this)
                .setTitle("Alert").setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dlg.show();
    }
    public void showpopup(View v){
        TextView close_text;
        Button close_button;
        myDialog.setContentView(R.layout.custompopup);
        close_text = (TextView) myDialog.findViewById(R.id.close_text);
        close_button = (Button) myDialog.findViewById(R.id.close_button);
        close_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        close_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch(checkedId){
            case R.id.male_ratio:
                gender = "ชาย";
                break;
            case R.id.female_ratio:
                gender = "หญิง";
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