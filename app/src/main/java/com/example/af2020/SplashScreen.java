package com.example.af2020;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.af2020.week7.CollapsingToolbarActivity;
import com.example.af2020.week8.NetworkActivity;
import com.example.af2020.week9.FileManagementActivity;
import com.example.af2020.week9.room.RoomSampleActivity;

// Usually this intermediate activity is not needed (we can apply the splash theme on any starting activity),
// but for this course it is useful because we can redirect to the desired course without moving the launcher
// attributes from manifest
public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // If we don't want to apply the theme in manifest we can do it programmatically
        // Be sure to call this before onCreate()
//        setTheme(R.style.SplashScreenTheme);
        super.onCreate(savedInstanceState);

        // No need to use setContentView() here as the actual image
        // is set directly on the window by the theme

        // Redirect to the desired activity
        startActivity(new Intent(this, RoomSampleActivity.class));

        // Close splash screen so that it doesn't stay in back stack
        finish(); // if we don't call this the user can actually go back to splash activity
    }
}
