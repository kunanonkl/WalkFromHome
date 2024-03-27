package com.example.walkfromhome;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class comment_to_user_page_guest extends AppCompatActivity {

    private Button first_page_but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_to_user_page);

        first_page_but = findViewById(R.id.first_page__but);
        Intent next_page = new Intent(comment_to_user_page_guest.this,show_test_result_page.class);
        Intent guest_result_page = new Intent(comment_to_user_page_guest.this,show_test_result_guest.class);
        first_page_but.setOnClickListener(new View.OnClickListener() {
            Intent next_page = new Intent(comment_to_user_page_guest.this,show_test_result_page.class);
            @Override
            public void onClick(View v) {
                if(GlobalVariable.login_check == true){
                    startActivity(next_page);
                    finish();
                }
                else{
                    startActivity(guest_result_page);
                    finish();
                }
            }
        });

    }
}