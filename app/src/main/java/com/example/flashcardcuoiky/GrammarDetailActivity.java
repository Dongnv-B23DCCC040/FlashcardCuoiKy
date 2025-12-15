package com.example.flashcardcuoiky;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class GrammarDetailActivity extends AppCompatActivity {

    private ImageView imageViewBack;
    private TextView textViewTitle;
    private TextView tabContent, tabReview, tabPractice;
    private ScrollView scrollViewContent, scrollViewReview, scrollViewPractice;
    private TextView textViewGrammarContent;
    private TextView textViewReviewFormulas, textViewReviewExamples;
    private TextView textViewPracticeQuestion, textViewPracticeResult;
    private Button buttonPracticeAnswer1, buttonPracticeAnswer2, buttonPracticeAnswer3, buttonPracticeAnswer4;
    private Button buttonNextQuestion;

    private String grammarTopic;
    private int currentPracticeIndex = 0;
    private List<PracticeQuestion> practiceQuestions;

    private class PracticeQuestion {
        String question;
        String[] options;
        int correctAnswer;

        PracticeQuestion(String question, String[] options, int correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar_detail);

        // Get grammar topic from intent
        grammarTopic = getIntent().getStringExtra("grammar_topic");
        if (grammarTopic == null) {
            grammarTopic = "Thì hiện tại";
        }

        // Initialize views
        imageViewBack = findViewById(R.id.imageViewBack);
        textViewTitle = findViewById(R.id.textViewTitle);
        tabContent = findViewById(R.id.tabContent);
        tabReview = findViewById(R.id.tabReview);
        tabPractice = findViewById(R.id.tabPractice);
        scrollViewContent = findViewById(R.id.scrollViewContent);
        scrollViewReview = findViewById(R.id.scrollViewReview);
        scrollViewPractice = findViewById(R.id.scrollViewPractice);
        textViewGrammarContent = findViewById(R.id.textViewGrammarContent);
        textViewReviewFormulas = findViewById(R.id.textViewReviewFormulas);
        textViewReviewExamples = findViewById(R.id.textViewReviewExamples);
        textViewPracticeQuestion = findViewById(R.id.textViewPracticeQuestion);
        textViewPracticeResult = findViewById(R.id.textViewPracticeResult);
        buttonPracticeAnswer1 = findViewById(R.id.buttonPracticeAnswer1);
        buttonPracticeAnswer2 = findViewById(R.id.buttonPracticeAnswer2);
        buttonPracticeAnswer3 = findViewById(R.id.buttonPracticeAnswer3);
        buttonPracticeAnswer4 = findViewById(R.id.buttonPracticeAnswer4);
        buttonNextQuestion = findViewById(R.id.buttonNextQuestion);

        // Set title
        textViewTitle.setText(grammarTopic);

        // Initialize content based on topic
        initializeContent();
        initializePracticeQuestions();

        // Back button
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Tab clicks
        tabContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTab(0);
            }
        });

        tabReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTab(1);
            }
        });

        tabPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTab(2);
            }
        });

        // Practice answer buttons
        buttonPracticeAnswer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPracticeAnswer(0);
            }
        });

        buttonPracticeAnswer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPracticeAnswer(1);
            }
        });

        buttonPracticeAnswer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPracticeAnswer(2);
            }
        });

        buttonPracticeAnswer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPracticeAnswer(3);
            }
        });

        // Next question button
        buttonNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPracticeQuestion();
            }
        });

        // Show content tab by default
        showTab(0);
    }

    private void showTab(int tabIndex) {
        // Reset all tabs
        scrollViewContent.setVisibility(View.GONE);
        scrollViewReview.setVisibility(View.GONE);
        scrollViewPractice.setVisibility(View.GONE);
        tabContent.setTextColor(getResources().getColor(R.color.gray_text));
        tabReview.setTextColor(getResources().getColor(R.color.gray_text));
        tabPractice.setTextColor(getResources().getColor(R.color.gray_text));

        // Show selected tab
        switch (tabIndex) {
            case 0: // Content
                scrollViewContent.setVisibility(View.VISIBLE);
                tabContent.setTextColor(getResources().getColor(R.color.purple_primary));
                break;
            case 1: // Review
                scrollViewReview.setVisibility(View.VISIBLE);
                tabReview.setTextColor(getResources().getColor(R.color.purple_primary));
                break;
            case 2: // Practice
                scrollViewPractice.setVisibility(View.VISIBLE);
                tabPractice.setTextColor(getResources().getColor(R.color.purple_primary));
                displayPracticeQuestion();
                break;
        }
    }

    private void initializeContent() {
        String content = "";
        String formulas = "";
        String examples = "";

        switch (grammarTopic) {
            case "Thì hiện tại":
                content = "Thì hiện tại đơn (Present Simple) được sử dụng để:\n\n• Diễn tả thói quen, hành động lặp đi lặp lại\n• Diễn tả sự thật hiển nhiên\n• Diễn tả lịch trình, thời gian biểu\n\nCấu trúc:\n• Khẳng định: S + V(s/es) + O\n• Phủ định: S + do/does + not + V + O\n• Nghi vấn: Do/Does + S + V + O?\n\nVí dụ:\n• I go to school every day.\n• She doesn't like coffee.\n• Do you play football?";
                formulas = "• Khẳng định: S + V(s/es) + O\n• Phủ định: S + do/does + not + V + O\n• Nghi vấn: Do/Does + S + V + O?";
                examples = "• I go to school every day.\n• She doesn't like coffee.\n• Do you play football?\n• They work in a hospital.\n• He doesn't watch TV.";
                break;
            case "Thì quá khứ":
                content = "Thì quá khứ đơn (Past Simple) được sử dụng để:\n\n• Diễn tả hành động đã xảy ra và kết thúc trong quá khứ\n• Diễn tả một chuỗi hành động xảy ra liên tiếp trong quá khứ\n• Diễn tả thói quen trong quá khứ\n\nCấu trúc:\n• Khẳng định: S + V2/ed + O\n• Phủ định: S + did + not + V + O\n• Nghi vấn: Did + S + V + O?\n\nVí dụ:\n• I went to school yesterday.\n• She didn't like coffee.\n• Did you play football?";
                formulas = "• Khẳng định: S + V2/ed + O\n• Phủ định: S + did + not + V + O\n• Nghi vấn: Did + S + V + O?";
                examples = "• I went to school yesterday.\n• She didn't like coffee.\n• Did you play football?\n• They worked in a hospital.\n• He didn't watch TV.";
                break;
            case "Thì tương lai":
                content = "Thì tương lai đơn (Future Simple) được sử dụng để:\n\n• Diễn tả hành động sẽ xảy ra trong tương lai\n• Diễn tả quyết định tức thì\n• Diễn tả lời hứa, đề nghị\n\nCấu trúc:\n• Khẳng định: S + will/shall + V + O\n• Phủ định: S + will/shall + not + V + O\n• Nghi vấn: Will/Shall + S + V + O?\n\nVí dụ:\n• I will go to school tomorrow.\n• She won't like coffee.\n• Will you play football?";
                formulas = "• Khẳng định: S + will/shall + V + O\n• Phủ định: S + will/shall + not + V + O\n• Nghi vấn: Will/Shall + S + V + O?";
                examples = "• I will go to school tomorrow.\n• She won't like coffee.\n• Will you play football?\n• They will work in a hospital.\n• He won't watch TV.";
                break;
            case "Câu điều kiện":
                content = "Câu điều kiện (Conditional) được sử dụng để:\n\n• Diễn tả điều kiện và kết quả\n• Có 3 loại: Loại 1, Loại 2, Loại 3\n\nCấu trúc:\n• Loại 1: If + S + V(s/es), S + will + V\n• Loại 2: If + S + V2/ed, S + would + V\n• Loại 3: If + S + had + V3/ed, S + would have + V3/ed\n\nVí dụ:\n• If it rains, I will stay home.\n• If I were you, I would go.\n• If I had studied, I would have passed.";
                formulas = "• Loại 1: If + S + V(s/es), S + will + V\n• Loại 2: If + S + V2/ed, S + would + V\n• Loại 3: If + S + had + V3/ed, S + would have + V3/ed";
                examples = "• If it rains, I will stay home.\n• If I were you, I would go.\n• If I had studied, I would have passed.\n• If she comes, we will celebrate.\n• If he had money, he would buy a car.";
                break;
            case "Câu bị động":
                content = "Câu bị động (Passive Voice) được sử dụng để:\n\n• Nhấn mạnh đối tượng chịu tác động\n• Khi không biết hoặc không cần nói đến chủ thể thực hiện hành động\n\nCấu trúc:\n• S + be + V3/ed + (by + O)\n• Các thì khác nhau: be được chia theo thì\n\nVí dụ:\n• The book is read by me.\n• The car was repaired yesterday.\n• The house will be built next year.";
                formulas = "• S + be + V3/ed + (by + O)\n• Present: S + am/is/are + V3/ed\n• Past: S + was/were + V3/ed\n• Future: S + will be + V3/ed";
                examples = "• The book is read by me.\n• The car was repaired yesterday.\n• The house will be built next year.\n• English is spoken worldwide.\n• The letter was written by him.";
                break;
            case "Động từ khuyết thiếu":
                content = "Động từ khuyết thiếu (Modal Verbs) bao gồm:\n\n• can, could, may, might, must, shall, should, will, would\n• Được sử dụng để diễn tả khả năng, sự cho phép, nghĩa vụ, v.v.\n\nCấu trúc:\n• S + modal verb + V (nguyên thể)\n• Phủ định: S + modal verb + not + V\n• Nghi vấn: Modal verb + S + V?\n\nVí dụ:\n• I can swim.\n• You must study hard.\n• Should I go?";
                formulas = "• S + modal verb + V (nguyên thể)\n• Phủ định: S + modal verb + not + V\n• Nghi vấn: Modal verb + S + V?";
                examples = "• I can swim.\n• You must study hard.\n• Should I go?\n• She might come.\n• He could help you.";
                break;
            default:
                content = "Nội dung ngữ pháp cho " + grammarTopic;
                formulas = "Công thức cho " + grammarTopic;
                examples = "Ví dụ cho " + grammarTopic;
        }

        textViewGrammarContent.setText(content);
        textViewReviewFormulas.setText(formulas);
        textViewReviewExamples.setText(examples);
    }

    private void initializePracticeQuestions() {
        practiceQuestions = new ArrayList<>();
        
        switch (grammarTopic) {
            case "Thì hiện tại":
                practiceQuestions.add(new PracticeQuestion("I _____ to school every day.", new String[]{"go", "goes", "going", "went"}, 0));
                practiceQuestions.add(new PracticeQuestion("She _____ coffee every morning.", new String[]{"drink", "drinks", "drinking", "drank"}, 1));
                practiceQuestions.add(new PracticeQuestion("They _____ football on weekends.", new String[]{"play", "plays", "playing", "played"}, 0));
                break;
            case "Thì quá khứ":
                practiceQuestions.add(new PracticeQuestion("I _____ to school yesterday.", new String[]{"go", "goes", "going", "went"}, 3));
                practiceQuestions.add(new PracticeQuestion("She _____ coffee this morning.", new String[]{"drink", "drinks", "drinking", "drank"}, 3));
                practiceQuestions.add(new PracticeQuestion("They _____ football last week.", new String[]{"play", "plays", "playing", "played"}, 3));
                break;
            case "Thì tương lai":
                practiceQuestions.add(new PracticeQuestion("I _____ to school tomorrow.", new String[]{"go", "goes", "will go", "went"}, 2));
                practiceQuestions.add(new PracticeQuestion("She _____ coffee tomorrow.", new String[]{"drink", "drinks", "will drink", "drank"}, 2));
                practiceQuestions.add(new PracticeQuestion("They _____ football next week.", new String[]{"play", "plays", "will play", "played"}, 2));
                break;
            default:
                practiceQuestions.add(new PracticeQuestion("Choose the correct answer:", new String[]{"Option A", "Option B", "Option C", "Option D"}, 0));
        }
    }

    private void displayPracticeQuestion() {
        if (currentPracticeIndex < practiceQuestions.size()) {
            PracticeQuestion question = practiceQuestions.get(currentPracticeIndex);
            textViewPracticeQuestion.setText("Chọn đáp án đúng:\n\n" + question.question);
            buttonPracticeAnswer1.setText(question.options[0]);
            buttonPracticeAnswer2.setText(question.options[1]);
            buttonPracticeAnswer3.setText(question.options[2]);
            buttonPracticeAnswer4.setText(question.options[3]);
            
            // Reset buttons
            resetPracticeButtons();
            textViewPracticeResult.setVisibility(View.GONE);
            buttonNextQuestion.setVisibility(View.GONE);
        }
    }

    private void resetPracticeButtons() {
        buttonPracticeAnswer1.setBackgroundResource(R.drawable.bg_answer);
        buttonPracticeAnswer2.setBackgroundResource(R.drawable.bg_answer);
        buttonPracticeAnswer3.setBackgroundResource(R.drawable.bg_answer);
        buttonPracticeAnswer4.setBackgroundResource(R.drawable.bg_answer);
        buttonPracticeAnswer1.setTextColor(getResources().getColor(R.color.white));
        buttonPracticeAnswer2.setTextColor(getResources().getColor(R.color.white));
        buttonPracticeAnswer3.setTextColor(getResources().getColor(R.color.white));
        buttonPracticeAnswer4.setTextColor(getResources().getColor(R.color.white));
    }

    private void checkPracticeAnswer(int selectedIndex) {
        if (currentPracticeIndex >= practiceQuestions.size()) return;
        
        PracticeQuestion question = practiceQuestions.get(currentPracticeIndex);
        boolean isCorrect = (selectedIndex == question.correctAnswer);
        
        resetPracticeButtons();
        textViewPracticeResult.setVisibility(View.VISIBLE);
        
        Button selectedButton = getPracticeButton(selectedIndex);
        Button correctButton = getPracticeButton(question.correctAnswer);
        
        if (isCorrect) {
            selectedButton.setBackgroundResource(R.drawable.bg_answer_correct);
            selectedButton.setTextColor(getResources().getColor(R.color.white));
            textViewPracticeResult.setText("Đúng rồi! ✓");
            textViewPracticeResult.setTextColor(getResources().getColor(R.color.purple_primary));
            Toast.makeText(this, "Đúng rồi!", Toast.LENGTH_SHORT).show();
        } else {
            selectedButton.setBackgroundResource(R.drawable.bg_answer_wrong);
            selectedButton.setTextColor(getResources().getColor(R.color.white));
            correctButton.setBackgroundResource(R.drawable.bg_answer_correct);
            correctButton.setTextColor(getResources().getColor(R.color.white));
            textViewPracticeResult.setText("Sai rồi! Đáp án đúng: " + question.options[question.correctAnswer]);
            textViewPracticeResult.setTextColor(getResources().getColor(R.color.red_highlight));
            Toast.makeText(this, "Sai rồi!", Toast.LENGTH_SHORT).show();
        }
        
        // Disable buttons
        buttonPracticeAnswer1.setEnabled(false);
        buttonPracticeAnswer2.setEnabled(false);
        buttonPracticeAnswer3.setEnabled(false);
        buttonPracticeAnswer4.setEnabled(false);
        
        // Show next question button if not last question
        if (currentPracticeIndex < practiceQuestions.size() - 1) {
            buttonNextQuestion.setVisibility(View.VISIBLE);
        }
    }

    private Button getPracticeButton(int index) {
        switch (index) {
            case 0: return buttonPracticeAnswer1;
            case 1: return buttonPracticeAnswer2;
            case 2: return buttonPracticeAnswer3;
            case 3: return buttonPracticeAnswer4;
            default: return buttonPracticeAnswer1;
        }
    }

    private void nextPracticeQuestion() {
        currentPracticeIndex++;
        if (currentPracticeIndex < practiceQuestions.size()) {
            displayPracticeQuestion();
            buttonPracticeAnswer1.setEnabled(true);
            buttonPracticeAnswer2.setEnabled(true);
            buttonPracticeAnswer3.setEnabled(true);
            buttonPracticeAnswer4.setEnabled(true);
        } else {
            Toast.makeText(this, "Hoàn thành luyện tập!", Toast.LENGTH_SHORT).show();
            currentPracticeIndex = 0;
            displayPracticeQuestion();
        }
    }
}


