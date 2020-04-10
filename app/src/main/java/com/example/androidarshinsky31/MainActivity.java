package com.example.androidarshinsky31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "ActionsMainActiv";

    private EditText fionEd;
    private EditText ageEd;
    private Button saveBut;
    private Button pressBut;
    private Button healthBut;

    private boolean flag = false;
    private static UserData userdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        saveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Click Save button performed");
                try {

                    String fio = fionEd.getText().toString();
                    if ("".equals(fio)) {
                        fionEd.requestFocus();
                        fionEd.setError(getString(R.string.emptyErr));
                        flag = false;
                    } else {
                        int age = Integer.parseInt(ageEd.getText().toString());
                        if (age < 0) {
                            throw new RuntimeException(getString(R.string.negAgeErr));
                        }
                        userdata = new UserData(fio, age);
                        flag = true;
                        Toast.makeText(MainActivity.this, getString(R.string.succesUD), Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    ageEd.requestFocus();
                    ageEd.setError(getString(R.string.numbErr));
                    flag = false;

                } catch (RuntimeException e) {
                    ageEd.requestFocus();
                    ageEd.setError(e.getMessage());
                    flag = false;
                }


            }
        });
        pressBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressBut.setError(null);
                if(flag){
                    startActivity(new Intent(MainActivity.this,PressureActivity.class));

                }
                else{
                    pressBut.requestFocus();
                    pressBut.setError(getString(R.string.needDataEr));
                    Toast.makeText(MainActivity.this, getString(R.string.needDataEr), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void init() {
        fionEd = findViewById(R.id.nameEd);
        ageEd = findViewById(R.id.ageEd);
        saveBut = findViewById(R.id.saveBt);
        pressBut = findViewById(R.id.presBut);
        healthBut = findViewById(R.id.healthBut);
    }
}
