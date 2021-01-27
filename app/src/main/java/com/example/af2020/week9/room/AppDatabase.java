package com.example.af2020.week9.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.af2020.week9.room.dao.CompanyDao;
import com.example.af2020.week9.room.dao.EmployeeDao;
import com.example.af2020.week9.room.entiy.Company;
import com.example.af2020.week9.room.entiy.Employee;

@Database(entities = {Company.class, Employee.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static final String DB_NAME = "android-courses-db";

    private static AppDatabase INSTANCE;

    public abstract CompanyDao companyDao();

    public abstract EmployeeDao employeeDao();

    public static AppDatabase getAppDatabase(@NonNull Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                    .build();
        }

        return INSTANCE;
    }

    /**
     * Migrate from:
     * version 1 - using Room
     * to
     * version 2 - using Room where the {@link Company} has an extra field: iso3
     */
    @VisibleForTesting
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE Company "
                    + " ADD COLUMN ref_no TEXT");
        }
    };

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
