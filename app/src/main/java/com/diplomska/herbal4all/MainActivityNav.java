package com.diplomska.herbal4all;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.diplomska.herbal4all.ui.login.LoginFragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.diplomska.herbal4all.databinding.ActivityMainNavBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class MainActivityNav extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainNavBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAuth = FirebaseAuth.getInstance();

        binding = ActivityMainNavBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMainActivityNav.toolbar);
   /*
        binding.appBarMainActivityNav.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
  */
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        //TODO: morem dodat tukaj v R.id + v mobile_navigation.xml + novi class + activity_main_drawer.xml + od classa izgled xml

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home,
                R.id.nav_login,
                R.id.nav_pharma,
                R.id.nav_profile,
                R.id.nav_quiz)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_nav);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



        //takoj ga vrze na login ce ni loginan
        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            Toast.makeText(MainActivityNav.this, "Prijavljeni ste kot: " + user.getEmail(),
                    Toast.LENGTH_SHORT).show();
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_activity_nav, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main_activity_nav);

        //ce ne pa pove s katerim racunom je prijavljen in nastavi vrednosti
        if (user != null) {
            updateUI();

        }

        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
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