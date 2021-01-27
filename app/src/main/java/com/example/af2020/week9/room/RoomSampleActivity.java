package com.example.af2020.week9.room;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.af2020.R;
import com.example.af2020.week9.room.entiy.Company;
import com.example.af2020.week9.room.entiy.Employee;

import java.util.List;

public class RoomSampleActivity extends AppCompatActivity {

    private static final String TAG = "RoomSampleActivity";

    private AppDatabase appDatabase;
    private TextView tvCompanies;
    private TextView tvEmployees;
    private TextView tvSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_sample);

        tvCompanies = findViewById(R.id.tvDisplayCompanies);
        tvEmployees = findViewById(R.id.tvDisplayEmployees);
        tvSelected = findViewById(R.id.tvDisplaySelected);

        appDatabase = AppDatabase.getAppDatabase(this);
        insertData();
    }

    private void insertData() {
        // We move DB task to a background thread to avoid blocking the UI thread 
        new Thread(() -> {
            try {
                DummyDatabase.initializeDb(appDatabase);
            } catch (Exception e) {
                Log.e(TAG, "insertData: failed to insert dummy data in DB", e);
            }
        }).start();
    }

    public void getDataOnClick(View view) {
        final LiveData<List<Company>> companiesLiveData = appDatabase.companyDao().getCompaniesOrdered();
        final LiveData<List<Company>> companiesByNameLiveData = appDatabase.companyDao().getCompanies("Apple");
        final LiveData<List<Employee>> employeesLiveData = appDatabase.employeeDao().getAllEmployees();

        companiesLiveData.observe(this, companies -> {
            tvCompanies.setText(getString(R.string.all_companies_n, companies.toString()));
        });
        companiesByNameLiveData.observe(this, companies -> {
            tvSelected.setText(getString(R.string.selected_x, companies.toString()));
        });
        employeesLiveData.observe(this, employees -> {
            tvEmployees.setText(getString(R.string.all_employees_n, employees.toString()));
        });
    }
}