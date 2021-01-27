package com.example.af2020.week9.room.entiy;

import android.graphics.Bitmap;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.af2020.week9.room.converter.DateConverter;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Entity(tableName = "Company")
public class Company {
    
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer companyId;
    
    @ColumnInfo(name = "name")
    private String name;
    
    @ColumnInfo(name = "date_updated")
    @TypeConverters(DateConverter.class)
    private Date itemUpdated;
    
    @Ignore
    Bitmap image;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getItemUpdated() {
        return itemUpdated;
    }

    public void setItemUpdated(Date itemUpdated) {
        this.itemUpdated = itemUpdated;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @NotNull
    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", name='" + name + '\'' +
                ", itemUpdated=" + itemUpdated +
                '}';
    }
}
