package com.nonsobiose.medic.DatabaseUtils;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.nonsobiose.medic.model.Medication;

/**
 * Created by Chidiebere on 4/4/2018.
 */

@Database(entities = Medication.class, version = 1, exportSchema = false)
public abstract class MedicationDatabase extends RoomDatabase {

    private static MedicationDatabase DATABASE_INSTANCE;

    public abstract MedicationDao MedicationDao();


    public static MedicationDatabase getInstance(Context context) {
        if(DATABASE_INSTANCE == null) {
            DATABASE_INSTANCE = Room.databaseBuilder(context, MedicationDatabase.class, "Medication Database")
                    .allowMainThreadQueries()
                    .build();
        }
        return DATABASE_INSTANCE;
    }


}
