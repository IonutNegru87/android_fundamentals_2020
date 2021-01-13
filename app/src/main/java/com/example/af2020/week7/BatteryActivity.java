package com.example.af2020.week7;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.af2020.R;

public class BatteryActivity extends AppCompatActivity {
    
    private static final String BATTER_LEVEL_KEY = "battery_level_key";

    private ImageView battery;
    private Button btnMinus;
    private Button btnPlus;
    
    // Initial level to start
    private int currentLevel = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_battery);
        
        battery = findViewById(R.id.battery);
        
        btnMinus = findViewById(R.id.minus);
        btnPlus = findViewById(R.id.plus);

        // recovering the instance state
        if (savedInstanceState != null) {
            currentLevel = savedInstanceState.getInt(BATTER_LEVEL_KEY, 1);
        }
        
        // We set the level when we first create the activity
        updateBatteryLevel(currentLevel);
        updateButtonState(currentLevel);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(BATTER_LEVEL_KEY, currentLevel);
        super.onSaveInstanceState(outState);
    }

    // This callback is called only when there is a saved instance that is previously saved by using
    // onSaveInstanceState(). We restore some state in onCreate(), while we can optionally restore
    // other state here, possibly usable after onStart() has completed.
    // The savedInstanceState Bundle is same as the one used in onCreate().
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public void onBatteryChangeMinus(View view) {
        --currentLevel; // Decrement current level
        updateBatteryLevel(currentLevel);
        updateButtonState(currentLevel);
    }

    public void onBatteryChangePlus(View view) {
        ++currentLevel; // Increment current level
        updateBatteryLevel(currentLevel);
        updateButtonState(currentLevel);
    }
    
    private void updateBatteryLevel(int level) {
        battery.setImageLevel(level);
    }
    
    private void updateButtonState(int level) {
        btnMinus.setEnabled(level > 1);
        btnPlus.setEnabled(level < 7);
    }
}
