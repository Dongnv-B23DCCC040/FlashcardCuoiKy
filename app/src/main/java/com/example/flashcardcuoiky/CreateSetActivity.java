package com.example.flashcardcuoiky;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateSetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_set);

        ImageView imageViewBack = findViewById(R.id.imageViewBack);
        Button buttonCreate = findViewById(R.id.buttonCreate);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText title = findViewById(R.id.editTextSetTitle);
                EditText term1 = findViewById(R.id.editTextTerm1);
                EditText definition1 = findViewById(R.id.editTextDefinition1);
                EditText term2 = findViewById(R.id.editTextTerm2);
                EditText definition2 = findViewById(R.id.editTextDefinition2);

                String t = title.getText().toString().trim();
                int termCount = 0;
                if (!term1.getText().toString().trim().isEmpty() ||
                        !definition1.getText().toString().trim().isEmpty()) {
                    termCount++;
                }
                if (!term2.getText().toString().trim().isEmpty() ||
                        !definition2.getText().toString().trim().isEmpty()) {
                    termCount++;
                }

                if (t.isEmpty()) {
                    Toast.makeText(CreateSetActivity.this, "Vui lòng nhập tiêu đề học phần", Toast.LENGTH_SHORT).show();
                } else {
                    saveRecentSet(t, termCount);
                    Toast.makeText(CreateSetActivity.this, "Đã lưu học phần \"" + t + "\"", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void saveRecentSet(String title, int termCount) {
        SharedPreferences prefs = getSharedPreferences("flashcard_prefs", MODE_PRIVATE);
        String json = prefs.getString("recent_sets", "[]");
        try {
            JSONArray array = new JSONArray(json);
            JSONObject obj = new JSONObject();
            obj.put("title", title);
            obj.put("termCount", termCount);
            // Thêm lên đầu danh sách
            JSONArray newArray = new JSONArray();
            newArray.put(obj);
            for (int i = 0; i < array.length(); i++) {
                newArray.put(array.getJSONObject(i));
            }
            prefs.edit().putString("recent_sets", newArray.toString()).apply();
        } catch (JSONException e) {
            e.printStackTrace();
            // Nếu lỗi, lưu đơn giản một phần tử
            JSONArray array = new JSONArray();
            JSONObject obj = new JSONObject();
            try {
                obj.put("title", title);
                obj.put("termCount", termCount);
                array.put(obj);
                prefs.edit().putString("recent_sets", array.toString()).apply();
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
        }
    }
}


