package com.example.flashcardcuoiky;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private ImageView imageViewMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize views
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);
        imageViewMenu = findViewById(R.id.imageViewMenu);

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
                Toast.makeText(HomeActivity.this, "Tạo mới", Toast.LENGTH_SHORT).show();
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

        // Setup recent study items click
        View recentStudy1 = findViewById(R.id.layoutRecentStudy);
        if (recentStudy1 != null) {
            recentStudy1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, StudyActivity.class);
                    startActivity(intent);
                }
            });
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

