package com.chemutai.android;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etPhoneNo, etPassword;
    private Button btnLogin;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (SharedPrefManager.getInstance(this).isLogin()){
            finish();
            startActivity(new Intent(this, BookActivity.class));
            return;
        }

        etPhoneNo = findViewById(R.id.etPhoneNo);
        etPassword = findViewById(R.id.etPassword);

        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
    }

    @Override
    public void onClick(View view) {
        if (view == btnLogin){
            userLogin();
        }
    }

    private void userLogin() {
        final String phone = etPhoneNo.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();

        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (!jsonObject.getBoolean("error")){
                        SharedPrefManager.getInstance(getApplicationContext()).userLogin(
                                jsonObject.getInt("user_id"),
                                jsonObject.getString("name"),
                                jsonObject.getString("phone_no"),
                                jsonObject.getString("national_id")
                        );
                       /* Toast.makeText(getApplicationContext(), "User Login Successful", Toast.LENGTH_LONG).show();*/
                        Intent intent = new Intent(getApplicationContext(), BookActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                /*return super.getParams();*/
                Map<String, String> params = new HashMap<>();
                params.put("phone_no", phone);
                params.put("password", password);
                return params;
            }

        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }
}
