package com.diplomska.herbal4all.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.diplomska.herbal4all.MainActivityNav;
import com.diplomska.herbal4all.R;
import com.diplomska.herbal4all.RegisterActivity;
import com.diplomska.herbal4all.databinding.FragmentLoginBinding;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    private FirebaseAuth mAuth;

    private String TAG = "FIREBASE LOGIN";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mAuth = FirebaseAuth.getInstance();


        //TODO: preko bindinga se zdaj klicejo funkcije definirane v fragmentih!
        binding.textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: ce klices iz fragmenta mores tako definirati intent!
                Intent intent = new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);


            }
        });

        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Editable emailE = binding.editTextTextEmailAddressLogin.getText();
                String email = emailE.toString();

                Editable pwE = binding.editTextTextPasswordLogin.getText();
                String password = pwE.toString();

                mAuth.signInWithEmailAndPassword(email, password)
                        //TODO: tu more bit getActivity()
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");

                                    Toast.makeText(getActivity(), "Prijava uspesna!",
                                            Toast.LENGTH_SHORT).show();

                                    //TODO: update user interface!!
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    //updateUI(user);

                                    Intent intent = new Intent(getActivity(), MainActivityNav.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());

                                    Toast.makeText(getActivity(), "Prijava neuspesna!",
                                            Toast.LENGTH_SHORT).show();
                                    //updateUI(null);
                                }

                                // ...
                            }



                        });

            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }






}