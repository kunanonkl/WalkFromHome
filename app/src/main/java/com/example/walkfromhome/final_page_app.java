package com.example.walkfromhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class final_page_app extends AppCompatActivity {
    private Button show_data_but,return_but;
    private TextView logout_txt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_page_app);
        show_data_but = findViewById(R.id.g_show_data_button);
        return_but = findViewById(R.id.g_return_button);
        logout_txt1 = findViewById(R.id.logout_txt2);
        logout_txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login_page = new Intent(getApplicationContext(), com.example.walkfromhome.login_page.class);
                SharedPrefManager.getInstance(getApplicationContext()).logout();
                startActivity(login_page);
                finish();
                GlobalVariable.login_check = false;
            }
        });


        show_data_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent show_result = new Intent(getApplicationContext(),test_data_page.class);
                startActivity(show_result);
                finish();
            }
        });
        return_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent return_page = new Intent(getApplicationContext(),profile_page.class);
                startActivity(return_page);
                finish();
            }
        });
    }
}