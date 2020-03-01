package com.example.assignment1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class DetailsActivity extends AppCompatActivity {

    //UI
    private TextView nameText;
    private TextView pronounciationText;
    private TextView descriptionText;
    private TextView noteText;
    private ImageView imageView;
    private TextView ratingText;
    private Button cancelButton;
    private Button editButton;

    //Consts
    public static final int REQUEST_CODE_EDITACTIVITY= 102;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        //Get values from intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String pronounciation = intent.getStringExtra("pronounciation");
        String description = intent.getStringExtra("description");
        String notes = intent.getStringExtra("notes");
        double rating = intent.getDoubleExtra("rating", 2.5);

        //Find UI views
        nameText = findViewById(R.id.text_details_name);
        pronounciationText = findViewById(R.id.text_details_pronounciation);
        descriptionText = findViewById(R.id.text_details_desciption);
        noteText = findViewById(R.id.text_details_notes);
        ratingText = findViewById(R.id.text_details_rating);
        imageView = findViewById(R.id.image_details_animal);

        //Set value of views
        nameText.setText(name);
        pronounciationText.setText(pronounciation);
        descriptionText.setText(description);
        if(!notes.contentEquals(""))
            noteText.setText(notes);
        imageView.setImageResource(ResourceHelper.getDrawableFromName(name));
        ratingText.setText(rating+"");

        //Setup buttons
        cancelButton = findViewById(R.id.button_details_cancel);
        editButton = findViewById(R.id.button_details_edit);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, EditActivity.class);
                intent.putExtra("name", nameText.getText());
                intent.putExtra("rating", ratingText.getText());
                intent.putExtra("notes", noteText.getText());
                startActivityForResult(intent, REQUEST_CODE_EDITACTIVITY);
            }
        });
    }

    //Receives result after editing
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode){
            case REQUEST_CODE_EDITACTIVITY:
                if(resultCode == RESULT_OK){
                    ratingText.setText(data.getStringExtra("rating"));
                    noteText.setText(data.getStringExtra("notes"));

                    Intent returnData = new Intent();
                    returnData.putExtra("rating", ratingText.getText());
                    returnData.putExtra("notes", noteText.getText());
                    returnData.putExtra("name", nameText.getText());
                    setResult(RESULT_OK, returnData);
                    finish();
                } else {
                    //Do nothing
                }
                break;
        }
    }
}
