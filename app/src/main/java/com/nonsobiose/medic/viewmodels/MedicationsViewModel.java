package com.nonsobiose.medic.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.nonsobiose.medic.DatabaseUtils.MedicationDatabase;
import com.nonsobiose.medic.model.Medication;

import java.util.List;

/**
 * Created by Chidiebere on 4/5/2018.
 */

public class MedicationsViewModel extends AndroidViewModel {

    public LiveData<List<Medication>> mMedications;


    public MedicationsViewModel(@NonNull Application application) {
        super(application);
        mMedications = MedicationDatabase.getInstance(application).MedicationDao().loadMedications();
    }

}
