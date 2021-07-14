package com.thismarco.skyassessement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://saman-mb.github.io/SkyMobileDeveloperAcademy/movies.json";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String response;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Log.i("JSON", response);
                jsonResponse = response;
                try {
                    editData(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("VOLLEY", error.toString());
            }
        });

        requestQueue.add(stringRequest);

    }

    private void editData(String s) throws JSONException {

        JSONObject moviesInfo = new JSONObject();
        //moviesInfo
        JSONArray movies = new JSONArray(moviesInfo.getJSONArray("movies"));

        String title = movies.get(0).toString();
        Log.i("Movie Title", title);

    }

}