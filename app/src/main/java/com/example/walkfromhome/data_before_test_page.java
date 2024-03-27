package com.example.walkfromhome;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class data_before_test_page extends AppCompatActivity {
    Dialog myDialog;
    TextInputEditText pulse_text,oxy_text,bloodpress_text;
    Button back,go,increase_level,decrease_level;
    TextView click_text,show_text,level_text;
    Double num_pulse,num_oxy,num_bloodpress;
    private int level = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_before_test_page);

        myDialog = new Dialog(this);
        pulse_text = findViewById(R.id.input_pulse_rate_before);
        oxy_text = findViewById(R.id.input_o2_before);
        bloodpress_text = findViewById(R.id.input_blood_pressure_before);
        go = findViewById(R.id.data_before_test_next_button);
        back = findViewById(R.id.data_before_test_back_button);
        click_text = findViewById(R.id.click_tried_view);

        increase_level = findViewById(R.id.increase_but);
        decrease_level = findViewById(R.id.decrease_but);
        level_text = findViewById(R.id.level_select);
        level_text.setText(String.format("%d",level));
        increase_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level = level+1;
                level_text.setText(String.format("%d",level));
                if(level > 10) {
                    alert("ค่าความเหนื่อยเกินกว่าที่กำหนดกรุณากรอกให้ถูกต้อง");
                    level = 10;
                    level_text.setText(String.format("%d",level));
                }
            }
        });
        decrease_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                level = level - 1;
                level_text.setText(String.format("%d",level));
                if(level < 1){
                    alert("ค่าความเหนื่อยต่ำกว่าที่กำหนดกรุณากรอกให้ถูกต้อง");
                    level = 1;
                    level_text.setText(String.format("%d",level));
                }
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back_page = new Intent(getApplicationContext(),how_to_do_6mwt_page.class);
                startActivity(back_page);
                finish();
            }
        });

        /*if((!pulse_string.isEmpty())&& (!oxy_string.isEmpty())&& (!bloodpress_string.isEmpty())){
            num_pulse = Double.parseDouble(pulse_string);
            num_oxy = Double.parseDouble(oxy_string);
            num_bloodpress = Double.parseDouble(bloodpress_string);
        }*/

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if((num_pulse.isNaN()) && (num_oxy.isNaN()) && (num_bloodpress.isNaN())){
                    alert("กรุณากรอกข้อมูลให้ครบถ้วน");
                }*/
                String pulse_string, oxy_string, bloodpress_string;
                pulse_string = pulse_text.getText().toString();
                oxy_string = oxy_text.getText().toString();
                bloodpress_string = bloodpress_text.getText().toString();
                try {
                    if ((pulse_string.isEmpty()) || (oxy_string.isEmpty()) || (bloodpress_string.isEmpty())) {
                        alert("กรุณากรอกข้อมูลให้ครบถ้วน");
                    } else if ((!pulse_string.isEmpty()) && (!oxy_string.isEmpty()) || (!bloodpress_string.isEmpty())) {
                        num_pulse = Double.parseDouble(pulse_string);
                        num_oxy = Double.parseDouble(oxy_string);
                        num_bloodpress = Double.parseDouble(bloodpress_string);

                        if ((num_pulse >= 101) || (num_oxy <= 59) || (level >= 5)) {
                            Intent intent_danger = new Intent(getApplicationContext(), danger_page.class);
                            startActivity(intent_danger);
                            finish();
                        } else {
                            Intent intent_good = new Intent(getApplicationContext(), good_page.class);
                            startActivity(intent_good);
                            finish();
                        }
                        //test รับค่าตัวแปร
                        /*Toast.makeText(data_before_test_page.this,pulse_string,Toast.LENGTH_LONG).show();*/
                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    alert("กรุณากรอกข้อมูลให้ถูกต้อง");
                }
            }
        });

    }

    public void alert(String message){
        AlertDialog dlg = new AlertDialog.Builder(data_before_test_page.this)
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
}