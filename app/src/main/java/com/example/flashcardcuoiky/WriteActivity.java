package com.example.flashcardcuoiky;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class WriteActivity extends AppCompatActivity {

    private ImageView imageViewBack;
    private TextView textViewHint, textViewTranslation, textViewResult;
    private EditText editTextAnswer;
    private Button buttonCheck;
    private TextView textViewProgress;
    private View buttonPrevious, buttonNext;

    // Question data
    private List<WritingQuestion> questions;
    private int currentQuestionIndex = 0;
    private int totalQuestions = 147;

    // Writing question class
    private class WritingQuestion {
        String phonetic;
        String translation;
        String correctAnswer;

        WritingQuestion(String phonetic, String translation, String correctAnswer) {
            this.phonetic = phonetic;
            this.translation = translation;
            this.correctAnswer = correctAnswer;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        // Initialize views
        imageViewBack = findViewById(R.id.imageViewBack);
        textViewHint = findViewById(R.id.textViewHint);
        textViewTranslation = findViewById(R.id.textViewTranslation);
        editTextAnswer = findViewById(R.id.editTextAnswer);
        buttonCheck = findViewById(R.id.buttonCheck);
        textViewResult = findViewById(R.id.textViewResult);
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

        // Check button
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
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
        
        // Writing questions based on vocabulary
        questions.add(new WritingQuestion("/'kæbinit/", "Tủ", "Cabinet"));
        questions.add(new WritingQuestion("/mıks/", "Trộn", "Mix"));
        questions.add(new WritingQuestion("/po:r/", "Đổ", "Pour"));
        questions.add(new WritingQuestion("/stɜ:/", "Khuấy", "Stir"));
        questions.add(new WritingQuestion("/beık/", "Nướng", "Bake"));
        questions.add(new WritingQuestion("/bɔıl/", "Đun sôi", "Boil"));
        questions.add(new WritingQuestion("/fraı/", "Chiên", "Fry"));
        questions.add(new WritingQuestion("/grıl/", "Nướng", "Grill"));
        questions.add(new WritingQuestion("/sti:m/", "Hấp", "Steam"));
        questions.add(new WritingQuestion("/roʊst/", "Quay", "Roast"));
        questions.add(new WritingQuestion("/slaıs/", "Cắt lát", "Slice"));
        questions.add(new WritingQuestion("/tʃɑ:p/", "Băm", "Chop"));
        questions.add(new WritingQuestion("/pi:l/", "Gọt vỏ", "Peel"));
        questions.add(new WritingQuestion("/daıs/", "Cắt hạt lựu", "Dice"));
        questions.add(new WritingQuestion("/greıt/", "Bào", "Grate"));
        questions.add(new WritingQuestion("/wısk/", "Đánh", "Whisk"));
        questions.add(new WritingQuestion("/ni:d/", "Nhào", "Knead"));
        questions.add(new WritingQuestion("/'mærıneıt/", "Ướp", "Marinate"));
        questions.add(new WritingQuestion("/'si:zən/", "Nêm gia vị", "Season"));
        questions.add(new WritingQuestion("/'gɑ:rnıʃ/", "Trang trí", "Garnish"));
        
        // Add more questions to reach 147
        for (int i = 20; i < totalQuestions; i++) {
            questions.add(new WritingQuestion("/wɜ:rd" + i + "/", "Từ " + i, "Word" + i));
        }
    }

    private void displayQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            WritingQuestion question = questions.get(index);
            textViewHint.setText("Phát âm: " + question.phonetic);
            textViewTranslation.setText("Nghĩa: " + question.translation);
            editTextAnswer.setText("");
            textViewResult.setVisibility(View.GONE);
            
            // Update navigation buttons
            buttonPrevious.setEnabled(index > 0);
            buttonNext.setEnabled(index < questions.size() - 1);
        }
    }

    private void updateProgress() {
        textViewProgress.setText((currentQuestionIndex + 1) + " / " + totalQuestions);
    }

    private void checkAnswer() {
        String userAnswer = editTextAnswer.getText().toString().trim();
        
        if (TextUtils.isEmpty(userAnswer)) {
            Toast.makeText(this, "Vui lòng nhập đáp án", Toast.LENGTH_SHORT).show();
            return;
        }

        if (currentQuestionIndex >= questions.size()) return;
        
        WritingQuestion question = questions.get(currentQuestionIndex);
        boolean isCorrect = userAnswer.equalsIgnoreCase(question.correctAnswer);
        
        textViewResult.setVisibility(View.VISIBLE);
        
        if (isCorrect) {
            textViewResult.setText("Đúng rồi! ✓");
            textViewResult.setTextColor(getResources().getColor(R.color.purple_primary));
            Toast.makeText(this, "Đúng rồi!", Toast.LENGTH_SHORT).show();
            
            // Auto move to next question after delay
            editTextAnswer.postDelayed(new Runnable() {
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
            textViewResult.setText("Sai rồi! Đáp án đúng: " + question.correctAnswer);
            textViewResult.setTextColor(getResources().getColor(R.color.red_highlight));
            Toast.makeText(this, "Sai rồi! Đáp án đúng: " + question.correctAnswer, Toast.LENGTH_SHORT).show();
        }
    }
}


