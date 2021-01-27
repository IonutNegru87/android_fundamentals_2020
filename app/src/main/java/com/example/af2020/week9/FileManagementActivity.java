package com.example.af2020.week9;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.af2020.R;

import java.io.File;
import java.io.IOException;

public class FileManagementActivity extends AppCompatActivity {

    private static final String TAG = "FileManagementActivity";

    private static final int REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 33;
    private static final int REQUEST_CODE_READ_EXTERNAL_STORAGE_PERMISSION = 34;

    private EditText mEditTextFileName;
    private TextView mTextViewContent;

    private InternalStorage mInternalStorage;
    private ExternalStorage mExternalStorage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_management);

        mInternalStorage = new InternalStorage();
        mExternalStorage = new ExternalStorage();

        initView();
    }

    private void initView() {
        mEditTextFileName = findViewById(R.id.editTextFileName);
        mTextViewContent = findViewById(R.id.textViewContent);
    }

    public void createFileInternalOnClick(View view) {
        if (mEditTextFileName != null && mEditTextFileName.getText() != null) {
            String fileName = mEditTextFileName.getText().toString();
            if (!fileName.isEmpty() && fileName != null) {
                mInternalStorage.writeInternal(this, fileName);
                Toast.makeText(this, getString(R.string.file_saved), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, getString(R.string.cannot_save), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, getString(R.string.cannot_save), Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    public void readFileInternalOnClick(View view) {
        if (mEditTextFileName != null && mEditTextFileName.getText() != null) {
            String fileName = mEditTextFileName.getText().toString();
            if (!fileName.isEmpty() && fileName != null) {
                mTextViewContent.setText("");
                mTextViewContent.setText("Read file internal = ");
                mTextViewContent.append(mInternalStorage.readInternal(this, fileName));
            }
        }
    }

    public void createFileExternalOnClick(View view) {
        if (isExternalStorageWritable()) {
            int writeExternalStoragePermission =
                    ContextCompat.checkSelfPermission(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (isPermissionGranted(writeExternalStoragePermission)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
                return;
            } else {
                if (mEditTextFileName != null && mEditTextFileName.getText() != null) {
                    String fileName = mEditTextFileName.getText().toString();
                    if (!fileName.isEmpty() && fileName != null) {
                        mExternalStorage.writeFileExternal(this, fileName);
                        Toast.makeText(this, getString(R.string.file_saved),
                                Toast.LENGTH_LONG).show();
                        return;
                    }
                }
            }
        }
        Toast.makeText(this, getString(R.string.cannot_write_external),
                Toast.LENGTH_LONG).show();
    }

    public void readFileExternalOnClick(View view) {
        if (isExternalStorageWritable()) {
            int readExternalStoragePermission =
                    ContextCompat.checkSelfPermission(this,
                            Manifest.permission.READ_EXTERNAL_STORAGE);
            if (isPermissionGranted(readExternalStoragePermission)) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_READ_EXTERNAL_STORAGE_PERMISSION);
            } else {
                readFile();
            }
        } else {
            Toast.makeText(this, getString(R.string.cannot_write_external),
                    Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("SetTextI18n")
    private void readFile() {
        if (mEditTextFileName != null && mEditTextFileName.getText() != null) {
            String fileName = mEditTextFileName.getText().toString();
            File textFile = new File(ExternalStorage.getExternalStorageCompat(this), fileName);
            if (!fileName.isEmpty() && fileName != null) {
                mTextViewContent.setText("");
                try {
                    mTextViewContent.setText("Read file external = ");
                    mTextViewContent.append(
                            mExternalStorage.readFileExternal(textFile));
                } catch (IOException e) {
                    Log.e(TAG, "readFile: ", e);
                }
            }
        }
    }

    protected boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    private boolean isPermissionGranted(int permission) {
        return (permission == PackageManager.PERMISSION_GRANTED);
    }
}