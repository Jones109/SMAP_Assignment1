package com.example.assignment1;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //Holds reference to UI elements of the ViewHolder
    public TextView nameTextView;
    public TextView ratingTextView;
    public TextView pronounciationTextView;
    public ImageView imageView;
    //Reference to listener to call on click
    Listener listener;

    public ViewHolder(View itemView, Listener listener) {
        super(itemView);
        this.listener = listener;
        itemView.setOnClickListener(this);

        //Gets the UI elements and saves a reference to them
        nameTextView = itemView.findViewById(R.id.text_name);
        ratingTextView = itemView.findViewById(R.id.text_rating);
        pronounciationTextView = itemView.findViewById(R.id.text_pronounciation);
        imageView = itemView.findViewById(R.id.image_animal);
    }

    @Override
    public void onClick(View v) {
        //Calls back on the listener with the position
        listener.onClick(getAdapterPosition());
    }
}
