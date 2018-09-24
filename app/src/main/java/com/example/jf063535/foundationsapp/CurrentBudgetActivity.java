package com.example.jf063535.foundationsapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CurrentBudgetActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_budget);

        button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openInsertBudgetData();
            }
        });
    }

    private void openInsertBudgetData() {
        Intent intent2 = new Intent(this, InsertDataActivity.class);
        startActivity(intent2);
    }
}

