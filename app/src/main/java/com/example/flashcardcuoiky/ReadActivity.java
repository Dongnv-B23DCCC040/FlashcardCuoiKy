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

public class ReadActivity extends AppCompatActivity {

    private ImageView imageViewBack;
    private TextView textViewPassage, textViewQuestion;
    private Button buttonAnswer1, buttonAnswer2, buttonAnswer3, buttonAnswer4;
    private TextView textViewProgress;
    private View buttonPrevious, buttonNext;

    // Question data
    private List<ReadingQuestion> questions;
    private int currentQuestionIndex = 0;
    private int totalQuestions = 147;

    // Reading question class
    private class ReadingQuestion {
        String passage;
        String question;
        String[] options;
        int correctAnswer; // 0-3 index

        ReadingQuestion(String passage, String question, String[] options, int correctAnswer) {
            this.passage = passage;
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);

        // Initialize views
        imageViewBack = findViewById(R.id.imageViewBack);
        textViewPassage = findViewById(R.id.textViewPassage);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        buttonAnswer1 = findViewById(R.id.buttonAnswer1);
        buttonAnswer2 = findViewById(R.id.buttonAnswer2);
        buttonAnswer3 = findViewById(R.id.buttonAnswer3);
        buttonAnswer4 = findViewById(R.id.buttonAnswer4);
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
        
        // Reading comprehension questions based on vocabulary
        questions.add(new ReadingQuestion(
            "The kitchen is equipped with modern appliances. There is a cabinet for storing dishes, a toaster for making toast, a dishwasher for cleaning dishes, and an oven for baking.",
            "What is used for storing dishes?",
            new String[]{"Toaster", "Dishwasher", "Oven", "Cabinet"}, 3));
        
        questions.add(new ReadingQuestion(
            "To prepare the cake, you need to mix the ingredients in a bowl. Then you should pour the mixture into a pan and bake it in the oven.",
            "What should you do first when preparing a cake?",
            new String[]{"Pour", "Bake", "Mix", "Stir"}, 2));
        
        questions.add(new ReadingQuestion(
            "When cooking soup, first you need to boil water in a pot. Then add vegetables and let them cook. Don't forget to stir occasionally.",
            "What should you do to the water first?",
            new String[]{"Stir", "Add vegetables", "Boil", "Cook"}, 2));
        
        questions.add(new ReadingQuestion(
            "For a healthy meal, you can steam vegetables instead of frying them. Steaming preserves more nutrients than other cooking methods.",
            "What cooking method preserves more nutrients?",
            new String[]{"Frying", "Steaming", "Boiling", "Roasting"}, 1));
        
        questions.add(new ReadingQuestion(
            "Before cooking, you should prepare the ingredients. Slice the vegetables, chop the herbs, and peel the potatoes.",
            "What should you do to potatoes before cooking?",
            new String[]{"Slice", "Chop", "Peel", "Dice"}, 2));
        
        // Add more questions to reach 147
        for (int i = 5; i < totalQuestions; i++) {
            questions.add(new ReadingQuestion(
                "This is a reading passage about vocabulary word " + i + ". Read carefully and answer the question.",
                "What is the main topic?",
                new String[]{"Option A", "Option B", "Option C", "Option D"}, i % 4));
        }
    }

    private void displayQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            ReadingQuestion question = questions.get(index);
            textViewPassage.setText(question.passage);
            textViewQuestion.setText(question.question);
            
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
        
        ReadingQuestion question = questions.get(currentQuestionIndex);
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
            }, 1500);
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


