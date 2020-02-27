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
https://developer.android.com/guide/topics/ui/layout/recyclerview
https://www.youtube.com/watch?v=i-TqNzUryn8
*/

public class ListActivity extends AppCompatActivity implements MyAdapter.Listener {

    private Button exitButton;
    private RecyclerView recyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Word> mWords;

    private final static String KEY_WORDS = "words";
    public static final int REQUEST_CODE_DETAILSACTIVITY = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        if(savedInstanceState!=null){
            mWords = savedInstanceState.getParcelableArrayList(KEY_WORDS);
        }

        if(mWords == null){
            mWords = readWordData();
        }

        exitButton = findViewById(R.id.button_list_exit);

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyAdapter(mWords, this);

        recyclerView.setAdapter(mAdapter);
    }

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //save the state

        outState.putParcelableArrayList(KEY_WORDS, mWords);
        super.onSaveInstanceState(outState);
    }

    private ArrayList<Word> readWordData(){
        ArrayList<Word> wordList = new ArrayList<Word>();

        InputStream is = getResources().openRawResource(R.raw.animal_list);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(is, Charset.forName("UTF-8"))
        );

        String line = "";

        try{
            int i = 0;
            while((line = reader.readLine()) != null){
                String[] tokens = line.split(";");

                Word word = new Word(tokens[0], tokens[1], tokens[2], 2.5);
                    wordList.add(word);

                i++;
            }
        } catch (IOException e) {
            Log.e("ListActivity", "Error reading csv file");
            e.printStackTrace();
        }
        return wordList;
    }
}
