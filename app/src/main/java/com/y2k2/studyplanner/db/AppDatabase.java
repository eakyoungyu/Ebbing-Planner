package com.y2k2.studyplanner.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;


//singleton pattern
@Database(entities = {Event.class, EbbingEvent.class}, version = 4, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract EventDao eventDao();
    private static boolean EXIST = false;
    private static AppDatabase DATABASE;
    private static String dbName = "user-name";

    public static AppDatabase getInstance(Context context){
        if(!EXIST) {
            synchronized (AppDatabase.class) {
                DATABASE = Room.databaseBuilder(context, AppDatabase.class, dbName).build();
                EXIST = true;
            }
        }
        return DATABASE;
    }
    public void destroyInstance(){
        EXIST = false;
        DATABASE.close();
        DATABASE = null;
    }

    public static void setDBName(String name){
        dbName = name;
    }
}
