package com.nonsobiose.medic;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.nonsobiose.medic.adapter.MedicationAdapter;
import com.nonsobiose.medic.model.Medication;
import com.nonsobiose.medic.viewmodels.MedicationsViewModel;

import java.util.ArrayList;

public class ViewMedActivity extends AppCompatActivity {

    private RecyclerView mMedicationsRecyclerView;
    private MedicationAdapter mMedicationAdapter;
    MedicationsViewModel mMedicationsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_med);

        // Setup RecyclerView and Adapter
        mMedicationsRecyclerView = findViewById(R.id.list_of_medications);
        mMedicationAdapter = new MedicationAdapter(new ArrayList<>(), this);
        mMedicationsRecyclerView.setAdapter(mMedicationAdapter);
        mMedicationsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mMedicationsViewModel = ViewModelProviders.of(this).get(MedicationsViewModel.class);

        mMedicationsViewModel.mMedications.observe(this, medications -> {
            mMedicationAdapter.swapAdapter(medications);
        });
    }
}
