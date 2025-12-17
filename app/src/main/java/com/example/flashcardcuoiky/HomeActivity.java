package com.example.flashcardcuoiky;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView imageViewMenu;
    private LinearLayout layoutRecentContainer;
    private View itemQuiz1, itemQuiz2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize views
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        imageViewMenu = findViewById(R.id.imageViewMenu);
        layoutRecentContainer = findViewById(R.id.layoutRecentContainer);
        itemQuiz1 = findViewById(R.id.itemQuiz1);
        itemQuiz2 = findViewById(R.id.itemQuiz2);

        // Setup navigation drawer
        navigationView.setNavigationItemSelectedListener(this);
        
        // Highlight home item by default
        MenuItem homeItem = navigationView.getMenu().findItem(R.id.nav_home);
        if (homeItem != null) {
            homeItem.setChecked(true);
        }

        // Setup menu button
        imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Setup other buttons
        findViewById(R.id.imageViewAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CreateSetActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.buttonUpgrade).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this, "Nâng cấp", Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.imageViewProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        // Sample quizzes
        setupSampleQuizzes();

        // Load recent sets
        loadRecentSets();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadRecentSets();
    }

    private void setupSampleQuizzes() {
        if (itemQuiz1 != null) {
            TextView title = itemQuiz1.findViewById(R.id.textViewQuizTitle);
            TextView number = itemQuiz1.findViewById(R.id.textViewQuizNumber);
            title.setText("Bài kiểm tra 1");
            number.setText("1");
            itemQuiz1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, TestActivity.class);
                    intent.putExtra("test_title", "Bài kiểm tra 1");
                    startActivity(intent);
                }
            });
        }

        if (itemQuiz2 != null) {
            TextView title = itemQuiz2.findViewById(R.id.textViewQuizTitle);
            TextView number = itemQuiz2.findViewById(R.id.textViewQuizNumber);
            title.setText("Bài kiểm tra 2");
            number.setText("2");
            itemQuiz2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, TestActivity.class);
                    intent.putExtra("test_title", "Bài kiểm tra 2");
                    startActivity(intent);
                }
            });
        }
    }

    private void loadRecentSets() {
        if (layoutRecentContainer == null) return;
        layoutRecentContainer.removeAllViews();

        SharedPreferences prefs = getSharedPreferences("flashcard_prefs", MODE_PRIVATE);
        String json = prefs.getString("recent_sets", null);
        try {
            JSONArray array;

            if (json == null) {
                // Tạo 2 học phần mẫu lần đầu
                array = new JSONArray();
                JSONObject sample1 = new JSONObject();
                sample1.put("title", "Từ vựng tiếng Anh");
                sample1.put("termCount", 12);
                array.put(sample1);

                JSONObject sample2 = new JSONObject();
                sample2.put("title", "Ngữ pháp cơ bản");
                sample2.put("termCount", 10);
                array.put(sample2);

                prefs.edit().putString("recent_sets", array.toString()).apply();
            } else {
                array = new JSONArray(json);
            }

            LayoutInflater inflater = LayoutInflater.from(this);

            int maxItems = Math.min(array.length(), 5);
            for (int i = 0; i < maxItems; i++) {
                JSONObject obj = array.getJSONObject(i);
                String title = obj.optString("title", "Học phần mới");
                int termCount = obj.optInt("termCount", 0);

                View itemView = inflater.inflate(R.layout.item_recent_study, layoutRecentContainer, false);
                TextView textTitle = itemView.findViewById(R.id.textViewSetTitle);
                TextView textInfo = itemView.findViewById(R.id.textViewSetInfo);

                textTitle.setText(title);
                if (termCount > 0) {
                    textInfo.setText(termCount + " thuật ngữ · Tác giả");
                } else {
                    textInfo.setText("Thuật ngữ · Tác giả");
                }

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(HomeActivity.this, StudyActivity.class);
                        intent.putExtra("set_title", title);
                        startActivity(intent);
                    }
                });

                layoutRecentContainer.addView(itemView);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        
        if (id == R.id.nav_home) {
            // Already on home
            Toast.makeText(this, "Trang chủ", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_library) {
            startActivity(new Intent(this, LibraryActivity.class));
        } else if (id == R.id.nav_notifications) {
            startActivity(new Intent(this, NotificationsActivity.class));
        } else if (id == R.id.nav_folders) {
            startActivity(new Intent(this, FoldersActivity.class));
        } else if (id == R.id.nav_new_folder) {
            startActivity(new Intent(this, NewFolderActivity.class));
        } else if (id == R.id.nav_flashcards) {
            startActivity(new Intent(this, FlashcardsLibraryActivity.class));
        } else if (id == R.id.nav_solutions) {
            startActivity(new Intent(this, SolutionsActivity.class));
        } else if (id == R.id.nav_grammar) {
            Intent intent = new Intent(HomeActivity.this, GrammarActivity.class);
            startActivity(intent);
        }
        
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}

