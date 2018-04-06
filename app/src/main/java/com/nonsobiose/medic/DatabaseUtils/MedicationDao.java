package com.nonsobiose.medic.DatabaseUtils;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import com.nonsobiose.medic.model.Medication;

/**
 * Created by Chidiebere on 4/4/2018.
 */

@Dao
public interface MedicationDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMedication(Medication medication);

    @Query("SELECT * FROM Medication")
    LiveData<List<Medication>> loadMedications();

    @Query("SELECT * FROM Medication WHERE id = :id")
    Medication loadMedication(int id);

    @Query("SELECT * FROM Medication WHERE Name = :name")
    LiveData<List<Medication>> loadMedications(String name);

    @Update
    void updateMedication(Medication medication);

    @Delete
    void deleteMedication(Medication medication);

    @Delete
    void deleteMedications(List<Medication> medication);


}
