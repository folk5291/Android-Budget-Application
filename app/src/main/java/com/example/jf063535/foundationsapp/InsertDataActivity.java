package com.example.jf063535.foundationsapp;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.List;

public class InsertDataActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button mBtn;
    TextView textViewDate;

    Calendar c;
    DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_data);

        mBtn = (Button) findViewById(R.id.DatePicker);
        textViewDate = (TextView) findViewById(R.id.DateTextView);

        Spinner spinner = findViewById(R.id.expense_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.expenses, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        mBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_MONTH);
                int month = c.get(Calendar.MONTH);
                int year = c.get(Calendar.YEAR);

                dpd = new DatePickerDialog(InsertDataActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        textViewDate.setText((month + 1) + "/" + dayOfMonth  + "/" + year);
                    }
                } ,day, month, year);
                dpd.show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
        Spinner spinner = (Spinner) findViewById(R.id.expense_spinner);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

