package com.diplomska.herbal4all;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PharmaResultsActivity extends AppCompatActivity {

    String TAG = "testni";

    List<String> resultPharma = new ArrayList<String>();

    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharma_results);

        Bundle b = this.getIntent().getExtras();
        String[] resultPharma = b.getStringArray("result");

            //recyclerview setup prvo se to izvede morem pocakat!!!
            recyclerView = findViewById(R.id.recview);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new RecyclerAdapter(this, resultPharma);
            recyclerView.setAdapter(adapter);

            //Log.d("dejson", "tesst");

















/*
                                    Toast.makeText(PharmaResultsActivity.this,document.getId() + document.getData().toString(),
                                            Toast.LENGTH_SHORT).show();

                                            String[] stringArray = simptomi.toArray(new String[0]);
 */

    }


}