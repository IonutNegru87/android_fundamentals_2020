package com.example.af2020.week5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.af2020.R;

public class ThreeButtonActivity extends AppCompatActivity implements View.OnClickListener {

    protected static final String EXTRA_TEXT_LONG = "extra_text_long";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.three_button_activity);

        findViewById(R.id.btn_first).setOnClickListener(this);
        findViewById(R.id.btn_second).setOnClickListener(this);
        findViewById(R.id.btn_third).setOnClickListener(this);
    }

    @StringRes
    private int getResForLongText(@IdRes int id) {
        int resId;
        if (id == R.id.btn_first) {
            resId = R.string.first_long_text;
        } else if (id == R.id.btn_second) {
            resId = R.string.second_long_text;
        } else if (id == R.id.btn_third) {
            resId = R.string.third_long_text;
        } else {
            resId = 0;
        }
        return resId;
    }

    @Override
    public void onClick(View view) {
        // get the resource id base on which button was clicked
        int resId = getResForLongText(view.getId());
        // Create the intent and start the new activity
        startActivity(createIntentForGoNext(resId));
    }

    @NonNull
    private Intent createIntentForGoNext(@StringRes int stringRes) {
        Intent intent = new Intent(this, SecondActivity.class);
        // set the string on the intent bundle only if it is valid
        if (stringRes != 0) {
            // format the text
            String text = getString(R.string.text_long_label,
                    getString(stringRes),
                    getString(R.string.large_text));
            // set the text in the intent bundle
            intent.putExtra(EXTRA_TEXT_LONG, text);
        }
        return intent;
    }
}
