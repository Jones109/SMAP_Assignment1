package com.example.assignment1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

/*
Followed this to learn to set up recycler view
https://developer.android.com/guide/topics/ui/layout/recyclerview

Followed this to read from csv file
https://www.youtube.com/watch?v=i-TqNzUryn8
*/

public class ListActivity extends AppCompatActivity implements Listener {

    //UI
    private Button exitButton;
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    //Data
    private ArrayList<Word> mWords;

    //Consts
    private final static String KEY_WORDS = "words";
    public static final int REQUEST_CODE_DETAILSACTIVITY = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //Read from saved instance
        if(savedInstanceState!=null){
            mWords = savedInstanceState.getParcelableArrayList(KEY_WORDS);
        }

        //Make sure word list is initialized
        if(mWords == null){
            mWords = readWordData();
        }

        //Setup button
        exitButton = findViewById(R.id.button_list_exit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });

        //Setup recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(mWords, this);
        recyclerView.setAdapter(mAdapter);
    }

    //This method is called when an item in the recyclerview is called
    @Override
    public void onClick(int position) {
        Intent intent = new Intent(ListActivity.this, DetailsActivity.class);
        Word word = mWords.get(position);
        intent.putExtra("name", word.getName());
        intent.putExtra("pronounciation", word.getPronounciation());
        intent.putExtra("description", word.getDescription());
        intent.putExtra("rating", word.getRating());
        intent.putExtra("notes", word.getNotes());
        startActivityForResult(intent, REQUEST_CODE_DETAILSACTIVITY);
    }

    //Receives result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode){
            case REQUEST_CODE_DETAILSACTIVITY:
                if(resultCode == RESULT_OK){
                    for(int i = 0; i<mWords.size(); i++){
                        Word word = mWords.get(i);
                        if(word.getName().equalsIgnoreCase(data.getStringExtra("name"))){
                            Word newWord = new Word(
                                    word.getName(),
                                    word.getPronounciation(),
                                    word.getDescription(),
                                    Double.parseDouble(data.getStringExtra("rating")));
                            newWord.setNotes(data.getStringExtra("notes"));
                            mWords.set(i, newWord);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext(),"No Changes made",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //Save list of words in the saved instance
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(KEY_WORDS, mWords);
        super.onSaveInstanceState(outState);
    }

    //Method to read the CSV file
    private ArrayList<Word> readWordData(){
        ArrayList<Word> wordList = new ArrayList<Word>();

        InputStream is = getResources().openRawResource(R.raw.animal_list);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";

        try{
            while((line = reader.readLine()) != null){
                String[] tokens = line.split(";");

                Word word = new Word(tokens[0], tokens[1], tokens[2], 2.5);
                    wordList.add(word);
            }
        } catch (IOException e) {
            Log.e("ListActivity", "Error reading csv file");
            e.printStackTrace();
        }
        return wordList;
    }
}
