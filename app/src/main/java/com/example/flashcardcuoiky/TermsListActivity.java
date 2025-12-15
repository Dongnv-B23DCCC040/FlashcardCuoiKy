package com.example.flashcardcuoiky;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TermsListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTerms;
    private TermsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_list);

        // Back button
        findViewById(R.id.imageViewBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Initialize RecyclerView
        recyclerViewTerms = findViewById(R.id.recyclerViewTerms);
        recyclerViewTerms.setLayoutManager(new LinearLayoutManager(this));

        // Sample data
        List<Term> terms = new ArrayList<>();
        terms.add(new Term("Toaster", "/toustə/", "Máy nướng bánh mỳ", false));
        terms.add(new Term("Cabinet", "/'kæbinit/", "Tủ", false));
        terms.add(new Term("Juicer", "/'dzu:sə/", "Máy ép hoa quả", false));
        terms.add(new Term("Garlic press", "/'ga:lik pres/", "Máy xay tỏi", false));
        terms.add(new Term("Microwave", "/'maikrəweiv/", "Lò vi sóng", false));
        terms.add(new Term("Refrigerator", "/ri'fridʒəreitə/", "Tủ lạnh", false));
        terms.add(new Term("Blender", "/'blendə/", "Máy xay sinh tố", false));
        terms.add(new Term("Coffee maker", "/'kɔfi 'meikə/", "Máy pha cà phê", false));

        adapter = new TermsAdapter(terms);
        recyclerViewTerms.setAdapter(adapter);
    }

    // Term model class
    public static class Term {
        String english;
        String phonetic;
        String vietnamese;
        boolean isFavorite;

        public Term(String english, String phonetic, String vietnamese, boolean isFavorite) {
            this.english = english;
            this.phonetic = phonetic;
            this.vietnamese = vietnamese;
            this.isFavorite = isFavorite;
        }
    }

    // Simple adapter (for now, using basic implementation)
    private class TermsAdapter extends RecyclerView.Adapter<TermsAdapter.ViewHolder> {
        private List<Term> terms;

        public TermsAdapter(List<Term> terms) {
            this.terms = terms;
        }

        @Override
        public ViewHolder onCreateViewHolder(android.view.ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.item_term, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Term term = terms.get(position);
            holder.textViewEnglish.setText(term.english);
            holder.textViewPhonetic.setText(term.phonetic);
            holder.textViewVietnamese.setText(term.vietnamese);
            
            holder.imageViewStar.setImageResource(
                term.isFavorite ? R.drawable.ic_star_filled : R.drawable.ic_star_outline
            );
            
            holder.imageViewStar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    term.isFavorite = !term.isFavorite;
                    holder.imageViewStar.setImageResource(
                        term.isFavorite ? R.drawable.ic_star_filled : R.drawable.ic_star_outline
                    );
                }
            });

            holder.imageViewSpeaker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(TermsListActivity.this, "Phát âm: " + term.english, Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return terms.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView textViewEnglish, textViewPhonetic, textViewVietnamese;
            ImageView imageViewStar, imageViewSpeaker;

            public ViewHolder(View itemView) {
                super(itemView);
                textViewEnglish = itemView.findViewById(R.id.textViewEnglish);
                textViewPhonetic = itemView.findViewById(R.id.textViewPhonetic);
                textViewVietnamese = itemView.findViewById(R.id.textViewVietnamese);
                imageViewStar = itemView.findViewById(R.id.imageViewStar);
                imageViewSpeaker = itemView.findViewById(R.id.imageViewSpeaker);
            }
        }
    }
}


