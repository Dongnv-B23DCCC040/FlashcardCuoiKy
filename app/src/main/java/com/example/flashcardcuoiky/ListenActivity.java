package com.example.flashcardcuoiky;

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
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int totalQuestions = 147;

    // Question class
    private class Question {
        String term;
        String phonetic;
        String translation;
        String[] options;
        int correctAnswer; // 0-3 index

        Question(String term, String phonetic, String translation, String[] options, int correctAnswer) {
            this.term = term;
            this.phonetic = phonetic;
            this.translation = translation;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);

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
                    Question question = questions.get(currentQuestionIndex);
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
        questions = new ArrayList<>();
        
        // Same questions as StudyActivity
        questions.add(new Question("Cabinet", "/'kæbinit/", "Tủ", 
            new String[]{"Toaster", "Dishwasher /'diʃ, wɔ:tə/: Máy rửa bát", "Oven", "Cabinet"}, 3));
        
        questions.add(new Question("Mix", "/mıks/", "Trộn", 
            new String[]{"Vỉ nướng", "đánh", "trộn", "/'miksə/: Máy trộn"}, 2));
        
        questions.add(new Question("Pour", "/po:r/", "Đổ", 
            new String[]{"Đổ", "Rót", "Trộn", "Nấu"}, 0));
        
        questions.add(new Question("Stir", "/stɜ:/", "Khuấy", 
            new String[]{"Khuấy", "Đổ", "Trộn", "Nấu"}, 0));
        
        questions.add(new Question("Bake", "/beık/", "Nướng", 
            new String[]{"Nướng", "Rang", "Luộc", "Chiên"}, 0));
        
        questions.add(new Question("Boil", "/bɔıl/", "Đun sôi", 
            new String[]{"Đun sôi", "Nướng", "Chiên", "Hấp"}, 0));
        
        questions.add(new Question("Fry", "/fraı/", "Chiên", 
            new String[]{"Chiên", "Nướng", "Luộc", "Hấp"}, 0));
        
        questions.add(new Question("Grill", "/grıl/", "Nướng", 
            new String[]{"Nướng", "Chiên", "Luộc", "Hấp"}, 0));
        
        questions.add(new Question("Steam", "/sti:m/", "Hấp", 
            new String[]{"Hấp", "Nướng", "Chiên", "Luộc"}, 0));
        
        questions.add(new Question("Roast", "/roʊst/", "Quay", 
            new String[]{"Quay", "Nướng", "Chiên", "Luộc"}, 0));
        
        // Add more questions to reach 147
        for (int i = 10; i < totalQuestions; i++) {
            questions.add(new Question("Word" + i, "/wɜ:rd" + i + "/", "Từ " + i, 
                new String[]{"Option A", "Option B", "Option C", "Option D"}, i % 4));
        }
    }

    private void displayQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            Question question = questions.get(index);
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
        
        Question question = questions.get(currentQuestionIndex);
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


