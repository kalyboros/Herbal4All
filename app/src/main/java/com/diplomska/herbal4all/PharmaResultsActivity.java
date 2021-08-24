package com.diplomska.herbal4all;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class PharmaResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharma_results);

        Bundle b = this.getIntent().getExtras();
        String[] simptomi = b.getStringArray("simptomi");


        String msg = simptomi[2];
        Toast.makeText(PharmaResultsActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}