package com.example.flashcardcuoiky;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Button;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class FlashcardsLibraryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcards_library);

        ImageView back = findViewById(R.id.imageViewBack);
        Button buttonOpen = findViewById(R.id.buttonOpenFlashcard);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (buttonOpen != null) {
            buttonOpen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(FlashcardsLibraryActivity.this, FlashcardActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}

