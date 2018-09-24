package com.example.jf063535.foundationsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PreviousBudgetActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_budget);

        button = (Button) findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openPreviousBudgetDataActivity();
            }
        });
    }

    private void openPreviousBudgetDataActivity() {
        Intent intent = new Intent(this, PreviousBudgetDataActivity.class);
        startActivity(intent);
    }
}
