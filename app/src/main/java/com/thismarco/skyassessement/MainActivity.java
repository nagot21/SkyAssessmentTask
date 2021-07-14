package com.thismarco.skyassessement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.thismarco.skyassessement.Movie;
import android.os.Bundle;
import android.util.Log;
import com.thismarco.skyassessement.recyclerAdapter;
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

public class MainActivity extends AppCompatActivity {

    String jsonResponse;
    private ArrayList<Movie> movielist;
    private RecyclerView recyclerView;
    ArrayList<String> moviesTitle = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String url = "https://saman-mb.github.io/SkyMobileDeveloperAcademy/movies.json";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        recyclerView = findViewById(R.id.RecView);

        movielist = new ArrayList<>();


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
        JSONObject moviesInfo = new JSONObject(s);
        Log.i("JSON", moviesInfo.toString());
        //moviesInfo
        //JSONArray movies = new JSONArray(moviesInfo.getJSONArray("movies"));
        JSONArray movies = new JSONArray(moviesInfo.getString("movies"));
        //Log.i("Movies Number", String.valueOf(movies.length()));
        for(int i = 0; i < movies.length(); i++){
            //Log.i("Movie Title", movies.getJSONObject(i).getString("title"));
            moviesTitle.add(movies.getJSONObject(i).getString("title"));
        }
        //Log.i("ArrayS", String.valueOf(moviesTitle.size()));
        for(String t : moviesTitle){
            Log.i("Title", t);
        }
        //String title = movies.get(0).toString();
        //Log.i("Movie Title", title);
        setMovieInfo();
        setAdapter();
    }


    private void setAdapter(){
        recyclerAdapter adapter = new recyclerAdapter(movielist);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
    private void setMovieInfo(){
        for(int i=0; i<=moviesTitle.size(); i++) {
            movielist.add(new Movie(moviesTitle.get(i)));

        }


    }



}