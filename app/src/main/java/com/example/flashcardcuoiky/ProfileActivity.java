package com.example.flashcardcuoiky;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView textViewUsername, textViewEmail, textViewAvatarInitial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize views
        textViewUsername = findViewById(R.id.textViewUsername);
        textViewEmail = findViewById(R.id.textViewEmail);
        textViewAvatarInitial = findViewById(R.id.textViewAvatarInitial);

        // Get user data from intent or SharedPreferences
        // For now, using default values
        String username = getIntent().getStringExtra("username");
        String email = getIntent().getStringExtra("email");
        
        if (username != null) {
            textViewUsername.setText(username);
            // Set avatar initial
            if (textViewAvatarInitial != null && !username.isEmpty()) {
                textViewAvatarInitial.setText(String.valueOf(username.charAt(0)).toUpperCase());
            }
        } else {
            textViewUsername.setText("nguyndongg");
            if (textViewAvatarInitial != null) {
                textViewAvatarInitial.setText("D");
            }
        }

        if (email != null) {
            textViewEmail.setText(email);
        } else {
            textViewEmail.setText("nguyendong8205@gmail.com");
        }

        // Back button
        findViewById(R.id.imageViewBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Menu item clicks
        findViewById(R.id.layoutAchievements).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "Thành tựu", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.layoutSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "Cài đặt", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.layoutDarkMode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "Chế độ tối", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.layoutLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logout and return to login
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        findViewById(R.id.layoutPrivacy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "Quyền riêng tư", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.layoutHelp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "Giúp đỡ và phản hồi", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.layoutUpgrade).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this, "Nâng cấp", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

