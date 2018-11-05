package com.example.jf063535.foundationsapp;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class GetDataActivity extends AppCompatActivity {

    String GetSQLiteQuery, UpdateRecordQuery, DeleteQuery;
    Cursor cursor;
    EditText costInput;
    EditText expenseType;
    Button Insert;
    Button Save;
    Button Add;
    Float cost;
    String label;
    Boolean CheckEditTextEmpty;
    String SQLiteQuery;
    SQLiteDatabase SQLITEDATABASE;
    PieChart pieChart;
    ArrayList<PieEntry> yValues = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        costInput = (EditText)findViewById(R.id.costInput);

        expenseType = (EditText)findViewById(R.id.expenseType);

        Save = (Button)findViewById(R.id.save);

        Insert = (Button)findViewById(R.id.Insert);

        Add = (Button)findViewById(R.id.Add);

        GetSQLiteQuery = "SELECT * FROM demoTable";

        SQLITEDATABASE = openOrCreateDatabase("DemoDataBase", Context.MODE_PRIVATE, null);

        cursor = SQLITEDATABASE.rawQuery(GetSQLiteQuery, null);

        cursor.moveToFirst();

        GetSQLiteDatabaseRecords();


        Insert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!cursor.isLast()) {

                    cursor.moveToNext();
                }

                GetSQLiteDatabaseRecords();

            }
        });

        Add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                if (!cursor.isLast()) {

                    cursor.moveToNext();
                }

                AddData();

            }
        });
    }

    public void GetSQLiteDatabaseRecords() {

        expenseType.setText(cursor.getString(2));

        costInput.setText(cursor.getString(1));

    }

    public void AddData() {

        GetSQLiteDatabaseRecords();

        pieChart = (PieChart) findViewById(R.id.pieChart);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        yValues.add(new PieEntry(Float.valueOf(cursor.getString(1)), cursor.getString(2)));

        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic);
        Description description = new Description();
        description.setText("Your monthly budget");
        description.setTextSize(14);
        pieChart.setDescription(description);

        PieDataSet dataSet = new PieDataSet(yValues, "");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
    }
}

