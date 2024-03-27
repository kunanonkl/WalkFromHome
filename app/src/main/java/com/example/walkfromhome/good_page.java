package com.example.walkfromhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class good_page extends AppCompatActivity {
    Button next_button,back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_page);

        next_button = findViewById(R.id.next_button);
        back_button = findViewById(R.id.back_button2);
        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_next_page = new Intent(getApplicationContext(),insert_data_page.class);
                startActivity(intent_next_page);
                finish();
            }
        });
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_back_page = new Intent(getApplicationContext(),data_before_test_page.class);
                startActivity(intent_back_page);
                finish();
            }
        });
    }
}