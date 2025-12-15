package com.example.flashcardcuoiky;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private ImageView imageViewPasswordToggle;
    private TextView textViewSignUp;
    private boolean isPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Initialize views
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        imageViewPasswordToggle = findViewById(R.id.imageViewPasswordToggle);
        textViewSignUp = findViewById(R.id.textViewSignUp);

        // Password toggle functionality
        imageViewPasswordToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    imageViewPasswordToggle.setImageResource(R.drawable.ic_eye_off);
                    isPasswordVisible = false;
                } else {
                    editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    imageViewPasswordToggle.setImageResource(R.drawable.ic_eye);
                    isPasswordVisible = true;
                }
                editTextPassword.setSelection(editTextPassword.getText().length());
            }
        });

        // Navigate to Sign Up
        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        // Login button click
        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });

        // Forgot password click
        findViewById(R.id.textViewForgotPassword).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this, "Chức năng quên mật khẩu sẽ được triển khai", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void performLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextEmail.setError("Vui lòng nhập email hoặc tên đăng nhập");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Vui lòng nhập mật khẩu");
            editTextPassword.requestFocus();
            return;
        }

        // TODO: Implement actual login logic here
        Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
        
        // Navigate to home activity after successful login
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}

