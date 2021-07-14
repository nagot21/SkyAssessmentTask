package com.example.skyassessmentdayproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {
    private ArrayList<Movie> movielist;

    public recyclerAdapter(ArrayList<Movie> movielist){
        this.movielist = movielist;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView movieText;

        public MyViewHolder(final View view){
            super(view);
            movieText = view.findViewById(R.id.txtMovieName);
        }
    }
    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {

        String movie = movielist.get(position).getMovie();
        holder.movieText.setText(movie);
    }

    @Override
    public int getItemCount() {
        return movielist.size();
    }
}
