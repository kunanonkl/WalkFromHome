package com.example.walkfromhome;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.time.LocalDate;

@RequiresApi(api = Build.VERSION_CODES.O)
public class comment_to_user_page_guest extends AppCompatActivity {

    private Button first_page_but;

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private long maxId = 0;
    private final String day = String.valueOf(LocalDate.now().getDayOfMonth());
    private final String month = String.valueOf(LocalDate.now().getMonthValue());
    private final String year = String.valueOf(LocalDate.now().getYear());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_to_user_page);

        database = FirebaseDatabase.getInstance("https://walkfromhome-431de-default-rtdb.asia-southeast1.firebasedatabase.app/");
        myRef = database.getReference().child("Data");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    maxId = snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });

        first_page_but = findViewById(R.id.first_page__but);
        Intent guest_result_page = new Intent(comment_to_user_page_guest.this,show_test_result_guest.class);
        first_page_but.setOnClickListener(new View.OnClickListener() {
            Intent next_page = new Intent(comment_to_user_page_guest.this,show_test_result_page.class);
            @Override
            public void onClick(View v) {
                save();
                if(GlobalVariable.login_check){
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
    public  void save(){
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
        myRef.child(String.valueOf(maxId + 1)).setValue(testResults);
    }

    public void alert(String message){
        AlertDialog dlg = new AlertDialog.Builder(this)
                .setTitle("Alert").setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create();
        dlg.show();
    }
}