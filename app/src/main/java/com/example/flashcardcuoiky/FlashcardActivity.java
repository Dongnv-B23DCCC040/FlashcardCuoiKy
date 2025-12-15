package com.example.flashcardcuoiky;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

public class FlashcardActivity extends AppCompatActivity {

    private TextView textViewCardNumber;
    private View cardFront, cardBack;
    private TextView textViewFrontWord, textViewBackContent;
    private ImageView imageViewStar, imageViewStarBack;
    private boolean isFlipped = false;
    private boolean isFavorite = false;
    private int currentCard = 1;
    private int totalCards = 147;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flashcard);

        // Initialize views
        textViewCardNumber = findViewById(R.id.textViewCardNumber);
        cardFront = findViewById(R.id.cardFront);
        cardBack = findViewById(R.id.cardBack);
        textViewFrontWord = findViewById(R.id.textViewFrontWord);
        textViewBackContent = findViewById(R.id.textViewBackContent);
        imageViewStar = findViewById(R.id.imageViewStar);
        imageViewStarBack = findViewById(R.id.imageViewStarBack);

        // Set initial card
        updateCard();

        // Back button
        findViewById(R.id.imageViewBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Dropdown menu
        findViewById(R.id.layoutFlashcardHeader).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FlashcardActivity.this, "Chọn chế độ học", Toast.LENGTH_SHORT).show();
            }
        });

        // Flip card - click on card content areas
        View cardFrontContent = findViewById(R.id.cardFrontContent);
        View cardBackContent = findViewById(R.id.cardBackContent);
        
        if (cardFrontContent != null) {
            cardFrontContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flipCard();
                }
            });
        }
        
        if (cardBackContent != null) {
            cardBackContent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    flipCard();
                }
            });
        }
        
        // Also allow clicking on the card container itself
        findViewById(R.id.cardContainer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });

        // Show hint - prevent event propagation
        findViewById(R.id.layoutShowHint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setClickable(true);
                Toast.makeText(FlashcardActivity.this, "Hiển thị gợi ý", Toast.LENGTH_SHORT).show();
            }
        });

        // Favorite/Star
        View.OnClickListener starClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFavorite = !isFavorite;
                int resId = isFavorite ? R.drawable.ic_star_filled : R.drawable.ic_star_outline;
                imageViewStar.setImageResource(resId);
                imageViewStarBack.setImageResource(resId);
            }
        };
        imageViewStar.setOnClickListener(starClickListener);
        imageViewStarBack.setOnClickListener(starClickListener);

        // Navigation buttons
        findViewById(R.id.buttonPrevious).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentCard > 1) {
                    currentCard--;
                    updateCard();
                    resetCard();
                }
            }
        });

        findViewById(R.id.buttonNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentCard < totalCards) {
                    currentCard++;
                    updateCard();
                    resetCard();
                }
            }
        });

        // Play button
        findViewById(R.id.imageViewPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FlashcardActivity.this, "Phát âm", Toast.LENGTH_SHORT).show();
            }
        });

        // Fullscreen button
        findViewById(R.id.imageViewFullscreen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FlashcardActivity.this, "Toàn màn hình", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateCard() {
        textViewCardNumber.setText(currentCard + "/" + totalCards);
        
        // Example card data - in real app, get from database/API
        String[] words = {"Toaster", "Cabinet", "Dishwasher", "Oven", "Microwave"};
        String[] phonetics = {"/toustə/", "/'kæbinit/", "/'diʃ, wɔ:tə/", "/'ʌvən/", "/'maikrəweiv/"};
        String[] translations = {"Máy nướng bánh mỳ", "Tủ", "Máy rửa bát", "Lò nướng", "Lò vi sóng"};
        
        int index = (currentCard - 1) % words.length;
        textViewFrontWord.setText(words[index]);
        textViewBackContent.setText(phonetics[index] + "\n\n" + translations[index]);
    }

    private void flipCard() {
        View visibleCard = isFlipped ? cardBack : cardFront;
        View invisibleCard = isFlipped ? cardFront : cardBack;
        
        // Create rotation animation
        ObjectAnimator flipOut = ObjectAnimator.ofFloat(visibleCard, "rotationY", 0f, 90f);
        flipOut.setDuration(200);
        flipOut.setInterpolator(new AccelerateDecelerateInterpolator());
        
        ObjectAnimator flipIn = ObjectAnimator.ofFloat(invisibleCard, "rotationY", -90f, 0f);
        flipIn.setDuration(200);
        flipIn.setInterpolator(new AccelerateDecelerateInterpolator());
        
        flipOut.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                visibleCard.setVisibility(View.GONE);
                invisibleCard.setVisibility(View.VISIBLE);
                flipIn.start();
            }
        });
        
        flipOut.start();
        isFlipped = !isFlipped;
    }

    private void resetCard() {
        isFlipped = false;
        cardFront.setVisibility(View.VISIBLE);
        cardBack.setVisibility(View.GONE);
    }
}

