package com.example.room.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.room.R;
import com.example.room.viewmodel.WordViewModel;
import com.example.room.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private WordViewModel mWordViewModel;
    private ActivityMainBinding binding;
    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        BottomNavigationView navigaionView = binding.buttomnNavigation;
        navigaionView.setOnNavigationItemSelectedListener(navListener);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mNavController = Navigation.findNavController(this, R.id.mainFragment);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    return NavigationUI.onNavDestinationSelected(item, mNavController);

                }
            };
}