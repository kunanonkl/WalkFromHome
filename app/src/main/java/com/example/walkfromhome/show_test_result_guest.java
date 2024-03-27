package com.example.walkfromhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class show_test_result_guest extends AppCompatActivity {
    private TextView distance_show;
    private Button next_butt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_test_result_guest);
        distance_show = findViewById(R.id.distance);
        next_butt = findViewById(R.id.next_button);
        distance_show.setText(String.format("%.3f",GlobalVariable.walking_distance));
        next_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent final_page_guest = new Intent(getApplicationContext(),final_page_general_user.class);
                startActivity(final_page_guest);
                finish();
            }
        });
    }
}