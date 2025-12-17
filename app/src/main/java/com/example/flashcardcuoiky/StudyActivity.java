package com.example.flashcardcuoiky;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StudyActivity extends AppCompatActivity {

    private View buttonFlashcards, buttonLearn, buttonTest, buttonListen, buttonRead, buttonWrite;
    private TextView textViewQuestion, textViewPhonetic, textViewTranslation;
    private Button buttonAnswer1, buttonAnswer2, buttonAnswer3, buttonAnswer4;
    private TextView textViewDontKnow;
    private TextView textViewProgress;
    private View buttonPrevious, buttonNext;
    private ToggleButton toggleProgress;
    private View imageViewPlay, imageViewFullscreen, imageViewSettings, imageViewLayout;

    // Question data
    private List<VocabularyHelper.Question> questions;
    private int currentQuestionIndex = 0;
    private int totalQuestions = 147;
    private String setTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study);

        // Get set title from intent
        Intent intent = getIntent();
        if (intent != null) {
            setTitle = intent.getStringExtra("set_title");
            if (setTitle == null) {
                setTitle = "";
            }
        }

        // Initialize views
        buttonFlashcards = findViewById(R.id.buttonFlashcards);
        buttonLearn = findViewById(R.id.buttonLearn);
        buttonTest = findViewById(R.id.buttonTest);
        buttonListen = findViewById(R.id.buttonListen);
        buttonRead = findViewById(R.id.buttonRead);
        buttonWrite = findViewById(R.id.buttonWrite);

        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewPhonetic = findViewById(R.id.textViewPhonetic);
        textViewTranslation = findViewById(R.id.textViewTranslation);

        buttonAnswer1 = findViewById(R.id.buttonAnswer1);
        buttonAnswer2 = findViewById(R.id.buttonAnswer2);
        buttonAnswer3 = findViewById(R.id.buttonAnswer3);
        buttonAnswer4 = findViewById(R.id.buttonAnswer4);
        textViewDontKnow = findViewById(R.id.textViewDontKnow);

        // Bottom controls
        textViewProgress = findViewById(R.id.textViewProgress);
        buttonPrevious = findViewById(R.id.buttonPrevious);
        buttonNext = findViewById(R.id.buttonNext);
        toggleProgress = findViewById(R.id.toggleProgress);
        imageViewPlay = findViewById(R.id.imageViewPlay);
        imageViewFullscreen = findViewById(R.id.imageViewFullscreen);
        imageViewSettings = findViewById(R.id.imageViewSettings);
        imageViewLayout = findViewById(R.id.imageViewLayout);

        // Initialize questions based on set title
        initializeQuestions();
        
        // Update total questions
        totalQuestions = questions.size();
        
        // Display first question
        displayQuestion(currentQuestionIndex);

        // Study mode buttons
        buttonFlashcards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetStudyModeButtons();
                buttonFlashcards.setBackgroundResource(R.drawable.bg_study_mode_selected);
                // Navigate to FlashcardActivity
                Intent intent = new Intent(StudyActivity.this, FlashcardActivity.class);
                intent.putExtra("set_title", setTitle);
                startActivity(intent);
            }
        });

        buttonLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetStudyModeButtons();
                buttonLearn.setBackgroundResource(R.drawable.bg_study_mode_selected);
                // Keep Learn button selected by default
            }
        });

        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetStudyModeButtons();
                buttonTest.setBackgroundResource(R.drawable.bg_study_mode_selected);
                // Navigate to TestActivity
                Intent intent = new Intent(StudyActivity.this, TestActivity.class);
                intent.putExtra("set_title", setTitle);
                startActivity(intent);
            }
        });

        buttonListen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetStudyModeButtons();
                buttonListen.setBackgroundResource(R.drawable.bg_study_mode_selected);
                // Navigate to ListenActivity
                Intent intent = new Intent(StudyActivity.this, ListenActivity.class);
                intent.putExtra("set_title", setTitle);
                startActivity(intent);
            }
        });

        buttonRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetStudyModeButtons();
                buttonRead.setBackgroundResource(R.drawable.bg_study_mode_selected);
                // Navigate to ReadActivity
                Intent intent = new Intent(StudyActivity.this, ReadActivity.class);
                intent.putExtra("set_title", setTitle);
                startActivity(intent);
            }
        });

        buttonWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetStudyModeButtons();
                buttonWrite.setBackgroundResource(R.drawable.bg_study_mode_selected);
                // Navigate to WriteActivity
                Intent intent = new Intent(StudyActivity.this, WriteActivity.class);
                intent.putExtra("set_title", setTitle);
                startActivity(intent);
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

        // Don't know button
        textViewDontKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentQuestionIndex < questions.size()) {
                    VocabularyHelper.Question question = questions.get(currentQuestionIndex);
                    resetAnswerButtons();
                    Button correctButton = getAnswerButton(question.correctAnswer);
                    correctButton.setBackgroundResource(R.drawable.bg_answer_correct);
                    correctButton.setTextColor(getResources().getColor(R.color.white));
                    Toast.makeText(StudyActivity.this, "Đáp án đúng: " + question.options[question.correctAnswer], Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Top bar buttons
        findViewById(R.id.imageViewBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.imageViewBookmark).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StudyActivity.this, "Lưu", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.imageViewGroup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StudyActivity.this, "Nhóm", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.imageViewShare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StudyActivity.this, "Chia sẻ", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.imageViewMore).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StudyActivity.this, "Thêm", Toast.LENGTH_SHORT).show();
            }
        });

        // Terms list button
        findViewById(R.id.textViewTermsCount).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to terms list
                android.content.Intent intent = new android.content.Intent(StudyActivity.this, TermsListActivity.class);
                startActivity(intent);
            }
        });

        // Bottom controls
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

        toggleProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle progress tracking
                if (toggleProgress.isChecked()) {
                    Toast.makeText(StudyActivity.this, "Bật theo dõi tiến độ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(StudyActivity.this, "Tắt theo dõi tiến độ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imageViewPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StudyActivity.this, "Phát âm", Toast.LENGTH_SHORT).show();
            }
        });

        imageViewFullscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StudyActivity.this, "Toàn màn hình", Toast.LENGTH_SHORT).show();
            }
        });

        imageViewSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StudyActivity.this, "Cài đặt", Toast.LENGTH_SHORT).show();
            }
        });

        imageViewLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(StudyActivity.this, "Bố cục", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void resetStudyModeButtons() {
        buttonFlashcards.setBackgroundResource(R.drawable.bg_study_mode);
        buttonLearn.setBackgroundResource(R.drawable.bg_study_mode);
        buttonTest.setBackgroundResource(R.drawable.bg_study_mode);
        buttonListen.setBackgroundResource(R.drawable.bg_study_mode);
        buttonRead.setBackgroundResource(R.drawable.bg_study_mode);
        buttonWrite.setBackgroundResource(R.drawable.bg_study_mode);
    }

    private void initializeQuestions() {
        questions = new ArrayList<>();
        
        // Check if this is the "Động vật" or "Dong vat" set
        if (setTitle != null && (setTitle.equalsIgnoreCase("Động vật") || setTitle.equalsIgnoreCase("Dong vat"))) {
            // Load animal vocabulary from helper
            questions = VocabularyHelper.getAnimalVocabulary();
        } else {
            // Load default vocabulary from helper
            questions = VocabularyHelper.getDefaultVocabulary();
        }
    }

    private void displayQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            VocabularyHelper.Question question = questions.get(index);
            setQuestion(question.term, question.phonetic, question.translation);
            
            // Set answer options
            buttonAnswer1.setText("1: " + question.options[0]);
            buttonAnswer2.setText("2: " + question.options[1]);
            buttonAnswer3.setText("3: " + question.options[2]);
            buttonAnswer4.setText("4: " + question.options[3]);
            
            // Reset button styles
            resetAnswerButtons();
            
            // Hide translation - user needs to guess
            textViewTranslation.setVisibility(View.GONE);
            
            // Update navigation buttons
            buttonPrevious.setEnabled(index > 0);
            buttonNext.setEnabled(index < questions.size() - 1);
        }
    }

    private void updateProgress() {
        textViewProgress.setText((currentQuestionIndex + 1) + " / " + totalQuestions);
    }

    private void setQuestion(String term, String phonetic, String translation) {
        textViewQuestion.setText(term);
        textViewPhonetic.setText(phonetic);
        // Hide translation - user needs to guess
        textViewTranslation.setVisibility(View.GONE);
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
            }, 1000);
        } else {
            button.setBackgroundResource(R.drawable.bg_answer_wrong);
            button.setTextColor(getResources().getColor(R.color.white));
            // Show correct answer
            Button correctButton = getAnswerButton(question.correctAnswer);
            correctButton.setBackgroundResource(R.drawable.bg_answer_correct);
            correctButton.setTextColor(getResources().getColor(R.color.white));
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
}

