package com.example.af2020.week9.room;


import androidx.annotation.NonNull;

import com.example.af2020.week9.room.entiy.Company;
import com.example.af2020.week9.room.entiy.Employee;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

class DummyDatabase {

    private static final String TAG = "DummyDatabase";
    private static final String[] COMPANIES = new String[]{
            "Google", "Apple", "LinkedIN"};

    static void initializeDb(@NonNull final AppDatabase db) {
        List<Company> companies = new ArrayList<>(COMPANIES.length);
        List<Employee> employees = new ArrayList<>();

        generateData(companies, employees);

        insertData(db, companies, employees);
    }

    private static void generateData(List<Company> companies, List<Employee> cities) {
        Random rnd = new Random();
        for (int i = 0; i < COMPANIES.length; i++) {
            Company company = new Company();
            company.setName(COMPANIES[i]);
            company.setItemUpdated(new Date());
            company.setCompanyId(i + 1);
            companies.add(company);
        }

        for (Company company : companies) {
            int citiesNumber = rnd.nextInt(5) + 1;
            for (int i = 0; i < citiesNumber; i++) {
                Employee employee = new Employee();
                employee.setCompanyId(company.getCompanyId());
                employee.setName("Employee " + (i + 1) + " from " + company.getName());
                cities.add(employee);
            }
        }

    }

    private static void insertData(@NonNull final AppDatabase db, @NonNull List<Company> companies, @NonNull List<Employee> employees) {
        db.companyDao().insertAll(companies);
        db.employeeDao().insertAll(employees);
    }
}