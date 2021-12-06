package com.example.android.logindemo.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.logindemo.R;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.util.Calendar;

public class SecondActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FirebaseAuth firebaseAuth;
    private ActionBarDrawerToggle toggle;

    ImageView prothom,jugantor,kalerkontho,ittefaq,dailystar,tribune,observer,independent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Calendar calendar=Calendar.getInstance();
        String CurrentDate= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        TextView textViewDate = findViewById(R.id.date);
        textViewDate.setText(CurrentDate);

        prothom=findViewById(R.id.prothom);
        jugantor=findViewById(R.id.jugantor);
        kalerkontho=findViewById(R.id.kalerkontho);
        ittefaq=findViewById(R.id.ittefaq);
        dailystar=findViewById(R.id.dailystar);
        observer=findViewById(R.id.observer);
        tribune=findViewById(R.id.tribune);
        independent=findViewById(R.id.independent);

        prothom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotourl("https://www.prothomalo.com/");
            }
        });
        jugantor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotourl("https://www.jugantor.com/");
            }
        });
        ittefaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotourl("https://www.ittefaq.com.bd/");
            }
        });
        kalerkontho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotourl("https://www.kalerkantho.com/");
            }
        });
        dailystar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotourl("https://www.thedailystar.net/");
            }
        });
        observer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotourl("https://www.observerbd.com/");
            }
        });
        independent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotourl("https://www.theindependentbd.com/");
            }
        });
        tribune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotourl("https://www.dhakatribune.com/");
            }
        });



        firebaseAuth = FirebaseAuth.getInstance();

        drawerLayout=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.nav_view_Id);

        toggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.bringToFront();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void gotourl(String s) {

        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }


    private void Logout() {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this, MainActivity.class));
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(toggle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.nav_home:
                Intent intent = new Intent(this, SecondActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_buy:{
                Intent i = new Intent(this, ItemsActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_sell:{
                Intent i = new Intent(this, UploadActivity.class);
                startActivity(i);
                break;
            }
            case R.id.nav_search:{

                startActivity(new Intent(this, SearchActivity.class));
                break;
            }
            case R.id.nav_profile: {
                startActivity(new Intent(this, ProfileActivity.class));
                break;
            }
            case R.id.nav_logout: {
                Logout();
                break;
            }
            case R.id.nav_dev: {
                startActivity(new Intent(this, DeveloperActivity.class));
                break;
            }
            case R.id.nav_app_about: {
                startActivity(new Intent(this, AboutAppActivity.class));
                break;
            }

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
