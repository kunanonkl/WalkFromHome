package com.example.walkfromhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class login_page extends AppCompatActivity {
    Button login_button,register_button,guest_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        login_button = findViewById(R.id.login_button);
        register_button = findViewById(R.id.register_button);
        guest_button = findViewById(R.id.guest_button);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_login_data_page = new Intent(login_page.this,login_data_page.class);
                startActivity(intent_login_data_page);
                finish();
            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_register_data_page = new Intent(getApplicationContext(),register_page_general_person.class);
                startActivity(intent_register_data_page);
                finish();
            }
        });

        guest_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_guest_page = new Intent(login_page.this,how_to_do_6mwt_page.class);
                startActivity(intent_guest_page);
                finish();
            }
        });
    }
}