package com.example.androidarshinsky31;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BodyCondActivity extends AppCompatActivity {
    private static final String TAG = "ActionsBodyActiv";
    private static List<BodyMeasure> measures = new ArrayList<BodyMeasure>();
    private EditText edWght;
    private EditText edStps;
    private Button butSaveBM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_cond);

        init();

        butSaveBM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Click Save button performed");
                try {
                    measures.add(new BodyMeasure(Float.parseFloat(edWght.getText().toString()), Integer.parseInt(edStps.getText().toString())));
                    Toast.makeText(BodyCondActivity.this, getString(R.string.succesUD), Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "Handler Save button finished");
                } catch (RuntimeException e) {
                    Log.i(TAG, "Handler Save button RuntimeException");
                    Toast.makeText(BodyCondActivity.this, getString(R.string.needDataEr), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void init() {
        edWght = findViewById(R.id.edMass);
        edStps = findViewById(R.id.edSteps);
        butSaveBM = findViewById(R.id.saveBodyBut);
    }
}
