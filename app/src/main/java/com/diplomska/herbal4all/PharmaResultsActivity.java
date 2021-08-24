package com.diplomska.herbal4all;

import androidx.annotation.NonNull;
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

import java.util.HashMap;
import java.util.Map;

public class PharmaResultsActivity extends AppCompatActivity {

    String TAG = "testni";

    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    String data[] = {"January, February", "March", "January", "January, February", "March", "January", "January, February", "March", "January"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharma_results);

        Bundle b = this.getIntent().getExtras();
        String[] simptomi = b.getStringArray("simptomi");


        //firestore
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                Toast.makeText(PharmaResultsActivity.this, document.getData().toString(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                            Toast.makeText(PharmaResultsActivity.this, "Neuspesen query",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            recyclerView = findViewById(R.id.recview);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new RecyclerAdapter(this, data);
            recyclerView.setAdapter(adapter);

















    }

}