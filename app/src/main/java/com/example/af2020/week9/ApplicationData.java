package com.example.af2020.week9;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public final class ApplicationData {

    private static final String APP_KEY = "android_course_key";
    
    public static void setValueInSharedPref(@NonNull Context context, String key, String value) {
        SharedPreferences sp = getSharedPreferences(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void setValueInSharedPref(@NonNull Context context, String key, int value) {
        SharedPreferences sp = getSharedPreferences(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    @NonNull
    public static String getValueInSharedPref(@NonNull Context context, String key, @NonNull String defaultValue) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getString(key, defaultValue);
    }

    public static int getValueInSharedPref(@NonNull Context context, String key, int defaultValue) {
        SharedPreferences sp = getSharedPreferences(context);
        return sp.getInt(key, defaultValue);
    }

    public static void deleteValueInSharedPref(@NonNull Context context, String key) {
        SharedPreferences sp = getSharedPreferences(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void deleteAllValuesInSharedPref(@NonNull Context context) {
        SharedPreferences sp = getSharedPreferences(context);

        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getApplicationContext().getSharedPreferences(APP_KEY, Context.MODE_PRIVATE);
    }
}
