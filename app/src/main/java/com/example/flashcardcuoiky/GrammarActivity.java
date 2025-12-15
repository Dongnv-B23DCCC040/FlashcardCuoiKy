package com.example.flashcardcuoiky;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class GrammarActivity extends AppCompatActivity {

    private ImageView imageViewBack;
    private CardView cardPresentTense, cardPastTense, cardFutureTense;
    private CardView cardConditional, cardPassiveVoice, cardModalVerbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);

        // Initialize views
        imageViewBack = findViewById(R.id.imageViewBack);
        cardPresentTense = findViewById(R.id.cardPresentTense);
        cardPastTense = findViewById(R.id.cardPastTense);
        cardFutureTense = findViewById(R.id.cardFutureTense);
        cardConditional = findViewById(R.id.cardConditional);
        cardPassiveVoice = findViewById(R.id.cardPassiveVoice);
        cardModalVerbs = findViewById(R.id.cardModalVerbs);

        // Back button
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Grammar topic cards
        cardPresentTense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToDetail("Thì hiện tại");
            }
        });

        cardPastTense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToDetail("Thì quá khứ");
            }
        });

        cardFutureTense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToDetail("Thì tương lai");
            }
        });

        cardConditional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToDetail("Câu điều kiện");
            }
        });

        cardPassiveVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToDetail("Câu bị động");
            }
        });

        cardModalVerbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToDetail("Động từ khuyết thiếu");
            }
        });
    }

    private void navigateToDetail(String topic) {
        Intent intent = new Intent(GrammarActivity.this, GrammarDetailActivity.class);
        intent.putExtra("grammar_topic", topic);
        startActivity(intent);
    }
    }

