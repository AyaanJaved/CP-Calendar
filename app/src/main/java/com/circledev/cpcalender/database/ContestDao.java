package com.circledev.cpcalender.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.circledev.cpcalender.models.AllContestsItem;

import java.util.List;

@Dao
public interface ContestDao {
    @Insert
    void insert(AllContestsItem allContestsItem);

    @Query("SELECT * FROM contests")
    List<AllContestsItem> getAllSubsContests();

    @Delete
    void delete(AllContestsItem url);
}
