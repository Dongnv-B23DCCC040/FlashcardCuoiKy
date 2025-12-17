package com.example.flashcardcuoiky;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends AppCompatActivity {

    private TextView textViewProgress;
    private TextView textViewQuestionNumber;
    private TextView textViewTerm;
    private TextView textViewPhonetic;
    private Button buttonAnswer1, buttonAnswer2, buttonAnswer3, buttonAnswer4;
    private TextView textViewDontKnow;
    private LinearLayout questionListContainer;
    private View sidebar;
    
    // Bottom controls
    private ToggleButton toggleProgress;
    private ImageView buttonPreviousBottom, buttonNextBottom;
    private ImageView imageViewPlayBottom, imageViewFullscreenBottom;
    private ImageView imageViewSettingsBottom, imageViewLayoutBottom;
    
    private List<VocabularyHelper.Question> questions;
    private int currentQuestionIndex = 0;
    private int totalQuestions = 20;
    private boolean isSidebarVisible = true;
    private boolean isTrackingProgress = false;
    private boolean isFullscreen = false;
    private boolean isPlaying = false;
    private String setTitle = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // Initialize views
        textViewProgress = findViewById(R.id.textViewProgress);
        textViewQuestionNumber = findViewById(R.id.textViewQuestionNumber);
        textViewTerm = findViewById(R.id.textViewTerm);
        textViewPhonetic = findViewById(R.id.textViewPhonetic);
        buttonAnswer1 = findViewById(R.id.buttonAnswer1);
        buttonAnswer2 = findViewById(R.id.buttonAnswer2);
        buttonAnswer3 = findViewById(R.id.buttonAnswer3);
        buttonAnswer4 = findViewById(R.id.buttonAnswer4);
        textViewDontKnow = findViewById(R.id.textViewDontKnow);
        questionListContainer = findViewById(R.id.questionListContainer);
        sidebar = findViewById(R.id.sidebar);
        
        // Bottom controls
        toggleProgress = findViewById(R.id.toggleProgress);
        buttonPreviousBottom = findViewById(R.id.buttonPreviousBottom);
        buttonNextBottom = findViewById(R.id.buttonNextBottom);
        imageViewPlayBottom = findViewById(R.id.imageViewPlayBottom);
        imageViewFullscreenBottom = findViewById(R.id.imageViewFullscreenBottom);
        imageViewSettingsBottom = findViewById(R.id.imageViewSettingsBottom);
        imageViewLayoutBottom = findViewById(R.id.imageViewLayoutBottom);

        // Get set title from intent
        Intent intent = getIntent();
        if (intent != null) {
            setTitle = intent.getStringExtra("set_title");
            if (setTitle == null) {
                setTitle = "";
            }
        }

        // Initialize questions
        initializeQuestions();

        // Setup UI
        updateProgress();
        displayQuestion(currentQuestionIndex);
        setupQuestionList();

        // Header buttons
        findViewById(R.id.imageViewBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.layoutTestHeader).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivity.this, "Chọn chế độ kiểm tra", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.buttonPrint).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivity.this, "In bài kiểm tra", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.imageViewSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivity.this, "Cài đặt", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.imageViewClose).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Toggle sidebar
        findViewById(R.id.buttonHideList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleSidebar();
            }
        });

        // Answer buttons
        buttonAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(0, buttonAnswer1);
            }
        });

        buttonAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(1, buttonAnswer2);
            }
        });

        buttonAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(2, buttonAnswer3);
            }
        });

        buttonAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(3, buttonAnswer4);
            }
        });

        // Don't know button
        textViewDontKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCorrectAnswer();
            }
        });
        
        // Bottom controls
        toggleProgress.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isTrackingProgress = isChecked;
            Toast.makeText(TestActivity.this, 
                isChecked ? "Đã bật theo dõi tiến độ" : "Đã tắt theo dõi tiến độ", 
                Toast.LENGTH_SHORT).show();
        });
        
        buttonPreviousBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                previousQuestion();
            }
        });
        
        buttonNextBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextQuestion();
            }
        });
        
        imageViewPlayBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                togglePlay();
            }
        });
        
        imageViewFullscreenBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFullscreen();
            }
        });
        
        imageViewSettingsBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivity.this, "Cài đặt bài kiểm tra", Toast.LENGTH_SHORT).show();
            }
        });
        
        imageViewLayoutBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleLayout();
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
        // Limit to 20 questions for test
        if (questions.size() > 20) {
            questions = questions.subList(0, 20);
        }
        totalQuestions = questions.size();
    }

    private void setupQuestionList() {
        questionListContainer.removeAllViews();
        
        for (int i = 0; i < totalQuestions; i++) {
            TextView questionNumber = new TextView(this);
            questionNumber.setText(String.valueOf(i + 1));
            questionNumber.setTextSize(16);
            questionNumber.setTextColor(getResources().getColor(R.color.blue_button));
            questionNumber.setPadding(16, 12, 16, 12);
            questionNumber.setClickable(true);
            questionNumber.setFocusable(true);
            questionNumber.setBackgroundResource(android.R.drawable.list_selector_background);
            
            final int index = i;
            questionNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (index < questions.size()) {
                        currentQuestionIndex = index;
                        displayQuestion(currentQuestionIndex);
                        updateProgress();
                    }
                }
            });
            
            questionListContainer.addView(questionNumber);
        }
    }

    private void displayQuestion(int index) {
        if (index >= questions.size()) {
            return;
        }
        
        VocabularyHelper.Question question = questions.get(index);
        textViewQuestionNumber.setText((index + 1) + "/" + totalQuestions);
        textViewTerm.setText(question.term);
        textViewPhonetic.setText(question.phonetic);
        
        buttonAnswer1.setText(question.options[0]);
        buttonAnswer2.setText(question.options[1]);
        buttonAnswer3.setText(question.options[2]);
        buttonAnswer4.setText(question.options[3]);
        
        // Reset button styles
        resetAnswerButtons();
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

    private void checkAnswer(int selectedIndex, Button selectedButton) {
        VocabularyHelper.Question question = questions.get(currentQuestionIndex);
        boolean isCorrect = (selectedIndex == question.correctAnswer);
        
        resetAnswerButtons();
        
        if (isCorrect) {
            selectedButton.setBackgroundResource(R.drawable.bg_answer_correct);
            selectedButton.setTextColor(getResources().getColor(R.color.white));
            Toast.makeText(this, "Đúng rồi!", Toast.LENGTH_SHORT).show();
            
            // Move to next question after a delay
            findViewById(R.id.mainContent).postDelayed(new Runnable() {
                @Override
                public void run() {
                    nextQuestion();
                }
            }, 1000);
        } else {
            selectedButton.setBackgroundResource(R.drawable.bg_answer_wrong);
            selectedButton.setTextColor(getResources().getColor(R.color.white));
            // Show correct answer
            Button correctButton = getAnswerButton(question.correctAnswer);
            correctButton.setBackgroundResource(R.drawable.bg_answer_correct);
            correctButton.setTextColor(getResources().getColor(R.color.white));
            Toast.makeText(this, "Sai rồi!", Toast.LENGTH_SHORT).show();
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

    private void showCorrectAnswer() {
        VocabularyHelper.Question question = questions.get(currentQuestionIndex);
        resetAnswerButtons();
        Button correctButton = getAnswerButton(question.correctAnswer);
        correctButton.setBackgroundResource(R.drawable.bg_answer_correct);
        correctButton.setTextColor(getResources().getColor(R.color.white));
    }

    private void previousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--;
            displayQuestion(currentQuestionIndex);
            updateProgress();
            scrollToTop();
        } else {
            Toast.makeText(this, "Đây là câu hỏi đầu tiên", Toast.LENGTH_SHORT).show();
        }
    }

    private void nextQuestion() {
        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            displayQuestion(currentQuestionIndex);
            updateProgress();
            scrollToTop();
        } else {
            Toast.makeText(this, "Hoàn thành bài kiểm tra!", Toast.LENGTH_SHORT).show();
        }
    }
    
    private void scrollToTop() {
        findViewById(R.id.mainContent).scrollTo(0, 0);
    }
    
    private void togglePlay() {
        isPlaying = !isPlaying;
        // You can add actual audio playback logic here
        Toast.makeText(this, isPlaying ? "Phát âm thanh" : "Dừng âm thanh", Toast.LENGTH_SHORT).show();
    }
    
    private void toggleFullscreen() {
        isFullscreen = !isFullscreen;
        if (isFullscreen) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
            if (getSupportActionBar() != null) {
                getSupportActionBar().hide();
            }
            sidebar.setVisibility(View.GONE);
            isSidebarVisible = false;
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            if (getSupportActionBar() != null) {
                getSupportActionBar().show();
            }
        }
        Toast.makeText(this, isFullscreen ? "Chế độ toàn màn hình" : "Thoát toàn màn hình", 
            Toast.LENGTH_SHORT).show();
    }
    
    private void toggleLayout() {
        // Toggle between sidebar visible/hidden
        toggleSidebar();
    }

    private void updateProgress() {
        textViewProgress.setText("0/" + totalQuestions + " từ vựng tiếng anh");
    }

    private void toggleSidebar() {
        isSidebarVisible = !isSidebarVisible;
        sidebar.setVisibility(isSidebarVisible ? View.VISIBLE : View.GONE);
    }

}

