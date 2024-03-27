package com.example.walkfromhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class profile_page extends AppCompatActivity {

    ////// เอาไปใส่แก้ไขในหน้า profile page ////////
    private TextView welcome_fullname,lastname,firstname,userId,logout_txt;
    private Button user_data_button,start_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        // define variable to test_data_page activity
        Intent to_result_page = new Intent(profile_page.this,test_data_page.class);
        Intent to_howToDoPage = new Intent(profile_page.this,how_to_do_6mwt_page.class);

        // เชื่อมต่อ UI component เข้ากับตัวแปร
        welcome_fullname = findViewById(R.id.textViewFullname);
        firstname = findViewById(R.id.name_profile);
        lastname = findViewById(R.id.lastname_profile);
        user_data_button = findViewById(R.id.user_test_data_button);
        start_button = findViewById(R.id.start_test_button);
        userId = findViewById(R.id.userId);
        logout_txt = findViewById(R.id.logout_txt);
        logout_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login_page = new Intent(getApplicationContext(), com.example.walkfromhome.login_page.class);
                SharedPrefManager.getInstance(getApplicationContext()).logout();
                startActivity(login_page);
                finish();
                GlobalVariable.login_check = false;
                
            }
        });

        firstname.setText("ชื่อ :  "+SharedPrefManager.getInstance(this).getfullname());
        lastname.setText("นามสกุล :  "+(SharedPrefManager.getInstance(this).getLastname()));
        welcome_fullname.setText("สวัสดีคุณ  "+SharedPrefManager.getInstance(this).getUsername());
        userId.setText("WALK ID : "+SharedPrefManager.getInstance(this).getID());

        // เก็บตัวแปรไว้สำหรับ ดึงค่า WALK DATA
        GlobalVariable.walk_id = SharedPrefManager.getInstance(this).getID();

        user_data_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(to_result_page);
                finish();
            }
        });

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(to_howToDoPage);
                finish();
            }
        });

    }
}