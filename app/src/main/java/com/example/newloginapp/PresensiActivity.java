package com.example.newloginapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

public class PresensiActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Spinner spinner;
    EditText input_date, input_time, input_desc;
    Button submit;
    String SpinnerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presensi);

        spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        input_date = findViewById(R.id.date);
        input_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        input_time = findViewById(R.id.time);
        input_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePicker();
            }
        });

        submit = findViewById(R.id.btn_submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        SpinnerText = adapterView.getItemAtPosition(i).toString();
        if (i != 0) {
            findViewById(R.id.desc).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.desc).setVisibility(View.GONE);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        SpinnerText = adapterView.getItemAtPosition(0).toString();
    }

    public void showDatePicker() {
        DialogFragment dateFragment = new DatePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "date-picker");
    }

    public void processDatePickerResult (int day, int month, int year) {
        String day_string = Integer.toString(day);
        String month_string = Integer.toString(month+1);
        String year_string = Integer.toString(year);
        String dateMessage = day_string + " - " + month_string + " - " + year_string;
        input_date.setText(dateMessage);
    }

    //    TimePicker Method
    public void showTimePicker() {
        DialogFragment dateFragment = new TimePickerFragment();
        dateFragment.show(getSupportFragmentManager(), "time-picker");
    }

    public void processTimePickerResult(int hour, int minute){
        String hour_string = Integer.toString(hour);
        String minute_string = Integer.toString(minute);
        String timeMessage = hour_string + " : " + minute_string;
        input_time.setText(timeMessage);
    }

    public void showAlertDialog() {
        AlertDialog.Builder alert = new AlertDialog.Builder(PresensiActivity.this);
        alert.setTitle("Konfirmasi");
        alert.setMessage("Apakah kamu yakin data yang akan kamu kirim sudah sesuai?");

        alert.setPositiveButton("YA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Presensi Berhasil", Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                startActivity(intent);
            }
        });

        alert.setNegativeButton("TIDAK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alert.show();
    }

}