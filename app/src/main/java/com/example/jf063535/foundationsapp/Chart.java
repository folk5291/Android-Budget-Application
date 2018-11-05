package com.example.jf063535.foundationsapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.ListView;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.widget.Toast;


import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;

public class Chart extends AppCompatActivity {

    EditText costInput;
    EditText expenseType;
    Button Save;
    Button Insert;
    Float cost;
    String label;
    Boolean CheckEditTextEmpty ;
    String SQLiteQuery;
    SQLiteDatabase SQLITEDATABASE;
    ArrayList<PieEntry> yValues = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_chart);

            costInput = (EditText)findViewById(R.id.costInput);

            expenseType = (EditText)findViewById(R.id.expenseType);

            Save = (Button)findViewById(R.id.save);

            Insert = (Button)findViewById(R.id.Insert);

            Save.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {// TODO Auto-generated method stub

                    DBCreate();

                    SubmitData2SQLiteDB();
                }
            });

            Insert.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub

                    Intent intent = new Intent(Chart.this, GetDataActivity.class);
                    startActivity(intent);

                }
            });
        }

        public void DBCreate(){

            SQLITEDATABASE = openOrCreateDatabase("DemoDataBase", Context.MODE_PRIVATE, null);

            SQLITEDATABASE.execSQL("CREATE TABLE IF NOT EXISTS demoTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, cost VARCHAR, label VARCHAR);");

        }

        public void SubmitData2SQLiteDB(){

            cost = Float.valueOf(costInput.getText().toString());

            label = expenseType.getText().toString();

            CheckEditTextIsEmptyOrNot( cost.toString(), label);

            if(CheckEditTextEmpty == true)
            {

                SQLiteQuery = "INSERT INTO demoTable (cost, label) VALUES('"+cost+"', '"+label+"');";

                SQLITEDATABASE.execSQL(SQLiteQuery);

                Toast.makeText(Chart.this,"Data Submit Successfully", Toast.LENGTH_LONG).show();

                ClearEditTextAfterDoneTask();

            }
            else {

                Toast.makeText(Chart.this,"Please Fill All the Fields", Toast.LENGTH_LONG).show();
            }

        }

        public void CheckEditTextIsEmptyOrNot(String cost, String label){

            if(TextUtils.isEmpty(cost) || TextUtils.isEmpty(label))
            {
                CheckEditTextEmpty = false ;
            }
            else
            {
                CheckEditTextEmpty = true ;
            }

        }

        public void ClearEditTextAfterDoneTask(){
            costInput.getText().clear();
            expenseType.getText().clear();
        }
}