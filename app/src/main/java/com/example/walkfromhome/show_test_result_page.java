package com.example.walkfromhome;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class show_test_result_page extends AppCompatActivity implements View.OnClickListener{

    private TextView lastname_txt,firstname_txt,distance_txt;
    private Button record_button,not_record_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_test_result_page);
        firstname_txt =  findViewById(R.id.name);
        lastname_txt = findViewById(R.id.lastname);
        distance_txt = findViewById(R.id.distance);
        record_button = findViewById(R.id.record_button);
        not_record_button = findViewById(R.id.not_record_button);
        record_button.setOnClickListener(this);
        not_record_button.setOnClickListener(this);

        firstname_txt.setText("ชื่อ :  "+SharedPrefManager.getInstance(this).getfullname());
        lastname_txt.setText("นามสกุล :  "+(SharedPrefManager.getInstance(this).getLastname()));
        distance_txt.setText(String.format("%.3f",GlobalVariable.walking_distance) );

    }
    @Override
    public void onClick(View v) {
        if(v == record_button){
            createUserWalk();
        }
        else if(v == not_record_button){
            Intent to_profile_page = new Intent(this,profile_page.class);
            startActivity(to_profile_page);
            finish();
        }
    }

    private void createUserWalk() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_CREATE_TABLE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    Intent to_final_page = new Intent(getApplicationContext(),final_page_app.class);
                    startActivity(to_final_page);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Intent to_final_page = new Intent(getApplicationContext(),final_page_app.class);
                    startActivity(to_final_page);
                    finish();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                String id = String.valueOf(GlobalVariable.walk_id);
                String distance = String.valueOf(GlobalVariable.walking_distance);
                params.put("distance",distance);
                params.put("user_id",id);
                return  params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}