package com.example.flashcardcuoiky;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListenActivity extends AppCompatActivity {

    private ImageView imageViewPlayAudio;
    private TextView textViewTerm, textViewPhonetic, textViewTranslation;
    private Button buttonAnswer1, buttonAnswer2, buttonAnswer3, buttonAnswer4;
    private TextView textViewShowAnswer;
    private TextView textViewProgress;
    private View buttonPrevious, buttonNext;

    // Question data
    private List<VocabularyHelper.Question> questions;
    private int currentQuestionIndex = 0;
    private int totalQuestions = 147;
    private String setTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);

        // Get set title from intent
        Intent intent = getIntent();
        if (intent != null) {
            setTitle = intent.getStringExtra("set_title");
            if (setTitle == null) {
                setTitle = "";
            }
        }

        // Initialize views
        imageViewBack = findViewById(R.id.imageViewBack);
        imageViewPlayAudio = findViewById(R.id.imageViewPlayAudio);
        textViewTerm = findViewById(R.id.textViewTerm);
        textViewPhonetic = findViewById(R.id.textViewPhonetic);
        textViewTranslation = findViewById(R.id.textViewTranslation);
        buttonAnswer1 = findViewById(R.id.buttonAnswer1);
        buttonAnswer2 = findViewById(R.id.buttonAnswer2);
        buttonAnswer3 = findViewById(R.id.buttonAnswer3);
        buttonAnswer4 = findViewById(R.id.buttonAnswer4);
        textViewShowAnswer = findViewById(R.id.textViewShowAnswer);
        textViewProgress = findViewById(R.id.textViewProgress);
        buttonPrevious = findViewById(R.id.buttonPrevious);
        buttonNext = findViewById(R.id.buttonNext);

        // Initialize questions
        initializeQuestions();
        
        // Display first question
        displayQuestion(currentQuestionIndex);

        // Back button
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Play audio button
        imageViewPlayAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ListenActivity.this, "Phát âm: " + questions.get(currentQuestionIndex).term, Toast.LENGTH_SHORT).show();
            }
        });

        // Answer buttons
        buttonAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(buttonAnswer1, 0);
            }
        });

        buttonAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(buttonAnswer2, 1);
            }
        });

        buttonAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(buttonAnswer3, 2);
            }
        });

        buttonAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(buttonAnswer4, 3);
            }
        });

        // Show answer button
        textViewShowAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestionIndex < questions.size()) {
                    VocabularyHelper.Question question = questions.get(currentQuestionIndex);
                    resetAnswerButtons();
                    Button correctButton = getAnswerButton(question.correctAnswer);
                    correctButton.setBackgroundResource(R.drawable.bg_answer_correct);
                    correctButton.setTextColor(getResources().getColor(R.color.white));
                    textViewTranslation.setVisibility(View.VISIBLE);
                }
            }
        });

        // Navigation buttons
        buttonPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestionIndex > 0) {
                    currentQuestionIndex--;
                    displayQuestion(currentQuestionIndex);
                    updateProgress();
                }
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestionIndex < questions.size() - 1) {
                    currentQuestionIndex++;
                    displayQuestion(currentQuestionIndex);
                    updateProgress();
                }
            }
        });
    }

    private void initializeQuestions() {
        // Load vocabulary based on set title
        if (setTitle != null && (setTitle.equalsIgnoreCase("Động vật") || setTitle.equalsIgnoreCase("Dong vat"))) {
            questions = VocabularyHelper.getAnimalVocabulary();
        } else {
            questions = VocabularyHelper.getDefaultVocabulary();
        }
        totalQuestions = questions.size();
    }

    private void displayQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            VocabularyHelper.Question question = questions.get(index);
            textViewTerm.setText(question.term);
            textViewPhonetic.setText(question.phonetic);
            textViewTranslation.setText(question.translation);
            textViewTranslation.setVisibility(View.GONE);
            
            // Set answer options
            buttonAnswer1.setText("1: " + question.options[0]);
            buttonAnswer2.setText("2: " + question.options[1]);
            buttonAnswer3.setText("3: " + question.options[2]);
            buttonAnswer4.setText("4: " + question.options[3]);
            
            // Reset button styles
            resetAnswerButtons();
            
            // Update navigation buttons
            buttonPrevious.setEnabled(index > 0);
            buttonNext.setEnabled(index < questions.size() - 1);
        }
    }

    private void updateProgress() {
        textViewProgress.setText((currentQuestionIndex + 1) + " / " + totalQuestions);
    }

    private void resetAnswerButtons() {
        buttonAnswer1.setBackgroundResource(R.drawable.bg_answer);
        buttonAnswer2.setBackgroundResource(R.drawable.bg_answer);
        buttonAnswer3.setBackgroundResource(R.drawable.bg_answer);
        buttonAnswer4.setBackgroundResource(R.drawable.bg_answer);
        buttonAnswer1.setTextColor(getResources().getColor(R.color.white));
        buttonAnswer2.setTextColor(getResources().getColor(R.color.white));
        buttonAnswer3.setTextColor(getResources().getColor(R.color.white));
        buttonAnswer4.setTextColor(getResources().getColor(R.color.white));
    }

    private void checkAnswer(Button button, int selectedIndex) {
        if (currentQuestionIndex >= questions.size()) return;
        
        VocabularyHelper.Question question = questions.get(currentQuestionIndex);
        boolean isCorrect = (selectedIndex == question.correctAnswer);
        
        // Reset all buttons
        resetAnswerButtons();

        if (isCorrect) {
            button.setBackgroundResource(R.drawable.bg_answer_correct);
            button.setTextColor(getResources().getColor(R.color.white));
            textViewTranslation.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Đúng rồi!", Toast.LENGTH_SHORT).show();
            
            // Auto move to next question after delay
            button.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (currentQuestionIndex < questions.size() - 1) {
                        currentQuestionIndex++;
                        displayQuestion(currentQuestionIndex);
                        updateProgress();
                    }
                }
            }, 1500);
        } else {
            button.setBackgroundResource(R.drawable.bg_answer_wrong);
            button.setTextColor(getResources().getColor(R.color.white));
            // Show correct answer
            Button correctButton = getAnswerButton(question.correctAnswer);
            correctButton.setBackgroundResource(R.drawable.bg_answer_correct);
            correctButton.setTextColor(getResources().getColor(R.color.white));
            textViewTranslation.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Sai rồi! Đáp án đúng: " + question.options[question.correctAnswer], Toast.LENGTH_SHORT).show();
        }
    }

    private Button getAnswerButton(int index) {
        switch (index) {
            case 0: return buttonAnswer1;
            case 1: return buttonAnswer2;
            case 2: return buttonAnswer3;
            case 3: return buttonAnswer4;
            default: return buttonAnswer1;
        }
    }

    private ImageView imageViewBack;
}


