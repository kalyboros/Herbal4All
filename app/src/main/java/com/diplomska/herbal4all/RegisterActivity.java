package com.diplomska.herbal4all;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

    }


    public void registerUser(View view) {
        EditText editText1 = findViewById(R.id.editTextTextEmailAddress2);
        String email = editText1.getText().toString();

        EditText editText2 = findViewById(R.id.editTextTextPassword);
        String password = editText2.getText().toString();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(RegisterActivity.this, "Registracija uspesna!",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();

                            //TODO: po registraciji updataj user ui
                            //updateUI(user);
                            Intent intent = new Intent(RegisterActivity.this, MainActivityNav.class);
                            startActivity(intent);

                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Registracija neuspesna!",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }


    private void updateUI() {
        TextView tvMail = (TextView) findViewById(R.id.textViewEmail);
        tvMail.setText(user.getEmail());

        TextView tvName = (TextView) findViewById(R.id.textViewUsername);
        tvName.setText(user.getDisplayName());

        ImageView img = (ImageView) findViewById(R.id.imageViewProfile);

        Picasso.get().load(user.getPhotoUrl()).into(img);
    }
}