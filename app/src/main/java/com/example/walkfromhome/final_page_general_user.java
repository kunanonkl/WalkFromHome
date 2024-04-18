package com.example.walkfromhome;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalDate;

@RequiresApi(api = Build.VERSION_CODES.O)
public class final_page_general_user extends AppCompatActivity {
    private Button return_butt;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private long maxId = 0;
    private final String day = String.valueOf(LocalDate.now().getDayOfMonth());
    private final String month = String.valueOf(LocalDate.now().getMonthValue());
    private final String year = String.valueOf(LocalDate.now().getYear());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_page_general_user);
        database = FirebaseDatabase.getInstance("https://walkfromhome-431de-default-rtdb.asia-southeast1.firebasedatabase.app/");
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

        TestResults testResults = getTestResults();
        myRef.child(String.valueOf(maxId + 1)).setValue(testResults);

        return_butt = findViewById(R.id.return_button3);
        return_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent go_first_page = new Intent(getApplicationContext(), login_page.class);
                startActivity(go_first_page);
                finish();
            }
        });
    }

    @NonNull
    private TestResults getTestResults() {
        TestResults testResults = new TestResults();
        testResults.setFirstName(GlobalVariable.firstName);
        testResults.setLastName(GlobalVariable.lastName);
        testResults.setWeight(GlobalVariable.weight);
        testResults.setHeight(GlobalVariable.height);
        testResults.setGender(GlobalVariable.gender);
        testResults.setStep(GlobalVariable.step);
        testResults.setWalking_distance(GlobalVariable.walking_distance);
        testResults.setWalking_information(GlobalVariable.walking_information);
        testResults.setStride_length(GlobalVariable.stride_length);
        testResults.setId(maxId + 1);
        testResults.setHeartRate(GlobalVariable.heartRate);
        testResults.setBloodOxygen(GlobalVariable.bloodOxygen);
        testResults.setBloodPress(GlobalVariable.bloodPress);
        testResults.setExhaustion(GlobalVariable.exhaustion);
        testResults.setDate(day + "/" + month + "/" + year);
        return testResults;
    }
}