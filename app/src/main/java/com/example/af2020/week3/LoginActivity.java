package com.example.af2020.week3;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.af2020.R;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private TextInputLayout emailInputLayout;
    private TextInputLayout phoneInputLayout;

    private EditText emailEditText;
    private EditText phoneEditText;

    private CheckBox termsAndConditionsCheck;

    private Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        // Initial state for the checked box (either here or in layout)
        termsAndConditionsCheck.setChecked(true);

        // Start the validation task when clicking submit
        submitButton.setOnClickListener(view -> {
            if (validate()) {
                handleSuccess();
            } else {
                Log.d(TAG, "submit: input data is not valid.");
            }
        });

        // Update the state of the button based on the terms & conditions checkbox state
        submitButton.setEnabled(termsAndConditionsCheck.isChecked());
        // React to the changes in the checkbox state - enabled/disable the button accordingly
        termsAndConditionsCheck.setOnCheckedChangeListener((compoundButton, isChecked) -> submitButton.setEnabled(isChecked));
    }

    private void initViews() {
        emailInputLayout = findViewById(R.id.til_email);
        phoneInputLayout = findViewById(R.id.til_phone);
        emailEditText = findViewById(R.id.et_email);
        phoneEditText = findViewById(R.id.et_phone);
        termsAndConditionsCheck = findViewById(R.id.cb_terms_conditions);
        submitButton = findViewById(R.id.btn_submit);
    }

    private boolean validate() {
        boolean isValid = true; // default to valid
        if (emailEditText.getText().toString().isEmpty()) {
            emailInputLayout.setError(getString(R.string.email_cannot_be_empty));
            isValid = false;
        } else if (!isValidEmailAddress()) {
            emailInputLayout.setError(getString(R.string.email_not_valid));
            isValid = false;
        } else {
            clearError(emailInputLayout);
        }

        if (phoneEditText.getText().toString().isEmpty()) {
            phoneInputLayout.setError(getString(R.string.phone_cannot_be_empty));
            isValid = false;
        } else {
            clearError(phoneInputLayout);
        }

        return isValid;
    }

    private void handleSuccess() {
        // Show a short success toast message and clear previous input data (reset to initial state)
        Toast.makeText(this, "Everything is ok, data submitted!", Toast.LENGTH_LONG).show();
        clearInputs();
    }

    private void clearInputs() {
        emailEditText.setText("");
        phoneEditText.setText("");
        termsAndConditionsCheck.setChecked(false);
    }

    private boolean isValidEmailAddress() {
        // Rely on Android Pattern Address for emails
        return Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText()).matches();
    }

    /**
     * Utility method for clearing the error on the provided {@link TextInputLayout}
     *
     * @param layout The view for which to clear the error.
     */
    private void clearError(TextInputLayout layout) {
        if (layout != null) {
            layout.setError(null);
        }
    }
}