package com.example.cordial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StressScore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stress_score);

        TextView marks = findViewById(R.id.marks);
        Button activity = findViewById(R.id.act);
        TextView result = findViewById(R.id.result);

        String score_str = getIntent().getStringExtra("SCORE");
        marks.setText(score_str);
        String resultS = getIntent().getStringExtra("RESULT");
        result.setText(resultS);

        activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StressScore.this, Stress.class);
                StressScore.this.startActivity(intent);
                StressScore.this.finish();

            }
        });
    }
}
