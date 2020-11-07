package com.example.careu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class homePageDuplicate extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    SharedPreferences sharedPreferences1,sharedPreferences2;

//    @Override
//    public void onBackPressed() {
//        sharedPreferences1=getSharedPreferences("logIn",MODE_PRIVATE);
//        if(sharedPreferences1.contains("logInStatus"))
//        {
//            finishAffinity();
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_duplicate);
        //sessions updates in Login Files

        sharedPreferences2=getSharedPreferences("logIn",MODE_PRIVATE);
//        if(!sharedPreferences2.contains("logInStatus")) {
//            SharedPreferences.Editor editor=sharedPreferences2.edit();
//            editor.putString("logInStatus","IN");
//            editor.apply();
//        }


        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navView);
        toolbar = findViewById(R.id.app_bar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.myProfile:
//                        Toast.makeText(homePageDuplicate.this, "this is my profile", Toast.LENGTH_SHORT).show();
                        Intent myp = new Intent(getApplicationContext(),myprofile.class);
                        startActivity(myp);
                        return true;

                    case R.id.myRequests:
                        Intent myr = new Intent(getApplicationContext(),myrequests.class);
                        startActivity(myr);
                        return true;

                    case R.id.editProfile:
                        Intent editp = new Intent(getApplicationContext(),edit_prof.class);
                        startActivity(editp);
                        return true;

                    case R.id.logOut:
                        SharedPreferences.Editor editor=sharedPreferences2.edit();
                        editor.remove("logInStatus");
                        editor.commit();
                        Intent logout=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(logout);
                        return true;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void Police_Emergency_Service(View view) {
        Intent i = new Intent(this,policeEcomplain.class);
        startActivity(i);
    }

    public void Suwasariya_Ambulance_Srevice(View view) {
        Intent i = new Intent(this,ambulance.class);
        startActivity(i);
    }

    public void profile(View view) {
        Intent i = new Intent(this, myprofile.class);
        startActivity(i);
    }

    public void emgency_Numbers(View view) {
        Intent i = new Intent(this, emergencyNumbersCate.class);
        startActivity(i);
    }

    public void first_Aid_Instructions(View view) {
        Intent i = new Intent(this,firstAidInstruction.class);
        startActivity(i);
    }

}