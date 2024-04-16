package com.example.walkfromhome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class final_page_general_user extends AppCompatActivity {
    private Button return_butt;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private long maxId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_page_general_user);
        database = FirebaseDatabase.getInstance("https://walkfromhome-8a54d-default-rtdb.asia-southeast1.firebasedatabase.app/");
        myRef = database.getReference().child("Data");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    maxId = (snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });

        return_butt = findViewById(R.id.return_button3);
        return_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TestResults testResults = new TestResults();
                testResults.setWeight(GlobalVariable.weight);
                testResults.setHeight(GlobalVariable.height);
                testResults.setGender(GlobalVariable.gender);
                testResults.setStep(GlobalVariable.step);
                testResults.setWalking_distance(GlobalVariable.walking_distance);
                testResults.setWalking_information(GlobalVariable.walking_information);
                testResults.setStride_length(GlobalVariable.stride_length);
                testResults.setId(maxId + 1);
                myRef.child(String.valueOf(maxId + 1)).setValue(testResults);

                Intent go_first_page = new Intent(getApplicationContext(), login_page.class);
                startActivity(go_first_page);
                finish();
            }
        });
    }
}