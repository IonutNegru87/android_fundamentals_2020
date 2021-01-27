package com.example.af2020.week9.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.af2020.week9.room.entiy.Company;

import java.util.List;

@Dao
public interface CompanyDao {

    @Query("SELECT * FROM Company")
    LiveData<List<Company>> getAllCompanies();

    @Query("SELECT * FROM Company ORDER BY name ASC")
    LiveData<List<Company>> getCompaniesOrdered();

    @Insert
    void insert(Company company);

    @Query("SELECT * FROM Company WHERE name LIKE :companyName")
    LiveData<List<Company>> getCompanies(String companyName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Company> companies);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Company... companies);

    @Update
    void updateCompany(Company company);

    @Update
    void updateCompanies(Company... company);

    @Delete
    void deleteCompany(Company company);

    @Delete
    void deleteCompanies(Company... company);

}
