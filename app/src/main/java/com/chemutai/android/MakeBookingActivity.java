package com.chemutai.android;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.loopj.android.http.*;

import org.json.JSONArray;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;
import okhttp3.Route;

import static com.chemutai.android.Constants.URL_ROUTE_SPINNER;

public class MakeBookingActivity extends AppCompatActivity {

    private TextView txtPhoneNo;

    private Spinner routeSpinner, busCompanySpinner, seatNoSpinner;
    private RequestQueue queue;
    private ProgressDialog dialog;

    private Button pickUpLocation;
    private AsyncHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_booking);

        client = new AsyncHttpClient();

        txtPhoneNo = findViewById(R.id.txtPhoneNo);
        txtPhoneNo.setText(SharedPrefManager.getInstance(this).getPhoneNo());

        routeSpinner = findViewById(R.id.spinnerRoute);
        busCompanySpinner = findViewById(R.id.spinnerBusCompany);
        seatNoSpinner = findViewById(R.id.spinnerSeatNo);

        pickUpLocation = findViewById(R.id.btnLocation);

        dialog = new ProgressDialog(this);
        dialog.setMessage("Booking");

        /*routeSpinner();*/

        routeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


  /*  private void routeSpinner(){
        client.post(URL_ROUTE_SPINNER, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 100){
                    rtSpinner(new String (responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void rtSpinner(String response) {
        ArrayList<Route> listR = new ArrayList<Route>();

        try{
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++){
                Route route_name = new Route();
                route_name.setRoute_name(jsonArray.getJSONObject(i).getString("route_name"));
                listR.add(route_name);
            }
            ArrayAdapter<Route> r = new ArrayAdapter<Route>(this, android.R.layout.simple_spinner_dropdown_item);
            routeSpinner.setAdapter(r);
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/

    //onClicking button choose location
    public void chooseLocation(View view) {
    }


}




 /*routeSpinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        Constants.URL_ROUTE_SPINNER, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
            }
        });*/

       /*//Route spinner

        String[] route = new String[]{};

        List<String> routeList = new ArrayList<>(Arrays.asList(route));
        ArrayAdapter<String> routeArrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_make_booking, routeList);
        routeArrayAdapter.setDropDownViewResource(R.layout.activity_make_booking);
        routeSpinner.setAdapter(routeArrayAdapter);

        routeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                routeSpinner.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "Route cannot be null", Toast.LENGTH_SHORT).show();
            }
        });

        //Bus Company spinner

        String[] busCompany = new String[]{};
        List<String> busCompanyList = new ArrayList<>(Arrays.asList(busCompany));
        ArrayAdapter<String> busCompanyArrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_make_booking, busCompanyList);
        routeArrayAdapter.setDropDownViewResource(R.layout.activity_make_booking);
        busCompanySpinner.setAdapter(busCompanyArrayAdapter);

        busCompanySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                busCompanySpinner.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                Toast.makeText(getApplicationContext(), "Bus Company cannot be null", Toast.LENGTH_SHORT).show();
            }
        });*/
