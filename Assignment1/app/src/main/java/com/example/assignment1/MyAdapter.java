package com.example.assignment1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/*
Followed this to learn to set up recycler view
https://developer.android.com/guide/topics/ui/layout/recyclerview

Followed this to figure out how to use on click on an item in the recycler view
https://www.youtube.com/watch?v=69C1ljfDvl0
 */

public class MyAdapter extends RecyclerView.Adapter<ViewHolder>{

    //Data
    private ArrayList<Word> mWords;
    //Reference to listener
    private Listener mListener;

    public MyAdapter(ArrayList<Word> words, Listener mListener) {
        mWords = words;
        this.mListener = mListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.text_view_constraint, parent, false);

        ViewHolder viewHolder = new ViewHolder(view, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Word word = mWords.get(position);

        //Gets the UI elements of the view holder
        TextView nameTextView = viewHolder.nameTextView;
        TextView ratingTextView = viewHolder.ratingTextView;
        TextView pronouncitationTextView = viewHolder.pronounciationTextView;
        ImageView imageView = viewHolder.imageView;

        //Sets the content of the UI elements in ViewHolder
        nameTextView.setText(word.getName());
        ratingTextView.setText(word.getRating()+"");
        pronouncitationTextView.setText(word.getPronounciation());
        imageView.setImageResource(ResourceHelper.getDrawableFromName(word.getName()));
    }

    @Override
    public int getItemCount() {
        return mWords.size();
    }
}