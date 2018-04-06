package com.nonsobiose.medic.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import com.nonsobiose.medic.DatabaseUtils.MedicationDatabase;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Chidiebere on 4/4/2018.
 */

@Entity(tableName = "Medication")
public class Medication {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "Name")
    public String mName;

    @ColumnInfo(name = "Description")
    public String mDescription;

    @ColumnInfo(name = "StartDate")
    public String mStartDate;

    @ColumnInfo(name = "EndDate")
    public String mEndDate;

    @ColumnInfo(name = "Interval Number")
    public String mIntervalNumber;

    @ColumnInfo(name = "Interval Type")
    public String mIntervalType;

    @Ignore
    public static final String[] MEDICATION_MONTH = new String[]{"January", "February", "March", "April", "May", "June", "July",
    "August", "September", "October", "November", "December"};



    public Medication(String name, String description, String mStartDate, String mEndDate, String mIntervalNumber, String mIntervalType) {
        this.mName = name;
        this.mDescription = description;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
        this.mIntervalNumber = mIntervalNumber;
        this.mIntervalType = mIntervalType;
    }

    @Ignore
    public static boolean AddNewMedication(String name, String description, String startDate, String endDate, String intervalNumber, String intervalType, Context context) {
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(intervalNumber) && !TextUtils.isEmpty(startDate) && !TextUtils.isEmpty(endDate)) {

            Date medicationStartDate = null;
            Date medicationEndDate = null;
            try {
                //medicationStartDate
                medicationEndDate = new Date(Long.parseLong(endDate));
            } catch (NumberFormatException e) {
                Toast.makeText(context, "Please select a date", Toast.LENGTH_SHORT).show();
                return false;
            }

            if (medicationStartDate.before(medicationEndDate)) {
                Medication medication = new Medication(name, description, startDate, endDate, intervalNumber, intervalType);
                MedicationDatabase.getInstance(context).MedicationDao().insertMedication(medication);
                Log.v("Medication Added Log", "Medication " + name + "Added Successfully");
                return true;
            } else {
                Toast.makeText(context, "Start Date cant be less than End Date", Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(context, "Fields cannot be empty, except Description", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public String getmName() {
        return mName;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmStartDate() {
        return mStartDate;
    }

    public String getmEndDate() {
        return mEndDate;
    }

    public String getmIntervalNumber() {
        return mIntervalNumber;
    }

    public String getmIntervalType() {
        return mIntervalType;
    }
}
