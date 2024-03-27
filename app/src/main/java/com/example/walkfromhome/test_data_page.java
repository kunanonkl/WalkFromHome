package com.example.walkfromhome;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class test_data_page extends AppCompatActivity {
    WalkData walkData;
    ListView listView;
    UserAdapter adapter;
    String distance;
    Button back_but;
    public static ArrayList<WalkData> walkDataArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_data_page);
        listView = findViewById(R.id.myListView);
        adapter = new UserAdapter(this,walkDataArrayList);
        listView.setAdapter(adapter);
        back_but = findViewById(R.id.back_button);
        back_but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent to_profile_page = new Intent(getApplicationContext(),profile_page.class);
                startActivity(to_profile_page);
                finish();
            }
        });

        retrieveData();

    }
    public void retrieveData(){
        StringRequest request = new StringRequest(Request.Method.POST, Constants.URL_DATA, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                walkDataArrayList.clear();
                try{
                    JSONObject obj = new JSONObject(response);
                    String success = obj.getString("success");
                    JSONArray jsonArray = obj.getJSONArray("data");
                    if(success.equals("1")){
                        for(int i=0;i<jsonArray.length();i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String id = object.getString("userId");
                            int walk_idz = Integer.parseInt(id);
                            if(walk_idz == GlobalVariable.walk_id){
                                distance = object.getString("distance");
                            }
                            walkData = new WalkData(distance);
                            walkDataArrayList.add(walkData);
                            adapter.notifyDataSetChanged();
                        }
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
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
                params.put("userId",id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}