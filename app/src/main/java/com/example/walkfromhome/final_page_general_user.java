package com.example.walkfromhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class final_page_general_user extends AppCompatActivity {
    private Button return_butt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_page_general_user);
        return_butt = findViewById(R.id.return_button3);
        return_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_first_page = new Intent(getApplicationContext(),login_page.class);
                startActivity(go_first_page);
                finish();
            }
        });
    }
}