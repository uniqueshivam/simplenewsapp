package com.example.shivamgupta.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class NewsByGoogle extends AppCompatActivity {
    ImageView breaking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_by_google);
        breaking =(ImageView) findViewById(R.id.breaking);
        breaking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent breaking = new Intent(NewsByGoogle.this,breakingnews.class);
                startActivity(breaking);
            }
        });
    }
}
