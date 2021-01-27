package com.example.af2020.week9;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

final class InternalStorage {

    private static final String TAG = "InternalStorage";

    protected void writeInternal(@NonNull Context context, String fileName) {
        String fileContent = "Android Fundamentals";

        FileOutputStream outputStream = null;
        try {
            outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            outputStream.write(fileContent.getBytes());
        } catch (Exception e) {
            Log.e(TAG, "writeInternal: ", e);
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    Log.e(TAG, "writeInternal: ", e);
                }
            }
        }
    }

    @NonNull
    String readInternal(@NonNull Context context, String fileName) {
        InputStreamReader inputStreamReader = null;
        StringBuffer stringBuffer = new StringBuffer();
        try {
            FileInputStream inputStream = context.openFileInput(fileName);
            inputStreamReader = new InputStreamReader(inputStream);
            char[] inputBuffer = new char[200];

            int charRead;
            while ((charRead = inputStreamReader.read(inputBuffer)) > 0) {
                stringBuffer.append(String.copyValueOf(inputBuffer, 0, charRead));
            }
        } catch (Exception e) {
            Log.e(TAG, "readInternal: ", e);
        } finally {
            try {
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (IOException e) {
                Log.e(TAG, "readInternal: ", e);
            }
        }

        return stringBuffer.toString();
    }
}
