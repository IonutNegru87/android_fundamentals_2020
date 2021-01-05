package com.example.af2020.week5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.af2020.R;

import static com.example.af2020.week5.ThreeButtonActivity.EXTRA_TEXT_LONG;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";

    private TextView tvLongText;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_second);

        // init the view that will display the text
        tvLongText = findViewById(R.id.tv_received_text);
        // handle the intent - get data from it's bundle
        handleReceivedIntent();
    }

    private void handleReceivedIntent() {
        String receivedText = null;
        Intent intent = getIntent();
        if (intent != null) { // for sanity checks: usually it cannot be null
            receivedText = intent.getStringExtra(EXTRA_TEXT_LONG);
        }
        if (receivedText != null) {
            showReceivedText(receivedText);
        } else {
            showNoTextReceived();
        }
    }

    private void showNoTextReceived() {
        tvLongText.setText(R.string.no_text_via_intent);
    }

    private void showReceivedText(@NonNull String receivedText) {
        tvLongText.setText(receivedText);
    }
}
