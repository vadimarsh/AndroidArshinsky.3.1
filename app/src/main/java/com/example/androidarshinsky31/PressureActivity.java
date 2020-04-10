package com.example.androidarshinsky31;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class PressureActivity extends AppCompatActivity {
    private static final String TAG = "ActionsPressActiv";
    private static Set<PressMeasure> dataPresMeas;
    private DatePickerDialog picker;
    private EditText pressUpEdt;
    private EditText pressDownEdt;
    private EditText pulseEdt;
    private CheckBox tachChk;
    private EditText dateTextEdt;
    private EditText timeTextEdt;
    private Button butSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure);

        dataPresMeas = new TreeSet<PressMeasure>(new Comparator<PressMeasure>() {
            @Override
            public int compare(PressMeasure o1, PressMeasure o2) {
                return o1.getDateTime().compareTo(o2.getDateTime());
            }
        });

        this.init();

        dateTextEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Click on DateEdit");

                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(PressureActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                dateTextEdt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
                Log.i(TAG, "Click DateEdit handler finished");
            }
        });
        timeTextEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Click on TimeEdit");
                final Calendar cldr = Calendar.getInstance();
                cldr.set(Calendar.HOUR_OF_DAY, 24);
                int hour = cldr.get(Calendar.HOUR) + 1;
                int minutes = cldr.get(Calendar.MINUTE);
                TimePickerDialog picker = new TimePickerDialog(PressureActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                timeTextEdt.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                picker.show();
                Log.i(TAG, "Click TimeEdit handler finished");
            }
        });

        butSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Save pressure activity button was pressed");
                try {
                    dataPresMeas.add(new PressMeasure(dateTextEdt.getText().toString(), timeTextEdt.getText().toString(), tachChk.isChecked(), Integer.parseInt(pressUpEdt.getText().toString()), Integer.parseInt(pressDownEdt.getText().toString()), Integer.parseInt(pulseEdt.getText().toString())));
                    Toast.makeText(PressureActivity.this, getString(R.string.succesUD), Toast.LENGTH_SHORT).show();
                    dateTextEdt.setText("");
                    timeTextEdt.setText("");
                    pulseEdt.setText("");
                    tachChk.setChecked(false);
                    pressDownEdt.setText("");
                    pressUpEdt.setText("");
                    Log.i(TAG, "Save pressure activity button handler finished");
                } catch (RuntimeException ex) {
                    Log.i(TAG, "Save pressure activity button handler exception");
                    Toast.makeText(PressureActivity.this, getString(R.string.needDataEr), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        dateTextEdt = findViewById(R.id.editDate);
        timeTextEdt = findViewById(R.id.editTime);
        pulseEdt = findViewById(R.id.edPulse);
        tachChk = findViewById(R.id.chbTach);
        pressDownEdt = findViewById(R.id.edDownPres);
        pressUpEdt = findViewById(R.id.edUpPres);
        butSave = findViewById(R.id.savePressBut);
    }
}
