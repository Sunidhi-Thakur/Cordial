package com.example.cordial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AnxietyScore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anxiety_score);

        TextView nMarks = findViewById(R.id.marks);
        Button nActivity = findViewById(R.id.act);
        TextView nResult = findViewById(R.id.result);

        String score_str = getIntent().getStringExtra("SCORE");
        nMarks.setText(score_str);
        String result = getIntent().getStringExtra("RESULT");
        nResult.setText(result);

        nActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnxietyScore.this, Anxiety.class);
                AnxietyScore.this.startActivity(intent);
                AnxietyScore.this.finish();

            }
        });
    }
}
