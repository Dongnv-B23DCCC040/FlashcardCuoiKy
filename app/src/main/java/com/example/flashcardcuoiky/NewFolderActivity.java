package com.example.flashcardcuoiky;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NewFolderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_folder);

        ImageView back = findViewById(R.id.imageViewBack);
        EditText editTextFolderName = findViewById(R.id.editTextFolderName);
        Button buttonCreate = findViewById(R.id.buttonCreateFolder);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextFolderName.getText().toString().trim();
                if (name.isEmpty()) {
                    Toast.makeText(NewFolderActivity.this, "Vui lòng nhập tên thư mục", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NewFolderActivity.this, "Đã tạo thư mục: " + name, Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}

