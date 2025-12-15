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

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword, editTextConfirmPassword, editTextUsername;
    private ImageView imageViewPasswordToggle, imageViewConfirmPasswordToggle;
    private TextView textViewLogIn;
    private boolean isPasswordVisible = false;
    private boolean isConfirmPasswordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Initialize views
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        imageViewPasswordToggle = findViewById(R.id.imageViewPasswordToggle);
        imageViewConfirmPasswordToggle = findViewById(R.id.imageViewConfirmPasswordToggle);
        textViewLogIn = findViewById(R.id.textViewLogIn);

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

        // Confirm password toggle functionality
        imageViewConfirmPasswordToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConfirmPasswordVisible) {
                    editTextConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    imageViewConfirmPasswordToggle.setImageResource(R.drawable.ic_eye_off);
                    isConfirmPasswordVisible = false;
                } else {
                    editTextConfirmPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    imageViewConfirmPasswordToggle.setImageResource(R.drawable.ic_eye);
                    isConfirmPasswordVisible = true;
                }
                editTextConfirmPassword.setSelection(editTextConfirmPassword.getText().length());
            }
        });

        // Navigate to Login
        textViewLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Register button click
        findViewById(R.id.buttonRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegister();
            }
        });
    }

    private void performRegister() {
        String email = editTextEmail.getText().toString().trim();
        String username = editTextUsername.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        if (username.isEmpty()) {
            editTextUsername.setError("Vui lòng nhập tên đăng nhập");
            editTextUsername.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Vui lòng nhập email");
            editTextEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Email không hợp lệ");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Vui lòng nhập mật khẩu");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Mật khẩu phải có ít nhất 6 ký tự");
            editTextPassword.requestFocus();
            return;
        }

        if (confirmPassword.isEmpty()) {
            editTextConfirmPassword.setError("Vui lòng xác nhận mật khẩu");
            editTextConfirmPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            editTextConfirmPassword.setError("Mật khẩu không khớp");
            editTextConfirmPassword.requestFocus();
            return;
        }

        // TODO: Implement actual registration logic here
        Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
        
        // Navigate to login activity after successful registration
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}

