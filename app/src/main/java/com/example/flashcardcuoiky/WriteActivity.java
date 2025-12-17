package com.example.flashcardcuoiky;

import android.content.Intent;
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
    private List<VocabularyHelper.WritingQuestion> questions;
    private int currentQuestionIndex = 0;
    private int totalQuestions = 147;
    private String setTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

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
        // Load writing questions based on set title
        if (setTitle != null && (setTitle.equalsIgnoreCase("Động vật") || setTitle.equalsIgnoreCase("Dong vat"))) {
            questions = VocabularyHelper.getAnimalWritingQuestions();
        } else {
            // Default writing questions
            questions = new ArrayList<>();
            questions.add(new VocabularyHelper.WritingQuestion("/'kæbinit/", "Tủ", "Cabinet"));
            questions.add(new VocabularyHelper.WritingQuestion("/mıks/", "Trộn", "Mix"));
            questions.add(new VocabularyHelper.WritingQuestion("/po:r/", "Đổ", "Pour"));
            questions.add(new VocabularyHelper.WritingQuestion("/stɜ:/", "Khuấy", "Stir"));
            questions.add(new VocabularyHelper.WritingQuestion("/beık/", "Nướng", "Bake"));
            questions.add(new VocabularyHelper.WritingQuestion("/bɔıl/", "Đun sôi", "Boil"));
            questions.add(new VocabularyHelper.WritingQuestion("/fraı/", "Chiên", "Fry"));
            questions.add(new VocabularyHelper.WritingQuestion("/grıl/", "Nướng", "Grill"));
            questions.add(new VocabularyHelper.WritingQuestion("/sti:m/", "Hấp", "Steam"));
            questions.add(new VocabularyHelper.WritingQuestion("/roʊst/", "Quay", "Roast"));
            questions.add(new VocabularyHelper.WritingQuestion("/slaıs/", "Cắt lát", "Slice"));
            questions.add(new VocabularyHelper.WritingQuestion("/tʃɑ:p/", "Băm", "Chop"));
            questions.add(new VocabularyHelper.WritingQuestion("/pi:l/", "Gọt vỏ", "Peel"));
            questions.add(new VocabularyHelper.WritingQuestion("/daıs/", "Cắt hạt lựu", "Dice"));
            questions.add(new VocabularyHelper.WritingQuestion("/greıt/", "Bào", "Grate"));
            questions.add(new VocabularyHelper.WritingQuestion("/wısk/", "Đánh", "Whisk"));
            questions.add(new VocabularyHelper.WritingQuestion("/ni:d/", "Nhào", "Knead"));
            questions.add(new VocabularyHelper.WritingQuestion("/'mærıneıt/", "Ướp", "Marinate"));
            questions.add(new VocabularyHelper.WritingQuestion("/'si:zən/", "Nêm gia vị", "Season"));
            questions.add(new VocabularyHelper.WritingQuestion("/'gɑ:rnıʃ/", "Trang trí", "Garnish"));
            
            // Add more questions to reach 147
            for (int i = 20; i < totalQuestions; i++) {
                questions.add(new VocabularyHelper.WritingQuestion("/wɜ:rd" + i + "/", "Từ " + i, "Word" + i));
            }
        }
        totalQuestions = questions.size();
    }

    private void displayQuestion(int index) {
        if (index >= 0 && index < questions.size()) {
            VocabularyHelper.WritingQuestion question = questions.get(index);
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
        
        VocabularyHelper.WritingQuestion question = questions.get(currentQuestionIndex);
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


