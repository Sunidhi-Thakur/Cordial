package com.example.cordial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    CardView anxiety, depression, stress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        stress = findViewById(R.id.stress);
        depression = findViewById(R.id.depression);
        anxiety = findViewById(R.id.anxiety);


        anxiety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Dashboard.this, Anxiety.class));

            }
        });


        stress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Dashboard.this, Stress.class));

            }
        });


        depression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Dashboard.this, Depression.class));

            }
        });
    }
}
