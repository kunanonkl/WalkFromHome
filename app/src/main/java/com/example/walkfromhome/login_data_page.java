package com.example.walkfromhome;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class login_data_page extends AppCompatActivity implements View.OnClickListener{
    Button back_button,login_button;
    TextView register_click;
    EditText username_text,password_text;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_data_page);

        back_button = findViewById(R.id.back_button);
        login_button = findViewById(R.id.login2_button);
        register_click = findViewById(R.id.register_click);
        username_text = findViewById(R.id.login_data_page_text_username);
        password_text = findViewById(R.id.login_data_page_password);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait....");
        //progressBar = findViewById(R.id.progress);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_back_to_login_page = new Intent(login_data_page.this,login_page.class);
                startActivity(intent_back_to_login_page);
                finish();
            }
        });

        register_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_register_page = new Intent(login_data_page.this,register_page_general_person.class);
                startActivity(intent_register_page);
                finish();
            }
        });
        login_button.setOnClickListener(this);
    }
    private void userLogin(){

        final String username = username_text.getText().toString().trim();
        final String password = password_text.getText().toString().trim();
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject obj = new JSONObject(response);
                    if(!obj.getBoolean("error")){
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(
                                obj.getInt("walk_id"),
                                obj.getString("username"),
                                obj.getString("email"),
                                obj.getString("fullname"),
                                obj.getString("lastname"),
                                obj.getString("phone")
                        );
                        Toast.makeText(getApplicationContext(),"User Login Successful!!!",Toast.LENGTH_LONG).show();
                        Intent intent_profile = new Intent(getApplicationContext(),profile_page.class);
                        startActivity(intent_profile);
                        finish();
                        GlobalVariable.login_check = true;
                    }else{
                        Toast.makeText(getApplicationContext(),obj.getString("message"),Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {
        if(v == login_button){
            final String username_check = username_text.getText().toString().trim();
            final  String password_check = password_text.getText().toString().trim();
            if(!username_check.equals("")&&!password_check.equals("")) {
                userLogin();
            }else{
                Toast.makeText(getApplicationContext(),"Please Insert Fields",Toast.LENGTH_LONG).show();
            }
        }
    }
}

