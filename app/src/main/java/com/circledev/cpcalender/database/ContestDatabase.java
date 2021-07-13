package com.circledev.cpcalender.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.circledev.cpcalender.models.AllContestsItem;

import org.jetbrains.annotations.NotNull;

@Database(entities = AllContestsItem.class, version = 1)
public abstract class ContestDatabase extends RoomDatabase {
   private static ContestDatabase instance;

   public abstract ContestDao contestDao();

   public static synchronized ContestDatabase getInstance(Context context) {
       if(instance == null) {
           instance = Room.databaseBuilder(context.getApplicationContext(), ContestDatabase.class, "contestDatabase").fallbackToDestructiveMigration().build();
       }
       return instance;
   }
}
