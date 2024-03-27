package com.example.walkfromhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class danger_page extends AppCompatActivity {

    Button return_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danger_page);

        return_button = findViewById(R.id.to_data_before);

        return_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_to_data_before_test = new Intent(getApplicationContext(),data_before_test_page.class);
                startActivity(intent_to_data_before_test);
                finish();
            }
        });
    }
}