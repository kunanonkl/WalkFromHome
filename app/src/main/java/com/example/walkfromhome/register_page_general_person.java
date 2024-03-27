package com.example.walkfromhome;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class register_page_general_person extends AppCompatActivity implements View.OnClickListener{

    EditText fullname_text, lastname_text, phone_text, username_text, password_text, email_text;
    Button back_button, register_button;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page_general_person);


        fullname_text = findViewById(R.id.fullname_register);
        lastname_text = findViewById(R.id.last_name_register);
        phone_text = findViewById(R.id.phone_register);
        username_text = findViewById(R.id.general_username);
        password_text = findViewById(R.id.general_password);
        email_text = findViewById(R.id.email_register);
        back_button = findViewById(R.id.back_button_general_person);
        register_button = findViewById(R.id.register_general_button);

        progressDialog = new ProgressDialog(this);

        back_button.setOnClickListener(this);
        register_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final String fullname1 = fullname_text.getText().toString().trim();
        final String lastname1 = lastname_text.getText().toString().trim();
        final String phone1 = phone_text.getText().toString().trim();
        final String username1 = username_text.getText().toString().trim();
        final String password1 = password_text.getText().toString().trim();
        final String email1 = email_text.getText().toString().trim();
        if(v == register_button) {
            if(!fullname1.equals("")&&!lastname1.equals("")&&!phone1.equals("")&&!username1.equals("")&&!email1.equals("")&&!password1.equals("")) {
                registerUser();
            }else{
                Toast.makeText(getApplicationContext(),"Please Insert Fields",Toast.LENGTH_LONG).show();
            }
        }
        if(v == back_button){
            Intent intent_to_login = new Intent(getApplicationContext(),login_page.class);
            startActivity(intent_to_login);
            finish();
        }
    }

    private void registerUser() {

        progressDialog.setMessage("Registering User .. ..");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                    Intent intent_login = new Intent(getApplicationContext(),login_data_page.class);
                    startActivity(intent_login);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                final String fullname = fullname_text.getText().toString().trim();
                final String lastname = lastname_text.getText().toString().trim();
                final String phone = phone_text.getText().toString().trim();
                final String username = username_text.getText().toString().trim();
                final String password = password_text.getText().toString().trim();
                final String email = email_text.getText().toString().trim();
                Map<String,String> params = new HashMap<>();
                params.put("fullname",fullname);
                params.put("lastname",lastname);
                params.put("phone",phone);
                params.put("username",username);
                params.put("password",password);
                params.put("email",email);
                return  params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

}