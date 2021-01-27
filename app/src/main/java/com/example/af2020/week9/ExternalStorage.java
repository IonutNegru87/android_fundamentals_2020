package com.example.af2020.week9;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

final class ExternalStorage {
    private static final String TAG = ExternalStorage.class.getSimpleName();
    
    protected void writeFileExternal(@NonNull Context context, String fileName) {
        String content = "Android - External storage";
        File textFile = new File(getExternalStorageCompat(context), fileName);
        try {
            FileOutputStream fos = new FileOutputStream(textFile);

            fos.write(content.getBytes());
            fos.close();
        } catch (IOException e) {
            Log.e(TAG, "writeFileExternal: ", e);
        }
    }
    
    public static File getExternalStorageCompat(@NonNull Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            return context.getExternalFilesDir(null);
        } else {
            return Environment.getExternalStorageDirectory();
        }
    }

    protected String readFileExternal(File file) throws IOException {
        StringBuffer contentOfTheFile = new StringBuffer();
        FileInputStream fis = new FileInputStream(file);
        DataInputStream in = new DataInputStream(fis);
        BufferedReader br =
                new BufferedReader(new InputStreamReader(in));
        String strLine;
        while ((strLine = br.readLine()) != null) {
            contentOfTheFile.append(strLine);
        }
        in.close();
        return contentOfTheFile.toString();
    }
}