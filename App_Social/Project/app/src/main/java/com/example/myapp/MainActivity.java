package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private RecyclerView postList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawable_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                UserMenuSelector(item);
                return false;
            }
        });
    }
    private void UserMenuSelector(MenuItem item)
    {
        int caseItem = item.getItemId();
        if (caseItem == R.id.nav_profile)
            Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
        else if (caseItem == R.id.nav_home)
            Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
        else if (caseItem == R.id.nav_friends)
            Toast.makeText(this, "Friends List", Toast.LENGTH_SHORT).show();
        else if (caseItem == R.id.nav_find_friends)
            Toast.makeText(this, "Find Friends", Toast.LENGTH_SHORT).show();
        else if (caseItem == R.id.nav_messages)
            Toast.makeText(this, "Messages", Toast.LENGTH_SHORT).show();
        else if (caseItem == R.id.nav_settings)
            Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
        else if (caseItem == R.id.nav_Logout)
            Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
    }
}