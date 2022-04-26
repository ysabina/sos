package com.example.sos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth=FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()==null){
            Intent intent=new Intent(MainActivity.this,LoginActivity.class);
            startActivity(intent);
        } else {
            bottomNavigationView = findViewById(R.id.bottomNavigationView);
            bottomNavigationView.setOnItemSelectedListener(this);
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,profileFragment).commit();
        }


    }

    ProfileFragment profileFragment=new ProfileFragment();
    SosFragment sosFragment = new SosFragment();
    MapsFragment mapsFragment = new MapsFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.sos:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, sosFragment).commit();
                return true;
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,profileFragment).commit();
                return true;
            case R.id.map:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,mapsFragment).commit();
                return true;
        }
        return false;
    }
}