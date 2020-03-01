package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class EditActivity extends AppCompatActivity {

    //UI
    private Button cancelButton;
    private Button okButton;
    private TextView nameText;
    private TextView noteText;
    private TextView ratingText;
    private SeekBar ratingSlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //Get data from intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String notes = intent.getStringExtra("notes");
        double rating = Double.parseDouble(intent.getStringExtra("rating"));

        //Get UI views
        nameText = findViewById(R.id.edit_text_name);
        noteText = findViewById(R.id.text_edit_notes);
        ratingText = findViewById(R.id.text_edit_rating);
        ratingSlider = findViewById(R.id.rating_slider);

        //Set content of views
        nameText.setText(name);
        noteText.setText(notes);
        ratingText.setText(rating+"");
        ratingSlider.setProgress((int)(rating*10));

        //Setup buttons
        cancelButton = findViewById(R.id.button_edit_cancel);
        okButton = findViewById(R.id.button_edit_ok);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnData = new Intent();
                returnData.putExtra("rating", ratingText.getText());
                returnData.putExtra("notes", noteText.getText().toString());
                setResult(RESULT_OK, returnData);
                finish();
            }
        });

        //Setup slider
        ratingSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ratingText.setText(((double)progress)/10.0+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
