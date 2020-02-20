package com.example.assignment1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends
        RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<Word> mWords;

    // Pass in the contact array into the constructor
    public MyAdapter(ArrayList<Word> words) {
        mWords = words;
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView ratingTextView;
        public TextView pronouncitionTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.text_name);
            ratingTextView = (TextView) itemView.findViewById(R.id.text_rating);
            pronouncitionTextView = (TextView) itemView.findViewById(R.id.text_pronounciation);
        }
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.text_view_constraint, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Word word = mWords.get(position);

        // Set item views based on your views and data model
        TextView nameTextView = viewHolder.nameTextView;
        TextView ratingTextView = viewHolder.ratingTextView;
        TextView pronouncitationTextView = viewHolder.pronouncitionTextView;
        nameTextView.setText(word.getName());
        ratingTextView.setText(word.getRating()+"");
        pronouncitationTextView.setText(word.getPronounciation());
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mWords.size();
    }
}