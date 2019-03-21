package com.chemutai.android;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etName, etPhoneNo, etNationalId, etPassword;
    private Button btnRegister;
    private ProgressDialog progressDialog;
    private TextView txtAlreadyRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (SharedPrefManager.getInstance(this).isLogin()){
            finish();
            startActivity(new Intent(this, BookActivity.class));
            return;
        }

        etName = findViewById(R.id.etName);
        etPhoneNo = findViewById(R.id.etPhoneNo);
        etNationalId = findViewById(R.id.etNationalId);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        txtAlreadyRegistered = findViewById(R.id.txtAlreadyRegistered);

        progressDialog = new ProgressDialog(this);

        btnRegister.setOnClickListener(this);
        txtAlreadyRegistered.setOnClickListener(this);

    }

    private void registerUser(){
        final String name = etName.getText().toString().trim();
        final String phone = etPhoneNo.getText().toString().trim();
        final String idno = etNationalId.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();

        progressDialog.setMessage("Registering user...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_REGISTER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                //passing json
                try {
                    JSONObject jsonObject = new JSONObject(response);//json string "response"
                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                /*return super.getParams();*/
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("phone_no", phone);
                params.put("national_id", idno);
                params.put("password", password);

                return params;
            }
        };

       /* RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);*/

       RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View view) {
        if (view == btnRegister)
            registerUser();
        if (view == txtAlreadyRegistered){
            startActivity(new Intent(this, LoginActivity.class));
        }

    }
}
